package org.tlsys.webos.gui

interface Menu : MenuItem {
    val items: List<MenuItem>
    fun createItem(click: (MenuItem) -> Boolean): MenuItem
    fun createSubMenu(creator: (Menu) -> Unit)
    fun remove(item: MenuItem)
    fun remove(index: Int)
}

interface MenuItem {
    val parent: Menu
}
