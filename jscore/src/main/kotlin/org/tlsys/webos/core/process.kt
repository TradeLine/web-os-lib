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

    private var _running: Boolean = true
    val running: Boolean
        get() = _running

    fun close() {
        stop()
        ProcessManager.process -= this
        _running = false
    }
}