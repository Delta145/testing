package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HashTest {
    private val size = 10
    var hashTable: OpenAddressHashTable<String, String> = OpenAddressHashTable(size)
    @BeforeEach
    fun initTable() {
        hashTable = OpenAddressHashTable(size)
    }

    @Test
    fun `put and get data from hash table`() {
        val name = "Georgii"
        val surname = "Savin"
        hashTable.put("name", name)
        hashTable.put("surname", surname)

        assertEquals(name, hashTable.get("name"))
        assertEquals(surname, hashTable.get("surname"))
    }

    @Test
    fun `check data absence after delete from table`() {
        val name = "Georgii"
        hashTable.put("name", name)
        assertEquals(name, hashTable.get("name"))
        hashTable.delete("name")
        assertEquals(null, hashTable.get("name"))
    }

    @Test
    fun `check value replacement`() {
        val name = "Georgii"
        val newName = "Georgij"
        hashTable.put("name", name)
        hashTable.put("name", newName)
        assertEquals(newName, hashTable.get("name"))
    }

    @Test
    fun `check work of put and get with 100 percent collision`() {
        // collision is unavoidable if we have more elements than the table size
        val strs = (1..size + 1).asIterable().map { i -> "str$i" }
        strs.forEach { s -> hashTable.put(s, s) }
        strs.forEach { s -> assertEquals(s, hashTable.get(s)) }
    }

    @Test
    fun `check work with collisions after delete`() {
        val strsToDelete = (1..size + 1).asIterable().map { i -> "strToDelete$i" }
        val strsToCheck = (1..size).asIterable().map { i -> "strToCheck$i" }

        strsToDelete.plus(strsToCheck).forEach { s -> hashTable.put(s, s) }
        strsToDelete.forEach { s -> hashTable.delete(s) }
        strsToCheck.forEach { s -> assertEquals(s, hashTable.get(s)) }
    }

    @Test
    fun `check correct delete in the middle of sequence`() {
        hashTable = OpenAddressHashTable(1)
        val strs = (1..3).asIterable().map { i -> "str$i" }

        strs.forEach { s -> hashTable.put(s, s) }
        hashTable.delete(strs[1])
        assertEquals(strs[0], hashTable.get(strs[0]))
        assertEquals(strs[2], hashTable.get(strs[2]))
    }
}