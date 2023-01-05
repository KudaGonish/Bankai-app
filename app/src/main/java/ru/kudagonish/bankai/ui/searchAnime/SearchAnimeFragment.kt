package ru.kudagonish.bankai.ui.searchAnime

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kudagonish.bankai.R
import ru.kudagonish.bankai.databinding.FragmentSearchAnimeBinding
import ru.kudagonish.bankai.utils.BaseFragment


@AndroidEntryPoint
class SearchAnimeFragment : BaseFragment<FragmentSearchAnimeBinding>(FragmentSearchAnimeBinding::inflate) {

    private var searchJob: Job? = null

    private val viewModel: SearchAnimeViewModel by viewModels()

    private val searchAnimePagingAdapter = SearchAnimePagingAdapter()

    override fun onCreateView(){
        setRecyclerView()
        searchAnime()

        val menuHost: MenuHost = binding.searchAnimeToolbar

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)

                val menuSearchButton = menu.findItem(R.id.action_search)
                val searchView =
                    menuSearchButton.actionView?.findViewById<SearchView>(R.id.customSearchView)

                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(query: String?): Boolean {
                        return if (query?.length!! >= 3) {
                            searchAnimeByName(query)
                            true
                        } else if (query.length < 3) {
                            searchJob?.cancel()
                            true
                        } else {
                            false
                        }
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return if (query!!.isNotEmpty()) {
                            menuSearchButton.collapseActionView()
                            searchAnimeByName(query)
                            true
                        } else false
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_filter) {
                    NavHostFragment.findNavController(this@SearchAnimeFragment).navigate(R.id.action_searchAnime_to_filters)
                }
                return true
            }
        }, viewLifecycleOwner)

        binding.swipeRefresh.setOnRefreshListener {
            searchAnime()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun setRecyclerView() {
        val columnsCount = resources.getInteger(R.integer.itemsInRecyclerCount)

        binding.searchAnimeRecycler.apply {
            adapter = searchAnimePagingAdapter
            layoutManager = GridLayoutManager(requireContext(), columnsCount)
        }
    }

    private fun searchAnime() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            binding.swipeRefresh.isRefreshing = true

            searchAnimePagingAdapter.submitData(lifecycle, PagingData.empty())

            viewModel.getSortedByRankAnime()
            viewModel.searchResult.observe(viewLifecycleOwner) {

                searchAnimePagingAdapter.submitData(lifecycle, it)
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun searchAnimeByName(name: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(1000)
            searchAnimePagingAdapter.submitData(lifecycle, PagingData.empty())
            viewModel.getSearchAnimeByName(name)
            viewModel.searchResult.observe(viewLifecycleOwner) {
                searchAnimePagingAdapter.submitData(lifecycle, it)
            }
        }
    }
}

