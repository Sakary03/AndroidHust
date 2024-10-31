package com.example.practice3110

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var numberToCalc: EditText
    private lateinit var listSelect: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        numberToCalc = findViewById(R.id.numberToCalc)
        listSelect = findViewById(R.id.listSelect)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listview)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val inputText = numberToCalc.text.toString()
        textViewError.text = "" // Xóa lỗi trước khi xử lý

        // Kiểm tra dữ liệu nhập vào có hợp lệ không
        val n = inputText.toIntOrNull()
        if (n == null || n < 0) {
            textViewError.text = "Vui lòng nhập một số nguyên dương."
            return
        }

        // Lấy ID của RadioButton được chọn
        val selectedId = listSelect.checkedRadioButtonId
        val numbers = when (selectedId) {
            R.id.radioButtonEven -> findEven(n)
            R.id.radioButtonOdd -> findOdd(n)
            R.id.radioButtonSquare -> findSquare(n)
            else -> {
                textViewError.text = "Vui lòng chọn một loại số."
                return
            }
        }

        // Cập nhật ListView với kết quả
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = arrayAdapter
    }

    private fun findOdd(n: Int): List<Int> {
        val listNum = mutableListOf<Int>()
        for (num in 1..n) {
            if (num % 2 == 1) listNum.add(num)
        }
        return listNum
    }

    private fun findEven(n: Int): List<Int> {
        val listNum = mutableListOf<Int>()
        for (num in 0..n) {
            if (num % 2 == 0) listNum.add(num)
        }
        return listNum
    }

    private fun findSquare(n: Int): List<Int> {
        val listNum = mutableListOf<Int>()
        var num = 1
        while (num * num <= n) {
            listNum.add(num * num)
            num++
        }
        return listNum
    }
}
