package org.tlsys.io

abstract class Reader {

    companion object

    fun boolean() = read().toInt() == 42
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
        val ch1 = read().toInt() and 0xFF
        val ch2 = read().toInt() and 0xFF
        val ch3 = read().toInt() and 0xFF
        val ch4 = read().toInt() and 0xFF

        if ((ch1 or ch2 or ch3 or ch4) < 0)
            throw RuntimeException("EOF")
        val out = ((ch1 shl 24) + (ch2 shl 16) + (ch3 shl 8) + (ch4 shl 0))
        return out
    }

    abstract fun float(): Float
    fun long(): Long {
        val v0 = read().toInt() and 0xFF
        val v1 = read().toInt() and 0xFF
        val v2 = read().toInt() and 0xFF
        val v3 = read().toInt() and 0xFF
        val v4 = read().toInt() and 0xFF
        val v5 = read().toInt() and 0xFF
        val v6 = read().toInt() and 0xFF
        val v7 = read().toInt() and 0xFF

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
        for (i in 0 until size) {
            sb.append(char())
        }
        return sb.toString()
    }

    fun obj(): DTO? {
        if (boolean()) {
            val id = int()
            val f = Objects.getFactoryById(id) ?: throw DTOFactoryNotFound(id)
            val o = f.read(this)
            return o
        } else {
            return null
        }
    }

    fun byteArray() = Array(int()) {
        byte()
    }

    fun <T : DTO> readList(): List<T> {
        val size = int()
        val out = ArrayList<T>(size)
        for (f in 0 until size) {
            out.add(obj() as T)
        }

        return out
    }

    fun intOrNull() = if (boolean()) int() else null
    fun stringOrNull() = if (boolean()) string() else null
    fun floatOrNull() = if (boolean()) float() else null
    fun longOrNull() = if (boolean()) long() else null
    fun byteOrNull() = if (boolean()) byte() else null

    fun booleanOrNull(): Boolean? = if (boolean()) boolean() else null


}

class DTOFactoryNotFound constructor(val id: Int) : RuntimeException("DTO with ID $id not found!")