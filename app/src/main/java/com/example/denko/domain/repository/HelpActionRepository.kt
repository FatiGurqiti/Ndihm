package com.example.denko.domain.repository

interface HelpActionRepository {
    fun setHelpAction(value: Boolean)
    fun getHelpAction(): Boolean
}
