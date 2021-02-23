package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HashTest {
    private val size = 3
    private var hashTable: OpenAddressHashTable<String, String> = OpenAddressHashTable(size)
    @BeforeEach
    fun initTable() {
        hashTable = OpenAddressHashTable(size)
    }

    @Test
    fun `put and get data from hash table`() {
        val name = "Georgii"
        val surname = "Savin"
        hashTable.put("surName", surname) // hash -1853946629 % 3 = -2
        hashTable.put("name", name) // hash 3373707 = 0

        assertEquals(surname, hashTable.get("surName"))
        assertEquals(name, hashTable.get("name"))

        val expectedActions = listOf("bucket2", "insert2:0", "bucket0", "insert0:0", "bucket2", "found2:0", "bucket0", "found0:0")
        assertEquals(expectedActions, hashTable.actions)
    }

    @Test
    fun `check data absence after delete from table`() {
        val name = "Georgii"
        hashTable.put("name", name)
        assertEquals(name, hashTable.get("name"))
        hashTable.delete("name")
        assertEquals(null, hashTable.get("name"))

        val expectedActions = listOf("bucket0", "insert0:0", "bucket0", "found0:0", "bucket0", "removed0:0", "bucket0", "found0:null")
        assertEquals(expectedActions, hashTable.actions)
    }

    @Test
    fun `check value replacement`() {
        val name = "Georgii"
        val newName = "Georgij"
        hashTable.put("name", name)
        hashTable.put("name", newName)
        assertEquals(newName, hashTable.get("name"))

        val expectedActions = listOf("bucket0", "insert0:0", "bucket0", "replace0:0", "bucket0", "found0:0")
        assertEquals(expectedActions, hashTable.actions)
    }

    @Test
    fun `check work of put and get with 100 percent collision`() {
        // collision is unavoidable if we have more elements than the table size
        val strs = listOf("str0", "str1", "str2", "str3") // buckets: 0, 1, 2, 0
        strs.forEach { s -> hashTable.put(s, s) }
        strs.forEach { s -> assertEquals(s, hashTable.get(s)) }

        val expectedActions = listOf("bucket0", "insert0:0", "bucket1", "insert1:0", "bucket2", "insert2:0", "bucket0", "insert0:1",
        "bucket0", "found0:0", "bucket1", "found1:0", "bucket2", "found2:0", "bucket0", "found0:1")
        assertEquals(expectedActions, hashTable.actions)
    }

    @Test
    fun `check work with collisions after delete`() {
        val strsToDelete = (1..size + 1).asIterable().map { i -> "strToDelete$i" } // buckets: 2, 0, 1, 2
        val strsToCheck = (1..size).asIterable().map { i -> "strToCheck$i" } // buckets: 2, 0, 1

        val expectedActions = mutableListOf<String>()
        strsToDelete.plus(strsToCheck).forEach { s -> hashTable.put(s, s) }
        expectedActions.addAll(listOf("bucket2", "insert2:0", "bucket0", "insert0:0", "bucket1", "insert1:0", "bucket2", "insert2:1",
            "bucket2", "insert2:2", "bucket0", "insert0:1", "bucket1", "insert1:1"))
        strsToDelete.forEach { s -> hashTable.delete(s) }
        expectedActions.addAll(listOf("bucket2", "removed2:0", "bucket0", "removed0:0", "bucket1", "removed1:0", "bucket2", "removed2:0"))
        strsToCheck.forEach { s -> assertEquals(s, hashTable.get(s)) }
        expectedActions.addAll(listOf("bucket2", "found2:0", "bucket0", "found0:0", "bucket1", "found1:0"))

        assertEquals(expectedActions, hashTable.actions)
    }

    @Test
    fun `check correct delete in the middle of sequence`() {
        hashTable = OpenAddressHashTable(1)
        val strs = (1..3).asIterable().map { i -> "str$i" }

        strs.forEach { s -> hashTable.put(s, s) }
        hashTable.delete(strs[1])
        assertEquals(strs[0], hashTable.get(strs[0]))
        assertEquals(null, hashTable.get(strs[1]))
        assertEquals(strs[2], hashTable.get(strs[2]))

        val expectedActions = listOf("bucket0", "insert0:0", "bucket0", "insert0:1", "bucket0", "insert0:2",
        "bucket0", "removed0:1", "bucket0", "found0:0", "bucket0", "found0:null", "bucket0", "found0:1")

        assertEquals(expectedActions, hashTable.actions)
    }
}