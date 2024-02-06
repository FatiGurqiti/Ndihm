package com.example.denko.ui.screen.info

import com.example.denko.common.Constants.USER_STATE
import com.example.denko.data.local.NdihdenPreferences
import com.example.denko.ui.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val ndihdenPreferences: NdihdenPreferences
) : BaseViewModel<InfoState, InfoEvent, InfoEffect>() {
    override val initialState: InfoState
        get() = InfoState

    override fun onEvent(event: InfoEvent) {
        when (event) {
            is InfoEvent.SetupUser -> {
                ndihdenPreferences.setBoolean(USER_STATE, true)
                InfoEffect.RedirectToDashboard.setEffect()
            }
        }
    }
}