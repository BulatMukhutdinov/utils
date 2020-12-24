package tat.mukhutdinov.android.utils.preference.implementation

import tat.mukhutdinov.android.utils.preference.PreferenceHelper
import tat.mukhutdinov.android.utils.preference.boundary.Preference

class StringPreference(
    private val helper: PreferenceHelper,
    private val defaultValue: String = ""
) : Preference<String> {

    override val isSet: Boolean = helper.isSet

    override fun get(): String =
        if (!helper.isSet) {
            defaultValue
        } else {
            helper.preferences.getString(helper.key, defaultValue) ?: defaultValue
        }

    override fun set(value: String) {
        helper.preferences
            .edit()
            .putString(helper.key, value)
            .apply()
    }

    override fun delete() =
        helper.delete()
}