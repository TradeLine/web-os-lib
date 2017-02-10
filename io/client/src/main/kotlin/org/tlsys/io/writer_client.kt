package org.tlsys.io

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array

class JSWriter : Writer {

    override fun toByteArray() = ByteArray(out.size) {
        out[it]
    }

    /*
    override val cursor: Int
        get() = out.length


    override fun writeChar(v: Char) {
        write(v.toInt())
    }

    override fun writeShort(v: Short) {
        val h = v.toInt()
        write((h ushr 8) and 0xFF);
        write((h ushr 0) and 0xFF);
    }
*/
    constructor(work: ((JSWriter) -> Unit)? = null) {
        if (work != null)
            work(this)
    }

    override fun double(v: Double) {
        val f = Float64Array(1)
        f.asDynamic()[0] = v
        val i = Int32Array(f.buffer)
        int(i.asDynamic()[1])
        int(i.asDynamic()[0])

    }

    /*
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
    */
    private var out = ArrayList<Byte>()

    override fun write(byte: Byte) {
        out.add(byte)
    }

    /*
    private fun asByteArray(): ByteArray {
        val data = ByteArray(out.length)
        for (i in 0..out.length - 1) {
            data[i] = out[0].toByte()
        }
        return data
    }

    */
/*
    override fun writeInt(v: Int) {
        write(((v ushr 24) and 0xFF))
        write(((v ushr 16) and 0xFF))
        write(((v ushr 8) and 0xFF))
        write(((v ushr 0) and 0xFF))
    }
*/
    override fun float(v: Float) {
        val f = Float32Array(1)
        f.asDynamic()[0] = v
        val i = Int32Array(f.buffer)
        int(i.asDynamic()[0])
    }
}

fun Writer.toBase64(): String = js("btoa")(toByteArray())
