package by.vzhilko.core.di.annotation.mapper

import by.vzhilko.core.util.mapper.IMapper
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Mapper(val type: KClass<out IMapper<*, *>>)
