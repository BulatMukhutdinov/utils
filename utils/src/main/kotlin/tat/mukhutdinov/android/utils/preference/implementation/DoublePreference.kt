package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class DoublePreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: Double = 0.0
) : BasePreference<Double>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: Double): Double =
        preferences.getLong(key, defaultValue.long()).double()

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: Double) {
        editor.putLong(key, value.long())
    }

    private fun Double.long() =
        java.lang.Double.doubleToRawLongBits(this)

    private fun Long.double() =
        java.lang.Double.longBitsToDouble(this)
}