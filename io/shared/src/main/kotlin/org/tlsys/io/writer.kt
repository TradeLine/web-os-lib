package org.tlsys.io

interface Writer {
    fun writeBoolean(value: Boolean) {
        write(if (value) 42 else 38)
    }

    fun write(v: Int)
    fun writeChar(v: Char)
    fun writeShort(v: Short)
    fun writeInt(v: Int)
    fun writeFloat(v: Float)
    fun writeLong(v: Long)
    fun writeDouble(v: Double)
    fun writeObject(obj: DTO?) {
        if (obj == null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeInt(obj.DTO_ID)
            Objects.getFactoryById(obj.DTO_ID)!!.write(obj, this)
        }
    }

    fun <T : DTO> writeList(list: List<T?>) {
        writeInt(list.size)
        for (it in list)
            writeObject(it)
    }

    fun <T : DTO> writeArray(list: Array<T?>) {
        writeInt(list.size)
        for (it in list)
            writeObject(it)
    }

    fun <T : DTO> writeArray2(list: Array<T>) {
        writeInt(list.size)
        for (it in list)
            writeObject(it)
    }

    fun writeString(str: String) {
        writeInt(str.length)
        for (i in 0..str.length - 1)
            write(str[i].toInt())
    }
}