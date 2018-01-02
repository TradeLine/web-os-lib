package org.tlsys.io


private val package_name = "org.tlsys.io"

class StringDTO(var value: String) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = StringDTO(reader.string())

        override fun write(obj: DTO, writer: Writer) {
            writer.string((obj as StringDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.StringDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

class BooleanDTO(val value: Boolean) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = BooleanDTO(reader.boolean())

        override fun write(obj: DTO, writer: Writer) {
            writer.boolean((obj as BooleanDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.BooleanDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

class CharDTO(val value: Char) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = CharDTO(reader.char())

        override fun write(obj: DTO, writer: Writer) {
            writer.char((obj as CharDTO).value)
        }

        override var DTO_ID: Int = Objects.calcId("$package_name.CharDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

class ByteDTO(val value: Byte) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = ByteDTO((reader.byte()).toByte())

        override fun write(obj: DTO, writer: Writer) {

            writer.byte((obj as ByteDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.ByteDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

class ShortDTO(val value: Short) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = ShortDTO(reader.short())

        override fun write(obj: DTO, writer: Writer) {
            writer.short((obj as ShortDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.ShortDTO")
    }

    override val DTO_ID: Int
        get() = DTO_ID
}

class IntDTO(val value: Int) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = IntDTO(reader.int())

        override fun write(obj: DTO, writer: Writer) {
            writer.int((obj as IntDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.IntDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID


    override fun toString(): String {
        return "IntDTO(value=$value)"
    }

}

class LongDTO(var value: Long) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = LongDTO(reader.long())

        override fun write(obj: DTO, writer: Writer) {
            writer.long((obj as LongDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.LongDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID

    override fun toString(): String {
        return "LongDTO(value=$value)"
    }

}

class FloatDTO(var value: Float) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = FloatDTO(reader.float())

        override fun write(obj: DTO, writer: Writer) {
            writer.float((obj as FloatDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.FloatDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

class DoubleDTO(var value: Double) : DTO {

    internal companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = DoubleDTO(reader.double())

        override fun write(obj: DTO, writer: Writer) {
            writer.double((obj as DoubleDTO).value)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.DoubleDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID

    override fun toString(): String {
        return "DoubleDTO(value=$value)"
    }
}

class ListDTO<T : DTO> : DTO, Iterable<T> {
    override fun iterator(): Iterator<T> = values.iterator()

    val values: List<T>

    constructor(values: List<T>) {
        this.values = values
    }

    constructor(size: Int, f: (Int) -> T) {
        val list = ArrayList<T>(size)
        (0 until size).mapTo(list) { f(it) }
        this.values = list
    }

    constructor(values: Array<T>) {
        val list = ArrayList<T>(values.size)
        (0 until values.size).mapTo(list) { values[it] }
        this.values = list
    }

    val size: Int
        get() = values.size

    operator fun get(index: Int): T = values[index]

    companion object FACTORY : DTOFactory {
        override fun read(reader: Reader): DTO = ListDTO(reader.readList<DTO>())

        override fun write(obj: DTO, writer: Writer) {
            writer.writeList((obj as ListDTO<DTO>).values)
        }

        override val DTO_ID: Int = Objects.calcId("$package_name.ListDTO")
    }

    override val DTO_ID: Int
        get() = FACTORY.DTO_ID
}

object OBJECT_PRIMITIVS {
    fun reg() {
        Objects.regFactory(StringDTO)
        Objects.regFactory(BooleanDTO)
        Objects.regFactory(CharDTO)
        Objects.regFactory(ByteDTO)
        Objects.regFactory(ShortDTO)
        Objects.regFactory(IntDTO)
        Objects.regFactory(LongDTO)
        Objects.regFactory(FloatDTO)
        Objects.regFactory(DoubleDTO)
        Objects.regFactory(ListDTO)
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

fun ByteArray.toDTO() = ListDTO(size) { ByteDTO(this[it]) }
fun ListDTO<ByteDTO>.toTypedArray() = ByteArray(size) {
    values[it].value
}

val Long?.dto: LongDTO?
    get() = if (this == null) null else LongDTO(this)

val Float?.dto: FloatDTO?
    get() = if (this == null) null else FloatDTO(this)

val Int?.dto: IntDTO?
    get() = if (this == null) null else IntDTO(this)

val String?.dto: StringDTO?
    get() = if (this == null) null else StringDTO(this)

val Boolean?.dto: BooleanDTO?
    get() = if (this == null) null else BooleanDTO(this)

val <T : DTO> List<T>?.dto: ListDTO<T>?
    get() = if (this == null) null else ListDTO(this)