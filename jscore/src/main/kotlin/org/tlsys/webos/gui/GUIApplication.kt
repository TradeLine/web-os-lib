package org.tlsys.webos.gui

interface GUIApplication<C : GUIApplicationController> {
    val controller: C
    val windows: List<GUIWindow<GUIWindowController, C>>
    val manager: GUIManager

    fun <W : GUIWindowController> createWindow(controlProvider: (GUIWindow<GUIWindowController, C>) -> W): GUIWindow<W, C>
}

interface GUIApplicationController {
    val application: GUIApplication<GUIApplicationController>
}