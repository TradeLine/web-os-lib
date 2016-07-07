package org.tlsys.webos.core

interface Application {
    val name:String
    fun start():Process
}
