package org.tlsys.io

import java.util.*

private val package_name = "org.tlsys.io"

class StringDTO(var value: String) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = StringDTO(reader.readString())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeString((obj as StringDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.StringDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID
}

class BooleanDTO(val value: Boolean) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = BooleanDTO(reader.readBoolean())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeBoolean((obj as BooleanDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.BooleanDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID
}

class CharDTO(val value: Char) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = CharDTO(reader.readChar())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeChar((obj as CharDTO).value)
        }

        internal var DTO_ID: Int = Objects.calcId("$package_name.CharDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID
}

class ByteDTO(val value: Byte) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = ByteDTO((reader.read() - 127).toByte())

        override fun write(obj: DTO, writer: Writer) {

            writer.write((obj as ByteDTO).value + 127)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.ByteDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID
}

class ShortDTO(val value: Short) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = ShortDTO(reader.readShort())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeShort((obj as ShortDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.ShortDTO")
    }

    override val DTO_ID: Int
        get() = DTO_ID
}

class IntDTO(val value: Int) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = IntDTO(reader.readInt())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeInt((obj as IntDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.IntDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID


    override fun toString(): String {
        return "IntDTO(value=$value)"
    }


}

class LongDTO(var value: Long) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = LongDTO(reader.readLong())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeLong((obj as LongDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.LongDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID

    override fun toString(): String {
        return "LongDTO(value=$value)"
    }


}

class FloatDTO(var value: Float) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = FloatDTO(reader.readFloat())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeFloat((obj as FloatDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.FloatDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID

}

class DoubleDTO(var value: Double) : DTO {

    internal companion object FACTORE : DTOFactory {
        override fun read(reader: Reader): DTO = DoubleDTO(reader.readDouble())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeDouble((obj as DoubleDTO).value)
        }

        internal val DTO_ID: Int = Objects.calcId("$package_name.DoubleDTO")
    }

    override val DTO_ID: Int
        get() = FACTORE.DTO_ID

    override fun toString(): String {
        return "DoubleDTO(value=$value)"
    }


}

class ListDTO<T : DTO>(val values: List<DTO?>) : DTO {
    companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = ListDTO<DTO>(reader.readList<DTO>())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeList((obj as ListDTO<DTO>).values)
        }

        val DTO_ID: Int = Objects.calcId("$package_name.ListDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

object OBJECT_PRIMITIVS {
    fun reg() {
        Objects.regFactory(StringDTO.DTO_ID, StringDTO)
        Objects.regFactory(BooleanDTO.DTO_ID, BooleanDTO)
        Objects.regFactory(CharDTO.DTO_ID, CharDTO)
        Objects.regFactory(ByteDTO.DTO_ID, ByteDTO)
        Objects.regFactory(ShortDTO.DTO_ID, ShortDTO)
        Objects.regFactory(IntDTO.DTO_ID, IntDTO)
        Objects.regFactory(LongDTO.DTO_ID, LongDTO)
        Objects.regFactory(FloatDTO.DTO_ID, FloatDTO)
        Objects.regFactory(DoubleDTO.DTO_ID, DoubleDTO)
    }
}
fun Array<Long>.toDTO() = Array(size) {
    LongDTO(this[it])
}

fun Array<String>.toDTO() = Array(size) {
    StringDTO(this[it])
}

fun Array<Int>.toDTO() = Array(size) {
    IntDTO(this[it])
}

fun Array<LongDTO>.fromDTO() = Array(size) {
    this[it].value
}

fun Array<StringDTO>.fromDTO() = Array(size) {
    this[it].value
}

fun Array<IntDTO>.fromDTO() = Array(size) {
    this[it].value
}