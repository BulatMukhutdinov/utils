package tat.mukhutdinov.android.utils

sealed class AccessStatus {

    /**
     * Newly granted after manual request
     */
    class Granted<T>(val value: T) : AccessStatus()

    /**
     * Available due to previous permission grant
     */
    class Available<T>(val value: T) : AccessStatus()

    object Denied : AccessStatus()

    object Loading : AccessStatus()

    object NotLoading : AccessStatus()
}
