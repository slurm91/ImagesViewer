package by.vzhilko.details.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.details.databinding.FragmentImageDetailsBinding
import by.vzhilko.details.di.component.ImageDetailsComponent
import by.vzhilko.details.viewmodel.ImageDetailsViewModel

class ImageDetailsFragment : BaseFragment<ImageDetailsComponent, ImageDetailsViewModel>() {

    private var _binding: FragmentImageDetailsBinding? = null
    val binding: FragmentImageDetailsBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun initAndGetComponent(): ImageDetailsComponent {
        return getDIContainerProvider().getComponentProvider<ImageDetailsComponent.Provider>().getImageDetailsComponent()
    }

    override fun initAndGetViewModel(): ImageDetailsViewModel {
        return ViewModelProvider(this, getViewModelFactory())[ImageDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("myTag", "ImageListFragment onViewCreated navigator: ${navigator}" +
                "\nviewModel: ${viewModel}"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}