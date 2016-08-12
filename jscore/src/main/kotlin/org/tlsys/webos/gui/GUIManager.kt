package org.tlsys.webos.gui

interface GUIManager {
    val applications: List<GUIApplication<GUIApplicationController>>
    fun <C : GUIApplicationController> createApplication(controllerProvider: (GUIApplication<GUIApplicationController>) -> C): GUIApplication<C>
    fun activateWindow(window: GUIWindow<*, *>)
    val screenWidth: Int
    val screenHeight: Int
    fun createMenu(x: Int, y: Int, creator: (Menu) -> Unit): Menu

    fun <T : PlaneController> createPlaneH(creator: (GlobalPlaneHorisantal<*>) -> T): GlobalPlaneHorisantal<T>
    fun destroyPlane(plane: GlobalPlane<*>)
}