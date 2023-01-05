package ru.kudagonish.bankai.ui.filters

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup
import ru.kudagonish.bankai.R
import ru.kudagonish.bankai.databinding.FragmentFiltersBinding
import ru.kudagonish.bankai.utils.BaseFragment
import ru.kudagonish.bankai.utils.layoutGravity
import ru.kudagonish.bankai.utils.setViewPadding

@AndroidEntryPoint
class FiltersFragment : BaseFragment<FragmentFiltersBinding>(FragmentFiltersBinding::inflate) {

    private var filtersJob: Job? = null
    private val viewModel: FiltersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filtersJob?.cancel()
        filtersJob = lifecycleScope.launch {
            viewModel.getKindAndStatusFilters()
            viewModel.getUserListFilters()

            val kindButtonGroup = binding.typeButtonGroup
            val statusButtonGroup = binding.statusButtonGroup
            val animeInUserListButtonGroup = binding.UserListButtonGroup

            for (i in 0 until viewModel.kindAndStatusFilter.kind.size)
                createThemedButton(kindButtonGroup, viewModel.kindAndStatusFilter.kind[i])

            for (i in 0 until viewModel.kindAndStatusFilter.status.size)
                createThemedButton(statusButtonGroup, viewModel.kindAndStatusFilter.status[i])

            for (i in 0 until viewModel.userListFilter.status.size)
                createThemedButton(animeInUserListButtonGroup, viewModel.userListFilter.status[i])
        }
    }
    override fun onCreateView() {

        binding.exitButton.setOnClickListener {
            NavHostFragment.findNavController(this@FiltersFragment).popBackStack()
        }


    }

    private fun createThemedButton(buttonGroup: ThemedToggleButtonGroup, data: String) {
        val btn = ThemedButton(buttonGroup.context).apply {
            tag = data + "button"
            text = data

            fontFamily = "/fonts/comfortaa_light.ttf"
            applyToTexts {
                it.textSize = 10f
                it.setViewPadding(
                    left = 38f,
                    top = 0f,
                    right = 38f,
                    bottom = 0f,
                    horizontal = 0f,
                    vertical = 0f
                )
                it.layoutGravity = Gravity.CENTER_VERTICAL
            }
            textColor = resources.getColor(R.color.green, context?.theme)
            selectedTextColor = resources.getColor(R.color.green, context?.theme)
            bgColor = resources.getColor(R.color.white, context?.theme)
            selectedBgColor = resources.getColor(R.color.opacity_green, context?.theme)
            borderColor = resources.getColor(R.color.green, context?.theme)
            selectedBorderColor = resources.getColor(R.color.green, context?.theme)
            borderWidth = 2.5f
            selectedBorderWidth = 2.7f
        }

        val params = RelativeLayout.LayoutParams(WRAP_CONTENT, 58)
        params.setMargins(0, 17, 0, 0)
        buttonGroup.addView(btn, params)
    }
}


