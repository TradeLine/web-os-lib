package org.tlsys.io

import java.util.*

class JReader : Reader {
    private val body: ByteArray

    companion object {
        fun fromBase64(str: String) = JReader(Base64.getDecoder().decode(str))
    }

    constructor(bytes: ByteArray) {
        body = bytes
    }

    /*
    constructor(body: String) {
        TODO()

        //this.body = body
    }
    */

    override fun double(): Double {
        val v1 = int().toLong() and 0xFFFFFFFF shl 32
        val v2 = int().toLong() and 0xFFFFFFFF
        val r = v1 + v2
        return java.lang.Double.longBitsToDouble(r)
    }

    private var _cursor: Int = 0
    override fun read(): Byte = body[_cursor++]

    override fun float(): Float {
        return java.lang.Float.intBitsToFloat(int())
    }
}