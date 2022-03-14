package com.egoriku.viewmodel

import androidx.compose.runtime.Composable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.androidx.compose.getViewModel

@Composable
actual inline fun <reified T : KmmViewModel> getViewModel(): T = getViewModel()

actual inline fun <reified T : KmmViewModel> Module.viewModelFactory(
    qualifier: Qualifier?,
    noinline definition: Definition<T>
): Pair<Module, InstanceFactory<T>> = viewModel(qualifier, definition)