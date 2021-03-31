package tat.mukhutdinov.android.utils.preference.implementation

import tat.mukhutdinov.android.utils.preference.PreferenceHelper
import tat.mukhutdinov.android.utils.preference.boundary.Preference

class DoublePreference(
    private val helper: PreferenceHelper,
    private val defaultValue: Double = 0.0
) : Preference<Double> {

    override val isSet: Boolean = helper.isSet

    override fun get(): Double =
        if (!helper.isSet) {
            defaultValue
        } else {
            helper.preferences.getLong(helper.key, defaultValue.long()).double()
        }

    override fun set(value: Double) {
        helper.preferences
            .edit()
            .putLong(helper.key, value.long())
            .apply()
    }

    override fun delete() =
        helper.delete()

    private fun Double.long() =
        java.lang.Double.doubleToRawLongBits(this)

    private fun Long.double() =
        java.lang.Double.longBitsToDouble(this)
}