package tat.mukhutdinov.android.utils.activityResultContract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import timber.log.Timber

class GoogleSignInContract(
    private val googleSignInClient: GoogleSignInClient
) : ActivityResultContract<Unit, GoogleSignInAccount?>() {

    override fun createIntent(context: Context, input: Unit?): Intent =
        googleSignInClient.signInIntent

    override fun parseResult(resultCode: Int, result: Intent?): GoogleSignInAccount? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }

        val task = GoogleSignIn.getSignedInAccountFromIntent(result)

        return try {
            task.getResult(ApiException::class.java) ?: throw ApiException(Status.RESULT_INTERNAL_ERROR)
        } catch (exception: ApiException) {
            Timber.e(exception)

            null
        }
    }
}