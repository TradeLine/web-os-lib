package org.tlsys.io

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array

class JSReader : Reader {
    companion object {
        fun fromBase64(data: String): JSReader = JSReader((js("atob")(data)))
    }

    private val body: ByteArray

    constructor(data: ByteArray) {
        this.body = data
    }

    /*
    override val cursor: Int
        get() = _cursor
    */

    //override fun readChar(): Char = read().toChar()

    /*
    override fun readShort(): Short {
        val ch1 = read()
        val ch2 = read()
        if ((ch1 or ch2) < 0)
            throw RuntimeException("EOFException");
        return ((ch1 shl 8) + (ch2 shl 0)).toShort();
    }
    */

    override fun double(): Double {
        val v2 = int().toLong() and 0xFFFFFFFF
        val v1 = int().toLong() and 0xFFFFFFFF
//val r = (v1.toLong() shl 24) + v2
        val i = Int32Array(2)
        i.asDynamic()[0] = v1
        i.asDynamic()[1] = v2
        //js("i[0]=v1")
        //js("i[1]=v2")
        val f = Float64Array(i.buffer)
        return f.asDynamic()[0]
        //return js("f[0]")
    }

    /*
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
                ((v6 and 255) shl 8) +
                ((v7 and 255) shl 0))
    }
*/
    private var _cursor = 0

    override fun float(): Float {
        val iArray = Int32Array(1)
        iArray.asDynamic()[0] = int()
        val fArray = Float32Array(iArray.buffer)
        return fArray.asDynamic()[0]
    }

    override fun read(): Byte = body[_cursor++]
/*
    override fun readInt(): Int {
        val ch1 = read().toInt();
        val ch2 = read().toInt();
        val ch3 = read().toInt();
        val ch4 = read().toInt();
        if ((ch1 or ch2 or ch3 or ch4) < 0)
            throw RuntimeException("EOFException");
        return ((ch1 shl 24) + (ch2 shl 16) + (ch3 shl 8) + (ch4 shl 0));
    }


    override fun readObject(): DTO? {
        val g = super.readObject()
        return g
    }
    */
}