package tat.mukhutdinov.android.utils.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tat.mukhutdinov.android.utils.Dispatchers

/**
 * Сохраняет переданный текст в буфер и показывает toast-сообщение.
 * Запись производится на [Dispatchers.IO], а показ toast'a на [Dispatchers.Main]
 *
 * @receiver Context - контекст для получения @[ClipboardManager]
 * @param textToCopy String - текст для сохранения в буффер
 * @param toastMessage String - сообщение, которое будет показано в toast'e
 * @param dispatchers Dispatchers - диспачеры, на которых будет работать функция
 */
fun Context.copyToClipBoard(textToCopy: String, toastMessage: String, dispatchers: Dispatchers) {
    GlobalScope.launch(dispatchers.IO) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

        val clip = ClipData.newPlainText(null, textToCopy)
        clipboard?.setPrimaryClip(clip)

        withContext(dispatchers.Main) {
            Toast.makeText(this@copyToClipBoard, toastMessage, Toast.LENGTH_LONG).show()
        }
    }
}