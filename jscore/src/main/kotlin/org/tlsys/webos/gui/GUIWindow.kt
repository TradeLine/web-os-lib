package org.tlsys.webos.gui

import org.w3c.dom.HTMLElement

interface GUIWindow<W : GUIWindowController, A : GUIApplicationController> {
    val application: GUIApplication<A>
    val controller: W
    var title: String
    var borderVisible: Boolean
    val content: HTMLElement
    var x: Int
    var y: Int
    var width: Int
    var height: Int
    var visible:Boolean

    fun position(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun size(width: Int, height: Int) {
        this.width = width
        this.height = height
    }
}

interface GUIWindowController {
    val window: GUIWindow<GUIWindowController, GUIApplicationController>
}