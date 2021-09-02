package com.sungje365.navigationtest.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sungje365.navigationtest.R
import com.sungje365.navigationtest.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        binding.btnFirstGoto.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }
}