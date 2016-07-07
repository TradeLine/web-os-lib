package org.tlsys.webos.core

import java.util.*

class Service<P : Process>(val name: String, val description: String, val creator: (  (P) -> Unit  ) -> P) {
    val state: Boolean
        get() = process != null

    private var process: P? = null

    fun use(ready: (P) -> Unit) {
        if (state)
            ready(process!! as P)
        else {
            process = creator(ready)
            ProcessManager.process += process!!
        }
    }
}
