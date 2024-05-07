package com.route.todoappc39g_mon_wed

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.route.todoappc39g_mon_wed.DateFormatter.getDateOnly
import com.route.todoappc39g_mon_wed.DateFormatter.getTimeOnly
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.ActivityTaskDetailsBinding
import com.route.todoappc39g_mon_wed.databinding.EditTaskFragmentBinding
import java.util.Calendar

class TaskEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var task: Task
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getTask()

        // Initialize calendar
        calendar = Calendar.getInstance()

        binding.content.selectTimeTv.setOnClickListener {
            getTimeDialogue()
        }

        binding.content.selectDateTv.setOnClickListener {
            getDateDialogue()
        }

        binding.content.btnSave.setOnClickListener {

            val newTask = Task(
                task.id,
                binding.content.title.text.toString(),
                binding.content.description.text.toString(),
                calendar.time
            )
            TasksDatabase.getInstance(this@TaskEditActivity).getTasksDao().updateTask(newTask)
        }


        bindTask(task)
    }

    private fun getTask() {
        task = intent.getParcelableExtra<Task>(Constant.TASK) ?: Task()
    }

    private fun bindTask(task: Task) {
        binding.content.title.setText(task.title ?: "")
        binding.content.description.setText(task.description ?: "")
        binding.content.selectDateTv.text = task.date?.getDateOnly() ?: ""
        binding.content.selectTimeTv.text = task.date?.getTimeOnly() ?: ""
    }

    private fun getDateDialogue() {
        val picker = DatePickerDialog(
            this@TaskEditActivity,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.content.selectDateTv.text = "$dayOfMonth / ${month + 1} / $year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        picker.datePicker.minDate = System.currentTimeMillis()
        picker.show()
    }

    private fun getTimeDialogue() {
        val picker = TimePickerDialog(
            this@TaskEditActivity,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                binding.content.selectTimeTv.text = "$hourOfDay:$minute"
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        picker.show()
    }
}
