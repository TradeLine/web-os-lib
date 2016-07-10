package org.tlsys.webos.gui

import org.tlsys.webos.core.Process
import org.w3c.dom.HTMLElement

interface WindowManager {
    val applications: List<GUIApplication>
}

abstract class GUIApplication {
    abstract val manager: WindowManager
    abstract val windows: List<Window>
    protected abstract val _windows: MutableList<Window>

    internal fun onNewWindow(window: GUIWindow) {
        _windows += window
    }

    internal fun onCloseWindow(window: GUIWindow) {
        _windows -= window
    }
}

abstract class GUIWindow(val application: GUIApplication) : Window() {

    override fun created(controller: WindowController) {
        super.created(controller)
        application.onNewWindow(this)
    }

    override fun onClosed() {
        application.onCloseWindow(this)
    }
}

interface Controller {
    val dom: HTMLElement
}

fun HTMLElement.useControl(controller: Controller) {
    val self = this
    js("self.CONTROL=controller")
}

val HTMLElement.con: Controller?
    get() {
        val self = this
        return js("self.CONTROL")
    }