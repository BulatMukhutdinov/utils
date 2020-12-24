package tat.mukhutdinov.android.utils.preference.implementation

import tat.mukhutdinov.android.utils.preference.PreferenceHelper
import tat.mukhutdinov.android.utils.preference.boundary.Preference

class BooleanPreference(
    private val helper: PreferenceHelper,
    private val defaultValue: Boolean = false
) : Preference<Boolean> {

    override val isSet: Boolean = helper.isSet

    override fun get(): Boolean =
        if (!helper.isSet) {
            defaultValue
        } else {
            helper.preferences.getBoolean(helper.key, defaultValue)
        }

    override fun set(value: Boolean) {
        helper.preferences
            .edit()
            .putBoolean(helper.key, value)
            .apply()
    }

    override fun delete() =
        helper.delete()
}