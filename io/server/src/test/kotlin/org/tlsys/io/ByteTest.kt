package org.tlsys.io

import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class ByteTest {

    /*
    @Test
    fun test() {
        val w = JWriter()
        w.float(100500.0f)
        val s = w.toString()
        val d = w.toByteArray()
        assertEquals(100500.0f, JReader(d).float())
        assertEquals(100500.0f, JReader(s).float())
    }
    */


    @Test
    fun testByteArray() {
        val data = ByteArrayOutputStream()
        val s = DataOutputStream(data)
        val ss = JWriter()
        val b: Byte = (101).toByte()



        //ss.write(b)
        //s.write(b.toInt())

        ss.short(-9991)
        s.writeShort(-9991)

        val b1 = data.toByteArray()
        val b2 = ss.toByteArray()

        assertEquals(b1.size, b2.size)

        for (i in 0..s.size() - 1) {
            println("=>${b1[i]} ${b2[i]}")
        }


        val g = "РУС".toByteArray()



        writer {
            int(10)
            long(Long.MAX_VALUE)
            reader {
                assertEquals(int(), 10)
                assertEquals(long(), Long.MAX_VALUE)
            }
        }
        val TEXT = "Текст на русском языке"
        val w = JWriter()
        w.string(TEXT)
        val r = JReader(w.toByteArray())
        assertEquals(r.string(), TEXT)

    }
}


private fun writer(f: JWriter.() -> Unit) {
    val w = JWriter()
    w.f()
}

private fun JWriter.reader(f: JReader.() -> Unit) {
    val r = JReader(this.toByteArray())
    r.f()
}