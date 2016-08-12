package org.tlsys.webos.gui

interface Menu {
    var x:Int
    var y:Int
    val width:Int
    val height:Int

    val parent: Menu?
    val manager: GUIManager

    val items: List<MenuItem>
    fun createTextItem(creator: (TextMenuItem) -> Unit): TextMenuItem
    fun createSubMenu(creator: (SubMenuMenuItem) -> Unit): SubMenuMenuItem
    fun remove(item: MenuItem)
    fun remove(index: Int)

    fun close()
}

interface MenuItem {
    var text: String
}

interface TextMenuItem:MenuItem {
    var clickListener:((TextMenuItem)->Boolean)?
}

interface SubMenuMenuItem : Menu, MenuItem {

}