package com.sungje365.navigationtest.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sungje365.navigationtest.R
import com.sungje365.navigationtest.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        binding.btnSecondGoto.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        return binding.root
    }
}