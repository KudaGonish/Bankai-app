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
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kudagonish.bankai.R
import ru.kudagonish.bankai.databinding.FragmentSearchAnimeBinding


@AndroidEntryPoint
class SearchAnimeFragment : Fragment() {

    private var searchJob: Job? = null

    private lateinit var binding: FragmentSearchAnimeBinding

    private val viewModel: SearchAnimeViewModel by viewModels()

    private val searchAnimePagingAdapter = SearchAnimePagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAnimeBinding.inflate(inflater, container, false)

        setRecyclerView()
        searchAnime()

        val menuHost: MenuHost = binding.searchAnimeToolbar
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val menuFilterButton = menu.findItem(R.id.action_filter)

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
                    Toast.makeText(context, "coming soon", Toast.LENGTH_LONG).show()
                }
                return true
            }
        }, viewLifecycleOwner)

        binding.swipeRefresh.setOnRefreshListener {
            searchAnime()
        }

        return binding.root
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

