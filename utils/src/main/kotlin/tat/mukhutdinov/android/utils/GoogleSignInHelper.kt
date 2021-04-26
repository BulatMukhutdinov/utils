package tat.mukhutdinov.android.utils

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import tat.mukhutdinov.android.utils.activityResultContract.GoogleSignInContract

/**
 * Если регестрировать колбеки через [ActivityResultRegistry], как советуется в документации https://developer.android.com/training/basics/intents/result#separate,
 * то при использовании в двух фрагментах, колбек [ActivityResultRegistry.register] будет срабатывать только для первого.
 */
class GoogleSignInHelper(
    private val googleSignInClient: GoogleSignInClient,
    private val resultCaller: ActivityResultCaller
) : DefaultLifecycleObserver {

    val googleAccountStatus = MutableLiveData<AccessStatus>(AccessStatus.NotLoading)

    private lateinit var googleSignInLauncher: ActivityResultLauncher<Unit>

    override fun onCreate(owner: LifecycleOwner) {
        googleSignInLauncher = resultCaller.registerForActivityResult(GoogleSignInContract(googleSignInClient)) { googleAccount ->
            if (googleAccount != null) {
                googleAccountStatus.value = AccessStatus.Available(googleAccount)
            } else {
                googleAccountStatus.value = AccessStatus.Denied
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        (owner as? Fragment)?.context?.let { context ->
            val account = GoogleSignIn.getLastSignedInAccount(context)

            if (account == null) {
                googleAccountStatus.value = AccessStatus.Denied
            } else {
                googleAccountStatus.value = AccessStatus.Available(account)
            }
        }
    }

    fun signIn() {
        googleAccountStatus.value = AccessStatus.Loading

        googleSignInLauncher.launch(Unit)
    }

    fun signOut() {
        googleSignInClient.signOut().addOnSuccessListener {
            googleAccountStatus.value = AccessStatus.Denied
        }
    }
}