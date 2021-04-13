package tat.mukhutdinov.android.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import tat.mukhutdinov.android.utils.activityResultContract.GoogleSignInContract

class GoogleSignInHelper(
    private val googleSignInClient: GoogleSignInClient,
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {

    val googleAccountStatus = MutableLiveData<AccessStatus>(AccessStatus.NotLoading)

    private lateinit var googleSignInLauncher: ActivityResultLauncher<Unit>

    override fun onCreate(owner: LifecycleOwner) {
        silentSignIn(owner)
        registerResultLauncher(owner)
    }

    private fun registerResultLauncher(owner: LifecycleOwner) {
        googleSignInLauncher = registry.register(KEY_ACTIVITY_RESULT, owner, GoogleSignInContract(googleSignInClient)) { googleAccount ->
            if (googleAccount != null) {
                this.googleAccountStatus.value = AccessStatus.Available(googleAccount)
            } else {
                this.googleAccountStatus.value = AccessStatus.Denied
            }
        }
    }

    private fun silentSignIn(owner: LifecycleOwner) {
        (owner as? Fragment)?.context?.let { context ->
            GoogleSignIn.getLastSignedInAccount(context)?.let { account ->
                googleAccountStatus.value = AccessStatus.Available(account)
            }
        }
    }

    fun requestGoogleSignIn() {
        googleAccountStatus.value = AccessStatus.Loading

        googleSignInLauncher.launch(Unit)
    }

    companion object {
        private const val KEY_ACTIVITY_RESULT = "google_sign_in_activity_result_key"
    }
}