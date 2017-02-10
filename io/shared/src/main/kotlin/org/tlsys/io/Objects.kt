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
    private val factorys = HashMap<Int, DTOFactory>()

    fun regFactory(factory: DTOFactory) {
        factorys.put(factory.DTO_ID, factory)
    }

    internal fun getFactoryById(id: Int): DTOFactory? = factorys.get(id)

    fun calcId(className: String): Int {
        var out = 0
        for (i in 0..className.length - 1) {
            out += className[i].toInt() * i
        }
        return out
    }
}