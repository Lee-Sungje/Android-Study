package com.sungje365.fragmenttest.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sungje365.fragmenttest.R
import com.sungje365.fragmenttest.databinding.FragmentCounterBinding
import com.sungje365.fragmenttest.ui.viewmodel.CounterViewModel

class CounterFragment : Fragment() {
    private lateinit var binding: FragmentCounterBinding
    private val model: CounterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_counter,
            container,
            false
        )

        binding.apply {
            viewModel = model
            lifecycleOwner = this@CounterFragment
        }

        return binding.root
    }
}