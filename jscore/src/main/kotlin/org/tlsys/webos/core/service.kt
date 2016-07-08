package org.tlsys.webos.core

import java.util.*

class Service<P : Process>(val name: String, val description: String, val checkState: (P) -> Boolean, val creator: ((P) -> Unit) -> Unit) {
    var _state: Boolean = false
    val state: Boolean
        get() = _state

    private var process: P? = null

    private val waitLoad = ArrayList<(P) -> Unit>()

    private fun onLoad(process: P) {
        this.process = process
        ProcessManager.process += process!!
        _state = true
        for (f in waitLoad)
            f(process)
    }

    fun use(ready: (P) -> Unit) {
        if (process != null && !process!!.running) {
            _state = false
            process = null
        }

        if (state)
            ready(process!! as P)
        else {
            waitLoad += ready
            creator({ onLoad(it) })

        }
    }
}
