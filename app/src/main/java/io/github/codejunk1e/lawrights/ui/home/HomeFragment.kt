package io.github.codejunk1e.lawrights.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import io.github.codejunk1e.lawrights.R
import io.github.codejunk1e.lawrights.databinding.FragmentHomeBinding
import io.github.codejunk1e.lawrights.ext.dpToPx
import io.github.codejunk1e.lawrights.ext.hide
import io.github.codejunk1e.lawrights.ext.show
import io.github.codejunk1e.lawrights.models.CardModel
import io.github.codejunk1e.lawrights.models.LinksModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var standardBottomSheetBehavior: BottomSheetBehavior<FrameLayout>

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
        binding.lawCardViewpger.adapter = LawSliderAdapter(getDummyCards())
        setupViewpager()
        setupLinks()
        setupBottomSheet()
        setupClickListeners()
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
            peekHeight = 1700
        }
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        standardBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
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
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }
        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        binding.mainView.isEnabled = false
    }

    private fun setupLinks() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 6) 2 else 1
            }
        }

        binding.recyclerGrid.layoutManager = layoutManager
        binding.recyclerGrid.adapter = LinksAdapter(getDummyLinks()){
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setupViewpager() {

        val offsetPx = resources
            .getDimension(R.dimen.viewpager_offset)
            .toInt()
            .dpToPx(resources.displayMetrics)

        val pageMarginPx = resources
            .getDimension(R.dimen.viewpager_margin)
            .toInt()
            .dpToPx(resources.displayMetrics)

        binding.lawCardViewpger.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2
            setPadding(0, 0, offsetPx, 0)
            setPageTransformer(MarginPageTransformer(pageMarginPx))
        }

        TabLayoutMediator(binding.indicator, binding.lawCardViewpger) { tab, position ->

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun getDummyCards() = listOf(
        CardModel(
            image = R.drawable.law_card_image,
            caption = getString(R.string.law_caption),
            desc = getString(R.string.law_description),
            actionText = getString(R.string.law_action_text)
        ),
        CardModel(
            image = R.drawable.law_card_image,
            caption = getString(R.string.law_caption),
            desc = getString(R.string.law_description),
            actionText = getString(R.string.law_action_text)
        ),
        CardModel(
            image = R.drawable.law_card_image,
            caption = getString(R.string.law_caption),
            desc = getString(R.string.law_description),
            actionText = getString(R.string.law_action_text)
        ),
        CardModel(
            image = R.drawable.law_card_image,
            caption = getString(R.string.law_caption),
            desc = getString(R.string.law_description),
            actionText = getString(R.string.law_action_text)
        )
    )

    fun getDummyLinks() = listOf(
        LinksModel(
            title = "My Rights",
            color = R.color.bg_color_grid_one
        ),
        LinksModel(
            title = "My Duties",
            color = R.color.bg_color_grid_two
        ),
        LinksModel(
            title = "General \n" +
                    "Provisions",
            color = R.color.bg_color_grid_three
        ),
        LinksModel(
            title = "Find a Lawyer",
            color = R.color.bg_color_grid_four
        ),
        LinksModel(
            title = "Know your\n" +
                    "Representatives",
            color = R.color.bg_color_grid_five
        ),
        LinksModel(
            title = "Constitution",
            color = R.color.bg_color_grid_six
        ),
        LinksModel(
            title = "Report a Violation",
            color = R.color.bg_color_grid_seven
        )
    )
}
