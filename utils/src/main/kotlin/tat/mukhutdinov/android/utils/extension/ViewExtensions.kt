package tat.mukhutdinov.android.utils.extension

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi

fun Float.toDp() =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

fun Float.toSp() =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

fun View.showKeyboard() {
    // This is to be called when the window already has focus.
    fun View.showKeyboardNow() {
        if (isFocused) {
            post {
                // We still post the call, just in case we are being notified of the windows focus
                // but InputMethodManager didn't get properly setup yet.
                val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    requestFocus()

    if (hasWindowFocus()) {
        showKeyboardNow()
    } else {
        viewTreeObserver.addOnWindowFocusChangeListener(object : ViewTreeObserver.OnWindowFocusChangeListener {

            override fun onWindowFocusChanged(hasFocus: Boolean) {
                // This notification will arrive just before the InputMethodManager gets set up.
                if (hasFocus) {
                    this@showKeyboard.showKeyboardNow()
                    viewTreeObserver.removeOnWindowFocusChangeListener(this)
                }
            }
        })
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun Int.attrToColor(context: Context): Int {
    val typedValue = TypedValue()
    val theme: Resources.Theme = context.theme
    theme.resolveAttribute(this, typedValue, true)
    return typedValue.data
}