package org.tlsys.webos.gui

import org.tlsys.webos.core.Process
import org.w3c.dom.HTMLElement

interface WindowManager {
    val applications: List<WindowApplication>
}

interface WindowTitle {
    var caption: String
    var visible: Boolean
}

interface Window {
    val application: WindowApplication
    val title: WindowTitle
    var width: Int
    var height: Int
    var x: Int
    var y: Int
    var visible: Boolean
    var border: Boolean
    val content: HTMLElement

    fun onOpen() {
    }

    fun onClose() {
    }

    fun Resize() {
    }
}

interface WindowApplication {
    val manager: WindowManager
    val windows: List<Window>
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