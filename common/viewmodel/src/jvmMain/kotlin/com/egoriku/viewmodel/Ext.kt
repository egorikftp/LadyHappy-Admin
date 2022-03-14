package com.egoriku.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent

@Composable
actual inline fun <reified T : KmmViewModel> getViewModel(): T = remember { KoinJavaComponent.get(T::class.java) }

actual inline fun <reified T : KmmViewModel> Module.viewModelFactory(
    qualifier: Qualifier?,
    noinline definition: Definition<T>
): Pair<Module, InstanceFactory<T>> = factory(qualifier, definition)