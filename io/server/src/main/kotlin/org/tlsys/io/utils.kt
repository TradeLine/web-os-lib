package org.tlsys.io

fun DTO?.asByteArray(): ByteArray {
    val w = JWriter()
    w.obj(this)
    return w.toByteArray()
}

fun <T> ByteArray.asDTO(): T {
    val r = JReader(this)
    return r.obj() as T
}