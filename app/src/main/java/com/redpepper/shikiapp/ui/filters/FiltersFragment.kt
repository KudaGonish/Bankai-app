package com.redpepper.shikiapp.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.redpepper.shikiapp.R
import com.redpepper.shikiapp.databinding.FragmentFiltersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup

@AndroidEntryPoint
class FiltersFragment : Fragment() {

    private lateinit var binding: FragmentFiltersBinding
    val viewModel: FiltersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            val kindAndStatusList = viewModel.getKindAndStatusFilters()
            val animeInUserList = viewModel.getUserListFilters()

            val kindButtonGroup = binding.typeButtonGroup
            val statusButtonGroup = binding.statusButtonGroup
            val animeInUserListButtonGroup = binding.UserListButtonGroup

            for (i in 0..kindAndStatusList.body()!!.kind.size)
                createThemedButton(kindButtonGroup, kindAndStatusList.body()!!.kind[i])

            for (i in 0..kindAndStatusList.body()!!.status.size)
                createThemedButton(statusButtonGroup, kindAndStatusList.body()!!.status[i])

            for (i in 0..animeInUserList.body()!!.status.size)
                createThemedButton(animeInUserListButtonGroup, animeInUserList.body()!!.status[i])
        }
        return binding.root
    }

    private fun createThemedButton(buttonGroup: ThemedToggleButtonGroup, data: String) {
        val btn = ThemedButton(buttonGroup.context).apply {
            tag = data + "button"
            tvText.textSize = 10f
            text = data
            textColor = R.color.green
            fontFamily = "/fonts/comfortaa_light.ttf"
            bgColor = R.color.white
            selectedBgColor = R.color.opacity_green
            borderColor = R.color.green
            borderWidth = 1.5f
            selectedBorderWidth = 1f
        }
        buttonGroup.addView(btn, ViewGroup.LayoutParams(WRAP_CONTENT, 20))
    }
}