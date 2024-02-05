package com.route.todoappc39g_mon_wed.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc39g_mon_wed.R
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        binding.selectTimeTv.setOnClickListener {
            val picker =
                TimePickerDialog(
                    requireContext(),
                    object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            // Calendar object <->  Dates , Time
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            calendar.set(Calendar.MINUTE, minute)
//                            calendar.get(Calendar.AM_PM)
                            binding.selectTimeTv.text = "$hourOfDay:$minute"
                        }
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
                )
            picker.show()
        }
        binding.selectDateTv.setOnClickListener {
            val picker =
                DatePickerDialog(
                    requireContext(),
                    object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            binding.selectDateTv.text = "$dayOfMonth / ${month + 1} / $year"
                        }
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),


                    )
            picker.datePicker.minDate = System.currentTimeMillis()
            picker.show()
        }
        binding.addTaskBtn.setOnClickListener {
            if (validateFields()) {
                val task = Task(
                    title = binding.title.text.toString(),
                    description = binding.description.text.toString(),
                    date = calendar.time,
                    isDone = false
                )

                TasksDatabase
                    .getInstance(requireContext())
                    .getTasksDao()
                    .insertTask(task)
                dismiss()
            }

        }

    }

    private fun validateFields(): Boolean {
        // ""                         // "              "    "     Hello   "
        if (binding.title.text?.isEmpty() == true || binding.title.text?.isBlank() == true) {
            binding.title.error = "Required"
            return false
        } else
            binding.title.error = null
        if (binding.description.text?.isEmpty() == true || binding.description.text?.isBlank() == true) {
            binding.description.error = "Required"
            return false
        } else
            binding.description.error = null
        if (binding.selectDateTv.text == getString(R.string.select_date)) {

            return false
        }
        if (binding.selectTimeTv.text == getString(R.string.select_time)) {

            return false
        }

        return true
    }
}
