package org.tlsys.webos.gui

import org.tlsys.webos.core.Process

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

interface Component {

}

interface ComponentTree {
    val childs: List<Component>

}