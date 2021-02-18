package ru.itmo

import java.lang.Math.abs

class OpenAddressHashTable<K, V>(
    private val size: Int,
) {
    private val internalStorage: Array<TableEl<K, V>?> = Array(size) { null }

    data class TableEl<K, V>(
        val key: K,
        var value: V,
        var next: TableEl<K, V>? = null,
    )

    private fun idx(key: K) = kotlin.math.abs(key.hashCode() % size)

    fun get(key: K): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        while (el !== null) {
            if (el.key == key)
                return el.value
            el = el.next
        }
        return null
    }

    fun put(key: K, value: V): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        while (el !== null) {
            if (el.key == key) {
                val ret = el.value
                el.value = value
                return ret
            } else if (el.next == null) {
                el.next = TableEl(key, value)
                return null
            }
            el = el.next
        }
        internalStorage[idx] = TableEl(key, value)
        return null
    }

    fun delete(key: K): V? {
        val idx = idx(key)
        var el = internalStorage[idx]
        var prev: TableEl<K, V>? = null
        while (el !== null) {
            if (el.key == key) {
                if (prev != null) {
                    prev.next = el.next
                } else {
                    internalStorage[idx] = el.next
                }
                return el.value
            }
            prev = el
            el = el.next
        }
        return null
    }


}