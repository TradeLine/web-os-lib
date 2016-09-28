package org.tlsys.io

import java.io.EOFException

class JReader constructor(val body: String) : Reader {
    override val cursor: Int
        get() = _cursor

    override fun readChar(): Char = read().toChar()

    override fun readShort(): Short {
        val ch1 = read();
        val ch2 = read();
        if ((ch1 or ch2) < 0)
            throw EOFException();
        return ((ch1 shl 8) + (ch2 shl 0)).toShort();
    }

    override fun readDouble(): Double {
        val v1 = readInt().toLong()and 0xFFFFFFFF shl 32
        val v2 = readInt().toLong() and 0xFFFFFFFF
        val r = v1 + v2
        return java.lang.Double.longBitsToDouble(r)
    }

    override fun readLong(): Long {
        val v0 = read()
        val v1 = read()
        val v2 = read()
        val v3 = read()
        val v4 = read()
        val v5 = read()
        val v6 = read()
        val v7 = read()

        return ((v0.toLong() shl 56) +
                ((v1 and 255).toLong() shl 48) +
                ((v2 and 255).toLong() shl 40) +
                ((v3 and 255).toLong() shl 32) +
                ((v4 and 255).toLong() shl 24) +
                ((v5 and 255) shl 16) +
                ((v6 and 255) shl  8) +
                ((v7 and 255) shl  0))
    }

    private var _cursor: Int = 0
    override fun read(): Int {
        val b = body[_cursor++].toInt()
        return b
    }

    override fun readInt(): Int {
        val ch1 = read().toInt();
        val ch2 = read().toInt();
        val ch3 = read().toInt();
        val ch4 = read().toInt();
        if ((ch1 or ch2 or ch3 or ch4) < 0)
            throw EOFException();
        return ((ch1 shl 24) + (ch2 shl 16) + (ch3 shl 8) + (ch4 shl 0));
    }

    override fun readFloat(): Float {
        return java.lang.Float.intBitsToFloat(readInt())
    }
/*
    override fun readObject(): DTO? {
        if (readBoolean()) {
            val id = readInt()
            val f = DTO.LIST[id] ?: throw DTOFactoryNotFound(id)
            val o = f.create()
            o.read(this)
            return o
        } else {
            return null
        }
    }*/
}