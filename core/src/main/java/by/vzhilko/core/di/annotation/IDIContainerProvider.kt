package by.vzhilko.core.di.annotation

interface IDIContainerProvider {

    fun <T> getComponentProvider(): T

}