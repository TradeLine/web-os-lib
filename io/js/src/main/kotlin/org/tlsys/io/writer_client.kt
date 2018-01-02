package org.tlsys.io

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array

class JSWriter(work: ((JSWriter) -> Unit)? = null) : Writer() {

    override fun toByteArray() = ByteArray(out.size) {
        out[it]
    }

    override fun double(v: Double) {
        val f = Float64Array(1)
        f.asDynamic()[0] = v
        val i = Int32Array(f.buffer)
        int(i.asDynamic()[1])
        int(i.asDynamic()[0])

    }

    private var out = ArrayList<Byte>()

    override fun write(byte: Byte) {
        out.add(byte)
    }

    override fun float(v: Float) {
        val f = Float32Array(1)
        f.asDynamic()[0] = v
        val i = Int32Array(f.buffer)
        int(i.asDynamic()[0])
    }

    init {
        if (work != null)
            work(this)
    }
}

fun Writer.toBase64(): String = js("btoa")(toByteArray())
