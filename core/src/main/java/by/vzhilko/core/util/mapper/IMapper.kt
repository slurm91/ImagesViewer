package by.vzhilko.core.util.mapper

interface IMapper<From, To> {

    fun map(from: From): To

}