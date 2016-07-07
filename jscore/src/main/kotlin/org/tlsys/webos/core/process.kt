package org.tlsys.webos.core

import java.util.*

object ProcessManager {
    val process = ArrayList<Process>()

    fun run(application: Application): Process {
        val p = application.start()
        process += p
        return p
    }
}

abstract class Process {
    protected abstract fun stop()
    fun close() {
        stop()
        ProcessManager.process -= this
    }
}