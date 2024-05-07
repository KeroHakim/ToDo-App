package com.route.todoappc39g_mon_wed.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todoappc39g_mon_wed.Constant
import com.route.todoappc39g_mon_wed.TaskEditActivity
import com.route.todoappc39g_mon_wed.adapters.TasksAdapter
import com.route.todoappc39g_mon_wed.clearTime
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.databinding.FragmentTasksBinding
import java.util.Calendar

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter: TasksAdapter
    lateinit var calendar: Calendar
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
        Log.e("TAG","Error")
        adapter.setOnTaskClickListener{
            val intent = Intent(requireActivity(), TaskEditActivity::class.java)
            intent.putExtra(Constant.TASK,it)
            startActivity(intent)
        }
        calendar = Calendar.getInstance()
        // Practice - Assignment -> Todo 
        binding.rvTasks.adapter = adapter
        val list = TasksDatabase.getInstance(requireContext()).getTasksDao().getAllTasks()

        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            val year = date.year
            val month = date.month - 1
            // 9:50:20 PM  X 9:50:21 PM
            val dayOfMonth = date.day
            calendar.clearTime()

            Log.e("TAG", "onViewCreated: Library  $month")
            Log.e("TAG", "onViewCreated: Calendar  ${calendar.get(Calendar.MONTH)}")
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month) // index = 0 -> January
            //              January -> 1
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            getTasks()
        }
        val today = CalendarDay.today()
        binding.calendarView.invalidate()
        adapter.updateData(list)
    }

    fun getTasks() {
        val updatedList = TasksDatabase
            .getInstance(requireContext())
            .getTasksDao()
            .getTasksByDate(calendar.time)
        adapter.updateData(updatedList)
    }
}
