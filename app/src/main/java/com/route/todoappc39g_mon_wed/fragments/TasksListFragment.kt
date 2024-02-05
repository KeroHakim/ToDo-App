package com.route.todoappc39g_mon_wed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.todoappc39g_mon_wed.adapters.TasksAdapter
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.databinding.FragmentTasksBinding
import java.util.Date

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter: TasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TasksAdapter(null)
        // Practice - Assignment -> Todo 
        binding.rvTasks.adapter = adapter
        val list = TasksDatabase.getInstance(requireContext()).getTasksDao().getAllTasks()
        val minDate: Date? = null
        val maxDate: Date? = null
        list.forEach {
            minDate

        }
        adapter.updateData(list)
    }
}
