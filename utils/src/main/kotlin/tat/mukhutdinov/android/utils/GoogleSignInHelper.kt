package tat.mukhutdinov.android.utils

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
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
        googleSignInLauncher = registry.register(KEY_ACTIVITY_RESULT, owner, GoogleSignInContract(googleSignInClient)) { googleAccount ->
            if (googleAccount != null) {
                this.googleAccountStatus.value = AccessStatus.Available(googleAccount)
            } else {
                this.googleAccountStatus.value = AccessStatus.Denied
            }
        }
    }

    fun requestGoogleSignIn(context: Context) {
        googleAccountStatus.value = AccessStatus.Loading

        val googleAccount = GoogleSignIn.getLastSignedInAccount(context)

        if (googleAccount == null) {
            googleSignInLauncher.launch(Unit)
        } else {
            googleAccountStatus.value = AccessStatus.Available(googleAccount)
        }
    }

    companion object {
        private const val KEY_ACTIVITY_RESULT = "google_sign_in_activity_result_key"
    }
}