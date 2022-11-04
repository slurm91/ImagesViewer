package by.vzhilko.core.di

interface IDIContainerProvider {

    fun <T> getComponentProvider(): T

}