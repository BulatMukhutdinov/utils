package tat.mukhutdinov.android.utils.preference.implementation

import tat.mukhutdinov.android.utils.preference.PreferenceHelper
import tat.mukhutdinov.android.utils.preference.boundary.Preference

class LongPreference(
    private val helper: PreferenceHelper,
    private val defaultValue: Long = 0
) : Preference<Long> {

    override val isSet: Boolean = helper.isSet

    override fun get(): Long =
        if (!helper.isSet) {
            defaultValue
        } else {
            helper.preferences.getLong(helper.key, defaultValue)
        }

    override fun set(value: Long) {
        helper.preferences
            .edit()
            .putLong(helper.key, value)
            .apply()
    }

    override fun delete() =
        helper.delete()
}