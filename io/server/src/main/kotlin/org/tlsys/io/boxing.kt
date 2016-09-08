package org.tlsys.io

import java.lang.reflect.Method
import java.util.*

private fun <T> unboxCast(valueFrom: DTO?, to: Class<*>): T? {
    if (valueFrom === null)
        return null
    if (valueFrom.javaClass === to)
        return valueFrom as T

    when (to) {
        String::class.java -> return (valueFrom as StringDTO).value as T
        Boolean::class.java -> return (valueFrom as BooleanDTO).value as T
        Char::class.java -> return (valueFrom as CharDTO).value as T
        Byte::class.java -> return (valueFrom as ByteDTO).value as T
        Short::class.java -> return (valueFrom as ShortDTO).value as T
        Int::class.java -> return (valueFrom as IntDTO).value as T
        Float::class.java -> return (valueFrom as FloatDTO).value as T
        Long::class.java -> return (valueFrom as LongDTO).value as T
        Double::class.java -> return (valueFrom as DoubleDTO).value as T
    }

    when (valueFrom) {
        is StringDTO -> return valueFrom.value as T
        is BooleanDTO -> return valueFrom.value as T
        is CharDTO -> return valueFrom.value as T
        is ByteDTO -> return valueFrom.value as T
        is ShortDTO -> return valueFrom.value as T
        is IntDTO -> return valueFrom.value as T
        is FloatDTO -> return valueFrom.value as T
        is LongDTO -> return valueFrom.value as T
        is DoubleDTO -> return valueFrom.value as T
    }

    TODO("Can't convert ${valueFrom!!.javaClass.name} to ${to.name}")
}

private fun boxCast(valueFrom: Any?): DTO? {
    if (valueFrom === null)
        return null
    if (valueFrom is DTO)
        return valueFrom
    when (valueFrom) {
        is String -> return StringDTO(valueFrom)
        is Boolean -> return BooleanDTO(valueFrom)
        is Char -> return CharDTO(valueFrom)
        is Byte -> return ByteDTO(valueFrom)
        is Short -> return ShortDTO(valueFrom)
        is Int -> return IntDTO(valueFrom)
        is Long -> return LongDTO(valueFrom)
        is Float -> return FloatDTO(valueFrom)
        is Double -> return DoubleDTO(valueFrom)
        is DTO -> return valueFrom
    }

    if (valueFrom.javaClass.isArray) {
        var len = java.lang.reflect.Array.getLength(valueFrom)
        val out = ArrayList<DTO?>()
        for (i in 0..len - 1)
            out.add(boxCast(java.lang.reflect.Array.get(valueFrom, i)))
        return ListDTO<DTO>(out)
    }

    if (valueFrom is List<*>) {
        val out = ArrayList<DTO?>(valueFrom.size)
        for (i in valueFrom) {
            out.add(boxCast(i))
        }
        return ListDTO<DTO>(out)
    }

    throw RuntimeException("Can't convert ${valueFrom.javaClass.name} to DTO")
}

fun Any.boxing() = boxCast(this)
fun <T> DTO.unboxing(clazz: Class<T>): T? = unboxCast(this, clazz)

fun Method.invokeDTO(self: Any?, vararg arguments: DTO?): Any? {
    if (parameterCount != arguments.size)
        throw IllegalArgumentException("Can't Invoke method: bad arguments length")
    return this.invoke(self, *Array(parameterCount, { arguments[it]?.unboxing(parameterTypes[it]) }))
}