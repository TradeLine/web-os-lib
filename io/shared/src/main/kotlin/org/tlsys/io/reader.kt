package org.tlsys.io

import java.util.*

interface Reader {
    fun readBoolean() = read() == 42

    val cursor: Int
    fun read(): Int
    fun byte(): Byte = read().toByte()
    fun readChar(): Char
    fun readShort(): Short
    fun readInt(): Int
    fun readFloat(): Float
    fun readLong(): Long
    fun readDouble(): Double
    fun readString(): String {
        var out = "";
        val size = readInt()
        for (i in 0..size - 1)
            out += read().toChar()
        return out
    }

    fun readObject(): DTO? {
        if (readBoolean()) {
            val id = readInt()
            val f = Objects.getFactoryById(id) ?: throw DTOFactoryNotFound(id)
            val o = f.read(this)
            return o
        } else {
            return null
        }
    }

    fun <T : DTO> readList(): List<T> {
        val size = readInt()
        val out = ArrayList<T>(size)
        for (f in 0..size - 1) {
            out.add(readObject() as T)
        }

        return out
    }

    fun intOrNull() = if (readBoolean()) readInt() else null
    fun stringOrNull() = if (readBoolean()) readString() else null
    fun floatOrNull() = if (readBoolean()) readFloat() else null
    fun longOrNull() = if (readBoolean()) readLong() else null
    fun byteOrNull() = if (readBoolean()) byte() else null
}

class DTOFactoryNotFound constructor(val id: Int) : RuntimeException("DTO with ID $id not found!")