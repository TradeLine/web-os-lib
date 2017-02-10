package org.tlsys.io

import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.*

class JWriter : Writer {
    val data = ByteArrayOutputStream()

    constructor(work: ((JWriter) -> Unit)? = null) {
        if (work != null)
            work(this)
    }

    fun writeToStream(stream: OutputStream) {
        stream.write(toByteArray())
    }

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
        } catch(e: Throwable) {
            throw RuntimeException("Can't write ${obj?.javaClass?.name}", e)
        }
    }
}

fun Writer.toBase64(): String = Base64.getEncoder().encodeToString(toByteArray())