package by.vzhilko.list.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.list.databinding.FragmentImageListBinding
import by.vzhilko.list.di.component.ImageListComponent
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactoryProvider
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import by.vzhilko.list.presentation.adapter.ImageDataAdapter
import by.vzhilko.list.presentation.adapter.ImageDataStateAdapter
import by.vzhilko.list.presentation.adapter.decoration.ImageDataViewDecoration
import by.vzhilko.list.presentation.adapter.diffutil.ImageDataDiffUtilItemCallback
import by.vzhilko.list.presentation.model.ImageDataListState
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageListFragment :
    BaseFragment<ImageListComponent, ImageListViewModel, FragmentImageListBinding>() {

    private lateinit var imageDataAdapter: ImageDataAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun initAndGetComponent(): ImageListComponent {
        return getDIContainerProvider().getComponentProvider<ImageListComponent.Provider>()
            .getImageListComponent()
    }

    override fun initAndGetViewModel(savedInstanceState: Bundle?): ImageListViewModel {
        return ViewModelProvider(this, getAbstractSavedStateViewModelFactory(savedInstanceState))[ImageListViewModel::class.java]
    }

    override fun initAndGetView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageListBinding {
        return FragmentImageListBinding.inflate(inflater, container, false).apply {
            viewModel = this@ImageListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initSearchView()
        initRecyclerView()
        subscribeOnImageDataLoadState()
        subscribeOnImageData()
        subscribeOnImageListRetry()
    }

    private fun initSearchView() {
        binding.imageListSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateQuery(newText)
                return true
            }
        })
    }

    private fun initRecyclerView() {
        val diffUtilItemCallback = ImageDataDiffUtilItemCallback()
        imageDataAdapter = ImageDataAdapter(diffUtilItemCallback) { data: ImageData ->
            navigator.openImageListDialogFragment(findNavController(), data)
        }
        val onRetryAction: () -> Unit = { viewModel.retryImageDataLoading() }
        val resultAdapter: ConcatAdapter = imageDataAdapter.withLoadStateHeaderAndFooter(
            header = ImageDataStateAdapter(onRetryAction),
            footer = ImageDataStateAdapter(onRetryAction)
        )

        val layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        val itemDecoration = ImageDataViewDecoration(requireContext())
        binding.imageListRecyclerView.apply {
            this.adapter = resultAdapter
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
        }
    }

    private fun subscribeOnImageData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageDataPagingDataFlow.collectLatest { pagingData: PagingData<ImageData> ->
                    //pagingData.map { Log.d("myTag", "fragment image: ${it}") }
                    imageDataAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun subscribeOnImageDataLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                imageDataAdapter.loadStateFlow.collectLatest { state: CombinedLoadStates ->
                    /*Log.d("myTag", "Fragment state mediator append: ${state.mediator?.append}" +
                            "\nsource append: ${state.source.append}" +
                            "\nrefresh mediator: ${state.mediator?.refresh}}"
                    )*/
                    when (state.refresh) {
                        is LoadState.Loading -> {
                            viewModel.updateImageListState(ImageDataListState.LOADING)
                        }
                        is LoadState.Error -> {
                            viewModel.updateImageListState(
                                ImageDataListState.ERROR.apply {
                                    message = (state.refresh as? LoadState.Error)?.error?.message
                                }
                            )
                        }
                        else -> {
                            viewModel.updateImageListState(ImageDataListState.NO_STATE)
                        }
                    }
                }
            }
        }
    }

    private fun subscribeOnImageListRetry() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageListRetryStateFlow.collectLatest {
                    imageDataAdapter.retry()
                }
            }
        }
    }

}