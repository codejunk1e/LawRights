package io.github.codejunk1e.lawrights.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.codejunk1e.lawrights.R
import io.github.codejunk1e.lawrights.databinding.FragmentHomeBinding
import io.github.codejunk1e.lawrights.ext.dpToPx
import io.github.codejunk1e.lawrights.ext.hide
import io.github.codejunk1e.lawrights.ext.show
import io.github.codejunk1e.lawrights.models.Result
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var standardBottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    val viewModel by viewModels<HomeViewModel>()

    @Inject lateinit var lawSliderAdapter: LawSliderAdapter
    @Inject lateinit var bottomSheetRecyclerAdapter: BottomSheetRecyclerAdapter
    @Inject lateinit var linksAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginUser()
        setupViewpager()
        setupLinks()
        setupBottomSheet()
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loginState.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Error -> {
                    Snackbar.make(binding.root, result.error, Snackbar.LENGTH_SHORT).show()
                }
                Result.Loading -> {}
                is Result.Success -> {}
            }
        })

        viewModel.getAllRights().observe(viewLifecycleOwner, {
            bottomSheetRecyclerAdapter.items = it
            bottomSheetRecyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setupClickListeners() {
        binding.fab.setOnClickListener {
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun setupBottomSheet() {
        standardBottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        standardBottomSheetBehavior.apply {
            isHideable = true
            skipCollapsed = true
            isDraggable = false
            isFitToContents = false
            halfExpandedRatio = 0.9F
            expandedOffset = 300
            peekHeight = 1900
        }
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        standardBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.apply {
                            fab.hide()
                            backdrop.hide()
                        }
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.apply {
                            fab.show()
                            backdrop.show()
                        }
                    }
                    else -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }
        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        binding.bottomsheetLayout.bottomsheetRecyclerview.adapter = bottomSheetRecyclerAdapter
    }

    private fun setupLinks() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 6) 2 else 1
            }
        }

        binding.recyclerGrid.layoutManager = layoutManager
        binding.recyclerGrid.adapter = linksAdapter
        linksAdapter.clickFnx = {
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            viewModel.fetchAllRights()
        }
    }

    private fun setupViewpager() {
        binding.lawCardViewpger.adapter = lawSliderAdapter

        val offsetPx = resources
            .getDimension(R.dimen.viewpager_offset).toInt().dpToPx(resources.displayMetrics)
        val pageMarginPx = resources
            .getDimension(R.dimen.viewpager_margin).toInt().dpToPx(resources.displayMetrics)

        binding.lawCardViewpger.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2
            setPadding(0, 0, offsetPx, 0)
            setPageTransformer(MarginPageTransformer(pageMarginPx))
        }

        TabLayoutMediator(binding.indicator, binding.lawCardViewpger) { _, _ -> }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (standardBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}
