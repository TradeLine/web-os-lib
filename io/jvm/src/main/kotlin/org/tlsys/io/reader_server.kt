package org.tlsys.io


class JReader(bytes: ByteArray) : org.tlsys.io.Reader() {
    private val body: ByteArray = bytes

    companion object {
        fun fromBase64(str: String) = JReader(java.util.Base64.getDecoder().decode(str))
        fun fromBinaryString(str: String):JReader {
            val r = JReader(ByteArray(str.length) {
                val d =str[it].toByte()
                d
            })
            return r
        }
    }

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