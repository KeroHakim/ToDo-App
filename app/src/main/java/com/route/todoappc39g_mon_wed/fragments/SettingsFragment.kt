package com.route.todoappc39g_mon_wed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.todoappc39g_mon_wed.databinding.FragmentSettingsBinding
import com.route.todoappc39g_mon_wed.databinding.FragmentTasksBinding

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}