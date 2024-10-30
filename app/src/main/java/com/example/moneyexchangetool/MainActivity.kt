package com.example.moneyexchangetool

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencycalculator.R
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var source: EditText
    private lateinit var target: EditText
    private lateinit var sourceSpinner: Spinner
    private lateinit var targetSpinner: Spinner

    private fun updateConversion(isSourceToTarget: Boolean) {
        val sourceAmountText = if (isSourceToTarget) source.text.toString() else target.text.toString()
        if (sourceAmountText.isNotEmpty()) {
            val sourceAmount = sourceAmountText.toDoubleOrNull() ?: 0.0
            val sourceCurrency = if (isSourceToTarget) sourceSpinner.selectedItem.toString() else targetSpinner.selectedItem.toString()
            val targetCurrency = if (isSourceToTarget) targetSpinner.selectedItem.toString() else sourceSpinner.selectedItem.toString()

            val conversionRate = getConversionRate(sourceCurrency, targetCurrency)
            val targetAmount = sourceAmount * conversionRate

            if (isSourceToTarget) {
                target.setText(String.format(Locale.US, "%.2f", targetAmount))
            } else {
                source.setText(String.format(Locale.US, "%.2f", targetAmount))
            }
        } else {
            if (isSourceToTarget) {
                target.setText("")
            } else {
                source.setText("")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Đổi tiền"
        }

        source = findViewById(R.id.source)
        target = findViewById(R.id.target)
        sourceSpinner = findViewById(R.id.sourceSpinner)
        targetSpinner = findViewById(R.id.targetSpinner)

        val currencyList = arrayOf("USD", "EUR", "VND", "JPY")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceSpinner.adapter = adapter
        targetSpinner.adapter = adapter




        source.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (source.isFocused) {
                    updateConversion(isSourceToTarget = true)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        target.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (target.isFocused) {
                    updateConversion(isSourceToTarget = false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sourceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                updateConversion(isSourceToTarget = false)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        targetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                updateConversion(isSourceToTarget = true)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }



    private fun getConversionRate(fromCurrency: String, toCurrency: String): Double {
        return when (fromCurrency to toCurrency) {
            "USD" to "EUR" -> 0.94
            "EUR" to "USD" -> 1/0.94
            "EUR" to "VND" -> 27432.62
            "VND" to "EUR" -> 1/27432.62
            "USD" to "VND" -> 25345.0
            "VND" to "USD" -> 1/25345.0
            "JPY" to "VND" -> 165.71
            "VND" to "JPY" -> 1/165.71
            else -> 1.0
        }
    }
}