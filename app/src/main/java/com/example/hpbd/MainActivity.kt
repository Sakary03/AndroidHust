package com.example.hpbd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var inputText: String = ""
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val expressionTextView: TextView = findViewById(R.id.expressionTextView)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        val buttonZero: Button = findViewById(R.id.zero)
        val buttonOne: Button = findViewById(R.id.one)
        val buttonTwo: Button = findViewById(R.id.two)
        val buttonThree: Button = findViewById(R.id.three)
        val buttonFour: Button = findViewById(R.id.four)
        val buttonFive: Button = findViewById(R.id.five)
        val buttonSix: Button = findViewById(R.id.six)
        val buttonSeven: Button = findViewById(R.id.seven)
        val buttonEight: Button = findViewById(R.id.eight)
        val buttonNine: Button = findViewById(R.id.nine)

        val buttonAdd: Button = findViewById(R.id.add)
        val buttonSub: Button = findViewById(R.id.sub)
        val buttonMul: Button = findViewById(R.id.mul)
        val buttonDiv: Button = findViewById(R.id.div)
        val buttonEqual: Button = findViewById(R.id.calc)
        val buttonClear: Button = findViewById(R.id.c)
        val buttonCE: Button = findViewById(R.id.ce)
        val buttonDot: Button = findViewById(R.id.comma)
        val buttonNegate: Button = findViewById(R.id.addOrSub)

        val numberClickListener = { number: String ->
            inputText += number
            expression += number
            expressionTextView.text = expression
        }

        buttonZero.setOnClickListener { numberClickListener("0") }
        buttonOne.setOnClickListener { numberClickListener("1") }
        buttonTwo.setOnClickListener { numberClickListener("2") }
        buttonThree.setOnClickListener { numberClickListener("3") }
        buttonFour.setOnClickListener { numberClickListener("4") }
        buttonFive.setOnClickListener { numberClickListener("5") }
        buttonSix.setOnClickListener { numberClickListener("6") }
        buttonSeven.setOnClickListener { numberClickListener("7") }
        buttonEight.setOnClickListener { numberClickListener("8") }
        buttonNine.setOnClickListener { numberClickListener("9") }
        buttonDot.setOnClickListener { numberClickListener(".") }

        buttonCE.setOnClickListener {
            inputText = ""
            expression = ""
            expressionTextView.text = "0"
        }

        buttonClear.setOnClickListener {
            if (inputText.isNotEmpty()) {
                inputText = inputText.dropLast(1)
                expression = expression.dropLast(1)
                expressionTextView.text = if (expression.isEmpty()) "0" else expression
            }
        }

        val operatorClickListener = { operator: String ->
            if (inputText.isNotEmpty()) {
                expression += operator
                inputText = ""
                expressionTextView.text = expression
            }
        }

        buttonAdd.setOnClickListener { operatorClickListener("+") }
        buttonSub.setOnClickListener { operatorClickListener("-") }
        buttonMul.setOnClickListener { operatorClickListener("*") }
        buttonDiv.setOnClickListener { operatorClickListener("/") }

        buttonEqual.setOnClickListener {
            if (expression.isNotEmpty()) {
                val result = calculateResult(expression)
                resultTextView.text = result.toString()
                expression = result.toString()
                inputText = ""
            }
        }
    }

    private fun calculateResult(expression: String): Double {
        return try {
            val parts = expression.split("(?<=[-+*/])|(?=[-+*/])".toRegex())
            val numbers = mutableListOf<Double>()
            val operators = mutableListOf<String>()

            for (part in parts) {
                when {
                    part.isEmpty() -> continue
                    part.matches("-?\\d+(\\.\\d+)?".toRegex()) -> {
                        numbers.add(part.toDouble())
                    }
                    else -> {
                        operators.add(part)
                    }
                }
            }

            var i = 0
            while (i < operators.size) {
                when (operators[i]) {
                    "*" -> {
                        val result = numbers[i] * numbers[i + 1]
                        numbers[i] = result
                        numbers.removeAt(i + 1)
                        operators.removeAt(i)
                    }
                    "/" -> {
                        val result = numbers[i] / numbers[i + 1]
                        numbers[i] = result
                        numbers.removeAt(i + 1)
                        operators.removeAt(i)
                    }
                    else -> i++
                }
            }

            var total = numbers[0]
            for (j in operators.indices) {
                when (operators[j]) {
                    "+" -> total += numbers[j + 1]
                    "-" -> total -= numbers[j + 1]
                }
            }

            total
        } catch (e: Exception) {
            0.0
        }
    }
}
