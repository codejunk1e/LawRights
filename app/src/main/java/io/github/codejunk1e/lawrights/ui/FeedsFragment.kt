package io.github.codejunk1e.lawrights.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.codejunk1e.lawrights.databinding.FragmentFeedsBinding
import io.github.codejunk1e.lawrights.databinding.FragmentHomeBinding

@AndroidEntryPoint
class FeedsFragment : Fragment() {

    private var _binding: FragmentFeedsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}