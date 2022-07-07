package com.egoriku.imageloader

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import org.jetbrains.skia.Image

val LocalHttpClient = composableCompositionLocalOf { HttpClient(CIO) }

val lruCache = mutableMapOf<String, ByteArray>()

@Composable
fun rememberAsyncImagePainter(url: String): AsyncImagePainter {
    val current = LocalHttpClient.current

    return remember { AsyncImagePainter(current, url) }
}

@Stable
class AsyncImagePainter internal constructor(
    httpClient: HttpClient,
    url: String
) : Painter(), RememberObserver {

    private var rememberScope: CoroutineScope? = null
    private val drawSize = MutableStateFlow(Size.Zero)

    private var painter: Painter? by mutableStateOf(null)
    private var alpha: Float by mutableStateOf(DefaultAlpha)
    private var colorFilter: ColorFilter? by mutableStateOf(null)

    // These fields allow access to the current value
    // instead of the value in the current composition.
    private var _state: State = State.Empty
        set(value) {
            field = value
            state = value
        }
    private var _painter: Painter? = null
        set(value) {
            field = value
            painter = value
        }

    internal var transform = DefaultTransform
    internal var onState: ((State) -> Unit)? = null

    /** The current [AsyncImagePainter.State]. */
    var state: State by mutableStateOf(State.Empty)
        private set

    /** The current [ImageRequest]. */
    var request: String by mutableStateOf(url)
        internal set

    /** The current [ImageLoader]. */
    var imageLoader: HttpClient by mutableStateOf(httpClient)
        internal set

    override val intrinsicSize: Size
        get() = painter?.intrinsicSize ?: Size.Unspecified

    override fun DrawScope.onDraw() {
        // Update the draw scope's current size.
        drawSize.value = size

        // Draw the current painter.
        painter?.apply { draw(size, alpha, colorFilter) }
    }

    override fun applyColorFilter(colorFilter: ColorFilter?): Boolean {
        this.colorFilter = colorFilter
        return true
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onRemembered() {
        // Short circuit if we're already remembered.
        if (rememberScope != null) return

        // Create a new scope to observe state and execute requests while we're remembered.
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        rememberScope = scope

        // Manually notify the child painter that we're remembered.
        (_painter as? RememberObserver)?.onRemembered()


        // Observe the current request and execute any emissions.
        scope.launch {
            snapshotFlow { request }
                .mapLatest {
                    execute().toState()
                }
                .collect(::updateState)
        }
    }

    private suspend fun execute() = coroutineScope {
        // Start executing the request on the main thread.
        val job = async(Dispatchers.IO) {
            return@async try {
                val bytes = lruCache[request]
                    ?: imageLoader.get(request).body<ByteArray>().also {
                        lruCache[request] = it
                    }

                SuccessResult(bytes)
            } catch (e: Exception) {
                println(e.localizedMessage)
                ErrorResult(byteArrayOf())
            }
        }

        return@coroutineScope job.await()
    }

    override fun onForgotten() {
        clear()
        (_painter as? RememberObserver)?.onForgotten()
    }

    override fun onAbandoned() {
        clear()
        (_painter as? RememberObserver)?.onAbandoned()
    }

    private fun clear() {
        rememberScope?.cancel()
        rememberScope = null
    }

    private fun updateState(input: State) {
        val previous = _state
        val current = transform(input)
        _state = current
        _painter = current.painter

        // Manually forget and remember the old/new painters if we're already remembered.
        if (rememberScope != null && previous.painter !== current.painter) {
            (previous.painter as? RememberObserver)?.onForgotten()
            (current.painter as? RememberObserver)?.onRemembered()
        }

        // Notify the state listener.
        onState?.invoke(current)
    }

    private fun ImageResult.toState() = when (this) {
        is SuccessResult -> State.Success(bytes.toPainter(), this)
        is ErrorResult -> State.Error(bytes?.toPainter(), this)
    }

    /** Convert this [Drawable] into a [Painter] using Compose primitives if possible. */
    private fun ByteArray.toPainter(): BitmapPainter {
        val toComposeImageBitmap = Image.makeFromEncoded(this).toComposeImageBitmap()

        return BitmapPainter(toComposeImageBitmap)
    }

    /**
     * The current state of the [AsyncImagePainter].
     */
    sealed class State {

        /** The current painter being drawn by [AsyncImagePainter]. */
        abstract val painter: Painter?

        /** The request has not been started. */
        object Empty : State() {
            override val painter: Painter? get() = null
        }

        /** The request was successful. */
        data class Success(
            override val painter: Painter,
            val result: SuccessResult,
        ) : State()

        /** The request failed due to [ErrorResult.throwable]. */
        data class Error(
            override val painter: Painter?,
            val result: ErrorResult,
        ) : State()
    }

    companion object {
        /**
         * A state transform that does not modify the state.
         */
        val DefaultTransform: (State) -> State = { it }
    }
}

sealed class ImageResult {
    abstract val bytes: ByteArray?
}

class SuccessResult(override val bytes: ByteArray) : ImageResult()

class ErrorResult(override val bytes: ByteArray?) : ImageResult()

@Composable
actual fun remoteImagePainter(url: String): Painter = rememberAsyncImagePainter(url)