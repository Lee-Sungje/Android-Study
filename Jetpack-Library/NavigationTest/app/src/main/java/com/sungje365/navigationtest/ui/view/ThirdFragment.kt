package com.sungje365.navigationtest.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sungje365.navigationtest.R
import com.sungje365.navigationtest.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)

        binding.btnThirdGoto.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        return binding.root
    }
}