package by.vzhilko.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.details.databinding.FragmentImageDetailsBinding
import by.vzhilko.details.viewmodel.ImageDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment<ImageDetailsViewModel, FragmentImageDetailsBinding>() {

    override val viewModel: ImageDetailsViewModel by viewModels()

    private val data: ImageData? by lazy { arguments?.getParcelable("IMAGE_DATA") as? ImageData }

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