package tat.mukhutdinov.android.utils.preference

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.boundary.Preference

abstract class BasePreference<T>(
    private val key: String,
    open val preferences: SharedPreferences,
    private val defaultValue: T
) : Preference<T> {

    override val isSet: Boolean
        get() = preferences.contains(key)

    override fun delete() =
        preferences
            .edit()
            .remove(key)
            .apply()

    override fun get(): T =
        if (!isSet) {
            defaultValue
        } else {
            getValue(key, defaultValue)
        }

    override fun set(value: T) {
        preferences
            .edit()
            .apply { setValue(this, key, value) }
            .apply()
    }

    abstract fun getValue(key: String, defaultValue: T): T

    abstract fun setValue(editor: SharedPreferences.Editor, key: String, value: T)
}