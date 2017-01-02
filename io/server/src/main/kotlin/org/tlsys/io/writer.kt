package org.tlsys.io

import java.io.OutputStream
import java.util.logging.Logger

class JWriter : Writer {
    override val cursor: Int
        get() = out.length

    override fun writeChar(v: Char) {
        write(v.toInt())
    }


    override fun writeShort(v: Short) {
        val h = v.toInt()
        write((h ushr 8) and 0xFF)
        write((h ushr 0) and 0xFF)
    }

    constructor(work: ((JWriter) -> Unit)? = null) {
        if (work != null)
            work(this)
    }

    fun writeToStream(stream: OutputStream) {
        stream.write(out.toByteArray())
    }

    override fun toByteArray() = out.toByteArray()

    override fun writeDouble(v: Double) {
        val l = java.lang.Double.doubleToLongBits(v);
        writeInt((l ushr 32).toInt())
        writeInt((l and 0xFFFFFFFF).toInt())
    }

    override fun writeLong(v: Long) {
        write((v ushr 56).toByte().toInt())
        write((v ushr 48).toByte().toInt())
        write((v ushr 40).toByte().toInt())
        write((v ushr 32).toByte().toInt())
        write((v ushr 24).toByte().toInt())
        write((v ushr 16).toByte().toInt())
        write((v ushr 8).toByte().toInt())
        write((v ushr 0).toByte().toInt())
    }

    private var out: String = ""
    override fun write(byte: Int) {
        out += byte.toChar()
    }

    override fun writeInt(v: Int) {
        write(((v ushr 24) and 0xFF))
        write(((v ushr 16) and 0xFF))
        write(((v ushr 8) and 0xFF))
        write(((v ushr 0) and 0xFF))
    }

    override fun writeFloat(float: Float) {
        return writeInt(java.lang.Float.floatToIntBits(float))
    }

    override fun toString(): String = out

    override fun writeObject(obj: DTO?) {
        try {
            super.writeObject(obj)
        } catch(e: Throwable) {
            throw RuntimeException("Can't write ${obj?.javaClass?.name}", e)
        }
    }
}
