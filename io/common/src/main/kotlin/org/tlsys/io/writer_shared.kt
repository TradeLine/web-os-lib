package org.tlsys.io

abstract class Writer {
    fun boolean(value: Boolean) {
        write(if (value) 42 else 38)
    }

    fun byte(v: Byte) {
        write(v)
    }

    abstract fun write(value: Byte)
    fun char(v: Char) = short(v.toShort())
    fun short(v: Short) {
        val h = v.toInt()
        write(((h ushr 8) and 0xFF).toByte())
        write(((h ushr 0) and 0xFF).toByte())
    }

    fun int(v: Int) {
        write(((v ushr 24) and 0xFF).toByte())
        write(((v ushr 16) and 0xFF).toByte())
        write(((v ushr 8) and 0xFF).toByte())
        write(((v ushr 0) and 0xFF).toByte())
    }

    abstract fun float(value: Float)
    fun long(v: Long) {
        write((v ushr 56).toByte())
        write((v ushr 48).toByte())
        write((v ushr 40).toByte())
        write((v ushr 32).toByte())
        write((v ushr 24).toByte())
        write((v ushr 16).toByte())
        write((v ushr 8).toByte())
        write((v ushr 0).toByte())
    }

    abstract fun double(v: Double)

    open fun obj(obj: DTO?) {
        if (obj === null) {
            boolean(false)
        } else {
            boolean(true)
            int(obj.DTO_ID)
            val factory = Objects.getFactoryById(obj.DTO_ID)
                    ?: throw RuntimeException("Can't find factory for ${obj.DTO_ID}")
            factory.write(obj, this)
        }
    }

    abstract fun toByteArray(): ByteArray

    fun toBinaryString(): String {
        var sb = StringBuilder()
        //var i = 0
        //println("===WRITE DATA===")
        for (c in toByteArray()) {
            //println("${i++}=>$c")
            sb.append(c.toChar())
        }
        //println("===WRITE DATA===")
        return sb.toString()
    }

    fun <T : DTO> writeList(list: List<T?>) {
        int(list.size)
        for (it in list)
            obj(it)
    }

    fun <T : DTO> writeArray(list: Array<T?>) {
        int(list.size)
        for (it in list)
            obj(it)
    }

    fun <T : DTO> writeArray2(list: Array<T>) {
        int(list.size)
        for (it in list)
            obj(it)
    }

    fun byteArray(array: ByteArray) {
        int(array.size)
        array.forEach {
            byte(it)
        }
    }

    fun string(str: String) {
        int(str.length)
        for (i in 0 until str.length)
            char(str[i])
    }

    fun intOrNull(value: Int?) {
        if (value === null) {
            boolean(false)
        } else {
            boolean(true)
            int(value)
        }
    }

    fun stringOrNull(value: String?) {
        if (value === null) {
            boolean(false)
        } else {
            boolean(true)
            string(value)
        }
    }

    fun floatOrNull(value: Float?) {
        if (value === null) {
            boolean(false)
        } else {
            boolean(true)
            float(value)
        }
    }

    fun longOrNull(value: Long?) {
        if (value === null) {
            boolean(false)
        } else {
            boolean(true)
            long(value)
        }
    }

    fun byteOrNull(value: Byte?) {
        if (value === null) {
            boolean(false)
        } else {
            boolean(true)
            byte(value)
        }
    }

    fun booleanOrNull(value: Boolean?) {
        if (value !== null) {
            boolean(true)
            boolean(value)
        } else {
            boolean(false)
        }
    }
}