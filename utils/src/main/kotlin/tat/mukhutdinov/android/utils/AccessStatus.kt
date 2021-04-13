package tat.mukhutdinov.android.utils

sealed class AccessStatus {

    class Available<T>(val value: T) : AccessStatus()

    object Denied : AccessStatus()

    object Loading : AccessStatus()

    object NotLoading : AccessStatus()
}
