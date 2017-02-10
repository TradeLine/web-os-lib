package org.tlsys.io

abstract class Reader {

    companion object {
    }

    fun readBoolean() = read().toInt() == 42
    abstract fun read(): Byte
    fun byte(): Byte = read()
    fun char(): Char = short().toChar()
    fun short(): Short {
        val ch1 = read().toInt()
        val ch2 = read().toInt()
        if ((ch1 or ch2) < 0)
            throw RuntimeException("EOF")
        return ((ch1 shl 8) + (ch2 shl 0)).toShort()
    }

    fun int(): Int {
        val ch1 = read().toInt()
        val ch2 = read().toInt()
        val ch3 = read().toInt()
        val ch4 = read().toInt()
        if ((ch1 or ch2 or ch3 or ch4) < 0)
            throw RuntimeException("EOF")
        return ((ch1 shl 24) + (ch2 shl 16) + (ch3 shl 8) + (ch4 shl 0));
    }

    abstract fun float(): Float
    fun long(): Long {
        val v0 = read().toInt()
        val v1 = read().toInt()
        val v2 = read().toInt()
        val v3 = read().toInt()
        val v4 = read().toInt()
        val v5 = read().toInt()
        val v6 = read().toInt()
        val v7 = read().toInt()

        return ((v0.toLong() shl 56) +
                ((v1 and 255).toLong() shl 48) +
                ((v2 and 255).toLong() shl 40) +
                ((v3 and 255).toLong() shl 32) +
                ((v4 and 255).toLong() shl 24) +
                ((v5 and 255) shl 16) +
                ((v6 and 255) shl 8) +
                ((v7 and 255) shl 0))
    }

    abstract fun double(): Double
    fun string(): String {
        val sb = StringBuilder()
        val size = int()
        for (i in 0..size - 1) {
            sb.append(char())
        }
        return sb.toString()
    }

    fun obj(): DTO? {
        if (readBoolean()) {
            val id = int()
            val f = Objects.getFactoryById(id) ?: throw DTOFactoryNotFound(id)
            val o = f.read(this)
            return o
        } else {
            return null
        }
    }

    fun <T : DTO> readList(): List<T> {
        val size = int()
        val out = ArrayList<T>(size)
        for (f in 0..size - 1) {
            out.add(obj() as T)
        }

        return out
    }

    fun intOrNull() = if (readBoolean()) int() else null
    fun stringOrNull() = if (readBoolean()) string() else null
    fun floatOrNull() = if (readBoolean()) float() else null
    fun longOrNull() = if (readBoolean()) long() else null
    fun byteOrNull() = if (readBoolean()) byte() else null
}

class DTOFactoryNotFound constructor(val id: Int) : RuntimeException("DTO with ID $id not found!")