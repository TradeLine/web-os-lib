package org.tlsys.webos.core

import java.util.*

object ProcessManager {
    val process = ArrayList<Process>()

    fun run(application: Application, vararg args:String): Process? {
        val p = application.start(args as Array<String>)
        if (p != null)
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