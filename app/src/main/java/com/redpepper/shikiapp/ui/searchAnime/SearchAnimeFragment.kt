package com.redpepper.shikiapp.ui.searchAnime

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
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
    ): View {
        binding = FragmentSearchAnimeBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = binding.searchAnimeToolbar
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val menuFilterButton = menu.findItem(R.id.action_filter)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.action_filter) {
                    Toast.makeText(context, "coming soon", Toast.LENGTH_LONG).show()
                }

                return true
            }
        }, viewLifecycleOwner)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()

        searchAnime()

        binding.swipeRefresh.setOnRefreshListener {
            searchAnime()
        }
    }


    private fun searchAnime() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            binding.swipeRefresh.isRefreshing = true

            searchAnimePagingAdapter.submitData(PagingData.empty())

            viewModel.getSortedByRankAnime()
            viewModel.searchResult.observe(viewLifecycleOwner) {
                searchAnimePagingAdapter.submitData(lifecycle, it)
            }

            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        val columnsCount = resources.getInteger(R.integer.itemsInRecyclerCount)

        binding.searchAnimeRecycler.apply {
            adapter = searchAnimePagingAdapter
            layoutManager = GridLayoutManager(requireContext(), columnsCount)
        }
    }
}