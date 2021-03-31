package tat.mukhutdinov.android.utils.preference.implementation

import tat.mukhutdinov.android.utils.preference.PreferenceHelper
import tat.mukhutdinov.android.utils.preference.boundary.Preference

class FloatPreference(
    private val helper: PreferenceHelper,
    private val defaultValue: Float = 0f
) : Preference<Float> {

    override val isSet: Boolean = helper.isSet

    override fun get(): Float =
        if (!helper.isSet) {
            defaultValue
        } else {
            helper.preferences.getFloat(helper.key, defaultValue)
        }

    override fun set(value: Float) {
        helper.preferences
            .edit()
            .putFloat(helper.key, value)
            .apply()
    }

    override fun delete() =
        helper.delete()
}