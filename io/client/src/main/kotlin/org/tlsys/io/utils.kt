package org.tlsys.io

fun DTO?.asByteArray(): ByteArray {
    val w = JSWriter()
    w.obj(this)
    return w.toByteArray()
}

fun <T> ByteArray.asDTO(): T {
    val r = JSReader(this)
    return r.obj() as T
}