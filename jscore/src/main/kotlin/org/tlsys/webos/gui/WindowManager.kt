package org.tlsys.webos.gui

import org.tlsys.webos.core.Process
import org.w3c.dom.HTMLElement
/*
interface WindowManager {
    val applications: List<GUIApplication<*>>
    fun <T : GUIApplicationController> createApplication(controller: T): GUIApplication<T>
}

abstract class GUIApplicationController {

}


abstract class GUIApplication<T : GUIApplicationController> {
    abstract val manager: WindowManager
    abstract val windows: List<Window<WindowController>>
    protected abstract val _windows: MutableList<Window<WindowController>>

    internal fun onNewWindow(window: GUIWindow<*, T>) {
        window as GUIWindow<WindowController, T>
        _windows += window
    }

    internal fun onCloseWindow(window: GUIWindow<*, T>) {
        window as GUIWindow<WindowController, T>
        _windows -= window
    }

    abstract fun <C : WindowController> createWindow(controller: C): GUIWindow<C, T>
}


abstract class GUIWindow<C : WindowController, T : GUIApplicationController>(val application: GUIApplication<T>) : Window<C>() {
    private lateinit var _controller: C
    val controller: C get() = _controller!!

    open protected fun created(controller: C) {
        _controller = controller
        controller._window = this
        controller.onCreated()
        application.onNewWindow(this)
    }

    override fun onClosed() {
        application.onCloseWindow(this)
    }
}

//=============================//

abstract class WindowController() {
    lateinit var _window: Window<*>
    val window: Window<*> get() = _window!!
    open fun onRezie() {
    }

    open fun closeRequest(): Boolean {
        return true
    }

    open fun onCreated() {
    }
}

//=============================//

            */

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