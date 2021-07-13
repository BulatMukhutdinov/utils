package tat.mukhutdinov.android.utils

import android.app.Activity
import android.content.Intent
import java.util.LinkedList

/**
 * Канал передачи intent'ов в приложении.
 *
 * Базовая идея использования: активити получает intent в [Activity.onCreate] или в [Activity.onNewIntent] и передает этот intent в [IntentQueue].
 * Фрагменты подписываются на [IntentQueue], используя [Subscriber].
 * При новом intent'e в канале, вызывается метод [Subscriber.receive].
 * Если фрагмент "забирает" этот интент, то другие фрагменты этот интент уже не увидят.
 *
 * @property capacity максимальное кол-во intent'ов в канале. Если в канал добавляется intent [capacity] + 1, то intent #1 будет удален из канала.
 */
class IntentQueue(private val capacity: Int = 10) {

    private val queue = LinkedList<Intent>()

    private val subscribers = mutableListOf<Subscriber>()

    init {
        require(capacity > 0) {
            "Capacity of the IntentQueue have to be greater than 0"
        }
    }

    fun push(intent: Intent) {
        var wasConsumed = false

        for (subscriber in subscribers) {
            if (subscriber.receive(intent)) {
                wasConsumed = true
                break
            }
        }

        if (!wasConsumed) {
            if (queue.size == capacity) {
                queue.removeFirst()
            }

            queue.add(intent)
        }
    }

    fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)

        val temp = queue.toList()

        temp.forEach {
            if (subscriber.receive(it)) {
                queue.remove(it)
            }
        }
    }

    fun unsubscribe(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    fun interface Subscriber {

        /**
         * Вызывается при получении нового значения.
         *
         * @param intent новое значение.
         * @return true, если подписчик "забрал" значение. Следующие подписчики это значение уже не получат.
         */
        fun receive(intent: Intent): Boolean
    }
}