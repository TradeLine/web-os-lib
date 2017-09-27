package org.tlsys.io

interface DTO {
    val DTO_ID: Int
}

interface DTOFactory {
    val DTO_ID: Int
    fun read(reader: Reader): DTO
    fun write(obj: DTO, writer: Writer)
}

object Objects {
    private val factories = HashMap<Int, DTOFactory>()

    fun regFactory(factory: DTOFactory) {
        factories.put(factory.DTO_ID, factory)
    }

    internal fun getFactoryById(id: Int): DTOFactory? = factories[id]

    fun calcId(className: String): Int {
        return (0 until className.length).sumBy { className[it].toInt() * it }
    }
}