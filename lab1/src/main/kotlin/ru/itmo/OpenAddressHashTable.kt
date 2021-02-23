package ru.itmo

class OpenAddressHashTable<K, V>(
    private val size: Int,
) {
    private val internalStorage: Array<TableEl<K, V>?> = Array(size) { null }
    var actions: MutableList<String> = mutableListOf()

    data class TableEl<K, V>(
        val key: K,
        var value: V,
        var next: TableEl<K, V>? = null,
    )

    private fun idx(key: K) = kotlin.math.abs(key.hashCode() % size)

    fun get(key: K): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        actions.add("bucket${idx}")
        var counter = 0
        while (el !== null) {
            if (el.key == key) {
                actions.add("found${idx}:${counter}")
                return el.value
            }
            counter++
            el = el.next
        }
        actions.add("found${idx}:null")
        return null
    }

    fun put(key: K, value: V): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        actions.add("bucket$idx")
        var counter = 0
        while (el !== null) {
            if (el.key == key) {
                val ret = el.value
                actions.add("replace${idx}:${counter}")
                el.value = value
                return ret
            } else if (el.next == null) {
                actions.add("insert${idx}:${counter + 1}")
                el.next = TableEl(key, value)
                return null
            }
            counter++
            el = el.next
        }
        actions.add("insert${idx}:${counter}")
        internalStorage[idx] = TableEl(key, value)
        return null
    }

    fun delete(key: K): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        actions.add("bucket$idx")
        var counter = 0
        var prev: TableEl<K, V>? = null
        while (el !== null) {
            if (el.key == key) {
                if (prev != null) {
                    actions.add("removed${idx}:${counter}")
                    prev.next = el.next
                } else {
                    actions.add("removed${idx}:${counter}")
                    internalStorage[idx] = el.next
                }
                return el.value
            }
            prev = el
            counter++
            el = el.next
        }
        return null
    }


}