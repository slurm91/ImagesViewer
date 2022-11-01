package by.vzhilko.list.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.list.databinding.FragmentImageListBinding
import by.vzhilko.list.di.component.ImageListComponent

class ImageListFragment : BaseFragment<ImageListComponent>() {

    private var _binding: FragmentImageListBinding? = null
    val binding: FragmentImageListBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun initAndGetComponent(): ImageListComponent {
         return getDIContainerProvider().getComponentProvider<ImageListComponent.Provider>().getImageListComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageListBtn.setOnClickListener {
            navigator.openImageDetailsFragment(findNavController())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}