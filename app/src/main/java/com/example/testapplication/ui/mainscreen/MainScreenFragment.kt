package com.example.testapplication.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainScreenViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        viewModel.deviceList.observe(viewLifecycleOwner) { deviceList ->
            DeviceAdapter(requireContext(), deviceList, viewModel).apply {
                notifyDataSetChanged()
                binding.deviceAdapter = this
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllDevices()
    }
}