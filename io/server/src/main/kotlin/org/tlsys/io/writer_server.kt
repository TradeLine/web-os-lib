package org.tlsys.io

import java.io.ByteArrayOutputStream

class JWriter(work: ((JWriter) -> Unit)? = null) : org.tlsys.io.Writer() {
    private val data = ByteArrayOutputStream()

    override fun toByteArray() = data.toByteArray()!!

    override fun double(v: Double) {
        val l = java.lang.Double.doubleToLongBits(v)
        int((l ushr 32).toInt())
        int((l and 0xFFFFFFFF).toInt())
    }

    override fun write(value: Byte) {
        data.write(value.toInt())
    }

    override fun float(value: Float) {
        return int(java.lang.Float.floatToIntBits(value))
    }

    override fun toString(): String = TODO()

    override fun obj(obj: DTO?) {
        try {
            super.obj(obj)
        } catch (e: Throwable) {
            e.printStackTrace()
            throw RuntimeException("Can't write ${obj?.javaClass?.name}", e)
        }
    }

    init {
        if (work != null)
            work(this)
    }
}

fun Writer.toBase64(): String = java.util.Base64.getEncoder().encodeToString(toByteArray())