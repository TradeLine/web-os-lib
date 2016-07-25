package org.tlsys.webos.gui

interface GlobalPlane<T : PlaneController> {
    val controller: T
}

interface GlobalPlaneHorisantal<T : PlaneController> : GlobalPlane<T> {
    var height: Int
    var type: Type

    enum class Type {
        TOP, BOTTOM
    }
}

interface PlaneController {
    fun onResize() {
    }

    val plane: GlobalPlane<*>
}