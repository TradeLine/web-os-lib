package org.tlsys.webos.gui

interface GUIManager {
    val applications: List<GUIApplication<GUIApplicationController>>
    fun <C : GUIApplicationController> createApplication(controllerProvider: (GUIApplication<GUIApplicationController>) -> C): GUIApplication<C>
    fun activateWindow(window:GUIWindow<*,*>)
    val screenWidth:Int
    val screenHeight:Int
}