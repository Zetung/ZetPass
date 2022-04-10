package com.zetung.android.zetpass

import androidx.fragment.app.Fragment

fun Fragment.coordinator(): InterfaceCoordinator {
    return requireActivity() as InterfaceCoordinator
}

interface InterfaceCoordinator {
    fun start()
    fun finish()
    fun startEnterFragment()
    fun startMainFragment()
    fun startRedactFragment()
}