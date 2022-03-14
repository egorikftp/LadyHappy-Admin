package com.egoriku.viewmodel

import androidx.compose.runtime.Composable
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

@Composable
expect inline fun <reified T : KmmViewModel> getViewModel(): T

expect inline fun <reified T : KmmViewModel> Module.viewModelFactory(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): Pair<Module, InstanceFactory<T>>