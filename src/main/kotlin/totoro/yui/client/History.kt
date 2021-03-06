package totoro.yui.client

import java.util.*
import kotlin.collections.HashMap

/**
 * Stores some amount of last chat messages for actions to use them.
 */

class History(private val size: Int) {
    class Record(val user: String, val message: String)

    private val history = HashMap<String, LinkedList<Record>>()

    fun add(chan: String, user: String, message: String) {
        if (!history.contains(chan)) history[chan] = LinkedList<Record>()
        history[chan]?.addLast(Record(user, message))
        if (history[chan]?.size ?: 0 > size) history[chan]?.removeFirst()
    }

    fun get(chan: String, index: Int) = history[chan]?.get(index)

    fun getFromEnd(chan: String, index: Int) = history[chan]?.get((history[chan]?.size ?: 0) - 1 - index)

    fun last(chan: String) = history[chan]?.last

    fun lastByUser(chan: String, user: String) = history[chan]?.lastOrNull { it.user == user }
}
