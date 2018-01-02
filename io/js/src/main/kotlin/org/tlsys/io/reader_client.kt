package org.tlsys.io

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int32Array

class JSReader(data: ByteArray) : Reader() {
    companion object {
        fun fromBase64(data: String): JSReader = JSReader((js("atob")(data)))
        fun fromBinaryString(str: String) =
                JSReader(ByteArray(str.length) {
                    str[it].toByte()
                })
    }

    private val body: ByteArray = data

    override fun double(): Double {
        val v2 = int().toLong() and 0xFFFFFFFF
        val v1 = int().toLong() and 0xFFFFFFFF
        val i = Int32Array(2)
        i.asDynamic()[0] = v1
        i.asDynamic()[1] = v2
        //js("i[0]=v1")
        //js("i[1]=v2")
        val f = Float64Array(i.buffer)
        return f.asDynamic()[0]
        //return js("f[0]")
    }

    private var _cursor = 0

    override fun float(): Float {
        val iArray = Int32Array(1)
        iArray.asDynamic()[0] = int()
        val fArray = Float32Array(iArray.buffer)
        return fArray.asDynamic()[0]
    }

    override fun read(): Byte = body[_cursor++]
}