package by.vzhilko.details.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.details.databinding.FragmentImageDetailsBinding
import by.vzhilko.details.di.component.ImageDetailsComponent
import by.vzhilko.details.viewmodel.ImageDetailsViewModel

class ImageDetailsFragment : BaseFragment<ImageDetailsComponent, ImageDetailsViewModel, FragmentImageDetailsBinding>() {

    private val data: ImageData? by lazy { arguments?.getParcelable("IMAGE_DATA") as? ImageData }

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

    override fun initAndGetView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageDetailsBinding {
        return FragmentImageDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = this@ImageDetailsFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageDetailsToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        data?.let { viewModel.updateImageData(it) }
    }

}