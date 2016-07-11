package org.tlsys.webos.gui

import org.w3c.dom.HTMLElement

/*
interface WindowsControlManager {
    val layout:HTMLElement
}

interface LookAndFeel {
    fun<T:WindowController> createWindow(manager:WindowsControlManager,controller: T): Window<T>
}



interface WindowTitle<T:WindowController> {
    var caption: String
    var visible: Boolean
}

abstract class Window<T:WindowController> {





    abstract val content: HTMLElement
    abstract val title: WindowTitle<T>

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
        */