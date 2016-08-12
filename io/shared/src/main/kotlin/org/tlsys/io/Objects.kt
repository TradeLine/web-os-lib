package org.tlsys.io

import java.util.*

interface DTO {
    val DTO_ID:Int
}

interface DTOFactory {
    fun read(reader:Reader):DTO
    fun write(obj:DTO,writer:Writer)
}

object Objects {
    private val factorys = HashMap<Int, DTOFactory>()

    fun regFactory(id:Int, factory:DTOFactory){
        factorys.put(id, factory)
    }

    internal fun getFactoryById(id:Int):DTOFactory? = factorys.get(id)

    fun calcId(className:String):Int {
        var out = 0
        for (i in 0..className.length-1) {
            out+=className[i].toInt()
        }
        return out
    }
}