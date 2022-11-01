package by.vzhilko.details.di.component

import by.vzhilko.core.di.annotation.scope.FragmentScope
import by.vzhilko.details.fragment.ImageDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface ImageDetailsComponent {

    fun inject(fragment: ImageDetailsFragment)

    interface Provider {
        fun getImageDetailsComponent(): ImageDetailsComponent
    }

}