package tat.mukhutdinov.android.utils.preference.boundary

interface Preference<T> {

    val isSet: Boolean

    fun get(): T

    fun set(value: T)

    fun delete()
}