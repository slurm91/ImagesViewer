package by.vzhilko.list.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.core.util.DefaultState
import by.vzhilko.list.databinding.FragmentImageListBinding
import by.vzhilko.list.di.component.ImageListComponent
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageListFragment : BaseFragment<ImageListComponent, ImageListViewModel>() {

    private var _binding: FragmentImageListBinding? = null
    val binding: FragmentImageListBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun initAndGetComponent(): ImageListComponent {
         return getDIContainerProvider().getComponentProvider<ImageListComponent.Provider>().getImageListComponent()
    }

    override fun initAndGetViewModel(): ImageListViewModel {
        return ViewModelProvider(this, getViewModelFactory())[ImageListViewModel::class.java]
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
        Log.d("myTag", "ImageListFragment onViewCreated navigator: ${navigator}" +
                "\nviewModel: ${viewModel}"
        )
    }

    private fun initView() {
        binding.imageListBtn.setOnClickListener {
            //navigator.openImageDetailsFragment(findNavController())
            viewModel.loadImages("fruits")
        }
        subscribeOnImageListData()
    }

    private fun subscribeOnImageListData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageListStateFlow.collectLatest {
                    when(it) {
                        is DefaultState.Success -> {
                            Log.d("myTag", "ImageListFragment subscribeOnImageListData data size: ${it.value.size}")
                        }
                        is DefaultState.Error -> {
                            Log.d("myTag", "ImageListFragment subscribeOnImageListData error: ${it.error.message}")
                        }
                        else -> {
                            Log.d("myTag", "ImageListFragment subscribeOnImageListData no state")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}