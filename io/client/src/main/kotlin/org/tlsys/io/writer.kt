package org.tlsys.io

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array

class JSWriter : Writer {
    override fun writeChar(v: Char) {
        write(v.toInt())
    }

    override fun writeShort(v: Short) {
        val h = v.toInt()
        write((h ushr 8) and 0xFF);
        write((h ushr 0) and 0xFF);
    }

    constructor(work: ((JSWriter) -> Unit)? = null) {
        if (work != null)
            work(this)
    }

    override fun writeDouble(v: Double) {
        val f = Float64Array(1)
        js("f[0]=v")
        val i = Int32Array(f.buffer)
        val v1 = js("i[0]")
        val v2 = js("i[1]")

        writeInt(v2)
        writeInt(v1)

    }

    override fun writeLong(v: Long) {
        write((v ushr 56).toByte().toInt())
        write((v ushr 48).toByte().toInt())
        write((v ushr 40).toByte().toInt())
        write((v ushr 32).toByte().toInt())
        write((v ushr 24).toByte().toInt())
        write((v ushr 16).toByte().toInt())
        write((v ushr  8).toByte().toInt())
        write((v ushr  0).toByte().toInt())
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

    override fun writeFloat(v: Float) {
        val f = Float32Array(1)
        js("f[0]=v")
        val i = Int32Array(f.buffer)
        writeInt(js("i[0]"))
    }

    override fun toString(): String = out
}
