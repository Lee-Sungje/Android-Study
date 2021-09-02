package com.sungje365.databindingtest.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sungje365.databindingtest.R
import com.sungje365.databindingtest.data.model.User
import com.sungje365.databindingtest.databinding.ActivityMainBinding
import com.sungje365.databindingtest.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private val model: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            viewModel = model
        }
    }
}