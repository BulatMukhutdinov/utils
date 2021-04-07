package tat.mukhutdinov.android.utils

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
class SingleLiveData<T> : MutableLiveData<T> {

    private val pending = AtomicBoolean(false)

    constructor() : super()

    constructor(value: T) : super(value) {
        pending.compareAndSet(false, true)
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        warnObservers()

        super.observe(owner) {
            onChanged(observer, it)
        }
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        warnObservers()

        super.observeForever {
            onChanged(observer, it)
        }
    }

    private fun onChanged(observer: Observer<in T>, value: T) {
        if (pending.compareAndSet(true, false)) {
            observer.onChanged(value)
        }
    }

    private fun warnObservers() {
        if (hasActiveObservers()) {
            Timber.w("Multiple observers registered but only one will be notified of changes.")
        }
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        pending.set(true)
        super.setValue(t)
    }
}