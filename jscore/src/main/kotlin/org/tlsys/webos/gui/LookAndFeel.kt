package org.tlsys.webos.gui

import org.w3c.dom.HTMLElement

interface WindowsControlManager {
    val layout:HTMLElement
}

interface LookAndFeel {
    fun createWindow(manager:WindowsControlManager,controller: WindowController): Window
}

abstract class WindowController() {
    internal var _window: Window? = null
    val window: Window get() = _window!!
    open fun onRezie() {
    }

    open fun closeRequest(): Boolean {
        return true
    }

    open fun onCreated() {
    }
}

interface WindowTitle {
    var caption: String
    var visible: Boolean
}

abstract class Window {

    private var _controller: WindowController? = null
    val controller: WindowController get() = _controller!!

    open protected fun created(controller: WindowController) {
        _controller = controller
        controller._window = this
        controller.onCreated()
    }

    abstract val content: HTMLElement
    abstract val title: WindowTitle

    abstract var width: Int
    abstract var height: Int
    abstract var x: Int
    abstract var y: Int
    abstract var visible: Boolean
    abstract var border: Boolean

    open fun close() {
        if (controller.closeRequest())
            onClosed()
    }

    abstract protected fun onClosed()
}