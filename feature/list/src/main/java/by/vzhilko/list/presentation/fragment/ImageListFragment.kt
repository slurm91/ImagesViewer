package by.vzhilko.list.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.vzhilko.core.ui.fragment.BaseFragment
import by.vzhilko.list.databinding.FragmentImageListBinding
import by.vzhilko.list.di.component.ImageListComponent
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.presentation.adapter.ImageDataAdapter
import by.vzhilko.list.presentation.adapter.ImageDataStateAdapter
import by.vzhilko.list.presentation.adapter.decoration.ImageDataViewDecoration
import by.vzhilko.list.presentation.adapter.diffutil.ImageDataDiffUtilItemCallback
import by.vzhilko.list.presentation.dialog.ImageListDialogFragment
import by.vzhilko.list.presentation.model.ImageDataListState
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageListFragment : BaseFragment<ImageListComponent, ImageListViewModel, FragmentImageListBinding>() {

    private lateinit var imageDataAdapter: ImageDataAdapter

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
        Log.d("myTag", "ImageListFragment onViewCreated navigator: ${navigator}" +
                "\nviewModel: ${viewModel}" +
                "\ncomponent: ${component}"
        )
    }

    private fun initView() {
        //binding.imageListSearchView.setQuery()
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

        val layoutManager = LinearLayoutManager(requireContext()).apply { orientation = RecyclerView.VERTICAL }
        val itemDecoration = ImageDataViewDecoration(requireContext())
        binding.imageListRecyclerView.apply {
            this.adapter = resultAdapter
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
        }
    }

    private fun subscribeOnImageData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageDataPagingDataFlow.collectLatest { pagingData: PagingData<ImageData> ->
                    Log.d("myTag", "Fragment subscribeOnImageData before pagingData: ${pagingData} thread: ${Thread.currentThread().name}")
                    imageDataAdapter.submitData(pagingData)
                    Log.d("myTag", "Fragment subscribeOnImageData after pagingData: ${pagingData}")
                }
            }
        }
    }

    private fun subscribeOnImageDataLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                imageDataAdapter.loadStateFlow.collectLatest { state: CombinedLoadStates ->
                    when (state.refresh) {
                        is LoadState.Loading -> {
                            viewModel.updateImageListState(ImageDataListState.LOADING)
                            //binding.imageListRecyclerView.scrollToPosition(0)
                        }
                        is LoadState.Error -> {
                            viewModel.updateImageListState(ImageDataListState.ERROR)
                        }
                        else -> {
                            viewModel.updateImageListState(ImageDataListState.NO_STATE)
                        }
                    }
                    Log.d("myTag", "Fragment subscribeOnImageDataLoadState state append: ${state.append}" +
                            " prepend: ${state.prepend}" +
                            " refresh: ${state.refresh}"
                    )
                }
            }
        }
    }

    private fun subscribeOnImageListRetry() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageListRetryStateFlow.collectLatest {
                    Log.d("myTag", "Fragment subscribeOnImageListRetry")
                    imageDataAdapter.retry()
                }
            }
        }
    }

}