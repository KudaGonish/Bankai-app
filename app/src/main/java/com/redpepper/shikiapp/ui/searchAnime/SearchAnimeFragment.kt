package com.redpepper.shikiapp.ui.searchAnime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.redpepper.shikiapp.R
import com.redpepper.shikiapp.databinding.FragmentSearchAnimeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAnimeFragment : Fragment() {

    private var searchJob: Job? = null

    private lateinit var binding: FragmentSearchAnimeBinding

    private val viewModel: SearchAnimeViewModel by viewModels()

    private val searchAnimePagingAdapter = SearchAnimePagingAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchAnimeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setReryclerView()
        searchAnime()
    }

    private fun searchAnime(){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch{
            viewModel.getSortedByRankAnime()
                viewModel.searchResult.observe(viewLifecycleOwner){
                searchAnimePagingAdapter.submitData(lifecycle,it)
            }
        }
    }

    private fun  setReryclerView(){
        val columnsCount = resources.getInteger(R.integer.itemsInRecyclerCount)

        binding.searchAnimeRecycler.apply {
            adapter = searchAnimePagingAdapter
            layoutManager = GridLayoutManager(requireContext(),columnsCount)
        }
    }
}