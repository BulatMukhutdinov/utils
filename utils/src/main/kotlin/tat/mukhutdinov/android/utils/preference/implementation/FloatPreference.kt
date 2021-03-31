package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class FloatPreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: Float = 0f
) : BasePreference<Float>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: Float): Float =
        preferences.getFloat(key, defaultValue)

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: Float) {
        editor.putFloat(key, value)
    }
}