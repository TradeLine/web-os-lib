package org.tlsys.io

import org.junit.Assert.*
import org.junit.Test

class ByteTest {

    @Test
    fun test() {
        val w = JWriter()
        w.writeFloat(100500.0f)
        val s = w.toString()
        val d = w.toByteArray()
        assertEquals(100500.0f, JReader(d).readFloat())
        assertEquals(100500.0f, JReader(s).readFloat())
    }
}
