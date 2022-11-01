package by.vzhilko.list.di.component

import by.vzhilko.core.di.annotation.scope.FragmentScope
import by.vzhilko.list.presentation.fragment.ImageListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface ImageListComponent {

    fun inject(fragment: ImageListFragment)

    interface Provider {
        fun getImageListComponent(): ImageListComponent
    }

}