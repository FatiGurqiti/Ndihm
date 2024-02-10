package com.example.denko.data.local.repository

import com.example.denko.common.Constants.PREFERENCES_HELP_ACTION
import com.example.denko.data.local.preferences.NdihdenPreferences
import com.example.denko.domain.repository.HelpActionRepository

class HelpActionRepositoryImpl(private val ndihdenPreferences: NdihdenPreferences) :
    HelpActionRepository {

    override fun setHelpAction(value: Boolean) {
        ndihdenPreferences.setBoolean(PREFERENCES_HELP_ACTION, value)
    }

    override fun getHelpAction(): Boolean =
        ndihdenPreferences.getBoolean(PREFERENCES_HELP_ACTION, false)
}