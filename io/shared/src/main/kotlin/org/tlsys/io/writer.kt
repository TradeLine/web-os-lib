package org.tlsys.io

interface Writer {
    fun writeBoolean(value: Boolean) {
        write(if (value) 42 else 38)
    }

    val cursor: Int
    fun byte(v: Byte) {
        write(v.toInt())
    }

    fun write(v: Int)
    fun writeChar(v: Char)
    fun writeShort(v: Short)
    fun writeInt(v: Int)
    fun writeFloat(v: Float)
    fun writeLong(v: Long)
    fun writeDouble(v: Double)
    fun writeObject(obj: DTO?) {
        if (obj === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeInt(obj.DTO_ID)
            val factory = Objects.getFactoryById(obj.DTO_ID) ?: throw RuntimeException("Can't find factory for ${obj.DTO_ID}")
            factory.write(obj, this)
        }
    }

    fun toByteArray(): ByteArray {
        val out = toString()
        val data = ByteArray(out.length)
        for (i in 0..out.length - 1) {
            data[i] = (out[i].toInt() - 127).toByte()
        }
        return data
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

    fun intOrNull(value: Int?) {
        if (value === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeInt(value)
        }
    }

    fun stringOrNull(value: String?) {
        if (value === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeString(value)
        }
    }

    fun floatOrNull(value: Float?) {
        if (value === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeFloat(value)
        }
    }

    fun longOrNull(value: Long?) {
        if (value === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            writeLong(value)
        }
    }

    fun byteOrNull(value: Byte?) {
        if (value === null) {
            writeBoolean(false)
        } else {
            writeBoolean(true)
            byte(value)
        }
    }
}