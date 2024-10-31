package com.example.practice3110ex2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: List<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        editTextSearch = findViewById(R.id.editTextSearch)

        students = listOf(
            Student("Nguyen Trung Hieu", "20215578"),
            Student("Nguyen Huy Khoa", "20215600"),
            Student("Alice Johnson", "34567"),
            Student("Bob Brown", "45678"),
            Student("Charlie Smith", "56789"),
            Student("Diana Wilson", "67890"),
            Student("Ethan Martinez", "78901"),
            Student("Fiona Taylor", "89012"),
            Student("George Anderson", "90123"),
            Student("Hannah Thomas", "01234"),
            Student("Ian White", "12345"),
            Student("Julia Harris", "23456"),
            Student("Kevin Clark", "34568"),
            Student("Laura Lewis", "45679"),
            Student("Mark Young", "56780"),
            Student("Nina Walker", "67891"),
            Student("Oscar Hall", "78902"),
            Student("Paula Allen", "89013"),
            Student("Quincy King", "90124"),
            Student("Rachel Wright", "01235"),
            Student("Sam Scott", "12346"),
            Student("Tina Adams", "23457"),
            Student("Umar Baker", "34569"),
            Student("Vera Gonzales", "45670"),
            Student("Willie Nelson", "56781"),
            Student("Xander Carter", "67892"),
            Student("Yara Mitchell", "78903"),
            Student("Zack Perez", "89014")
        )

        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString()
                if (keyword.length > 2) {
                    val filteredList = students.filter {
                        it.name.contains(keyword, ignoreCase = true) ||
                                it.studentId.contains(keyword)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(students)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
