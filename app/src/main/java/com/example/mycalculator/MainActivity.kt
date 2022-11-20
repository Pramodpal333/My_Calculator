package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvResult:TextView? = null
    private var isNumber:Boolean = false
    private var isDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
    }

    fun onDigit(view: View){
        tvResult?.append((view as Button).text)
        isNumber = true
        isDot = false
    }

    fun onClear(view: View){
        tvResult?.text = ""
    }

    fun onDecimal(view: View){
        if (isNumber && !isDot){
            tvResult?.append((view as Button).text)
            isDot = true
            isNumber = false
        }
    }

    fun onOperator(view: View){
        tvResult?.text?.let {
            if (isNumber && !isOperatorAdded(it.toString())){
                tvResult?.append((view as Button).text)
                isNumber = false
                isDot =false
            }
        }

    }



  private fun isOperatorAdded(value:String):Boolean {
      return if (value.startsWith("-")){
          false
      }
      else{
                     value.contains("/")
                  || value.contains("+")
                  || value.contains("*")
                  || value.contains("-")

      }
  }


    fun onEqual(view: View){
        if (isNumber){
            var tvValue = tvResult?.text.toString()

            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue =tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult?.text = removeDot((one.toDouble() - two.toDouble()).toString())
                } else  if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult?.text = removeDot((one.toDouble() + two.toDouble()).toString())
                } else  if (tvValue.contains("x")){
                    val splitValue = tvValue.split("x")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult?.text = removeDot((one.toDouble() * two.toDouble()).toString())
                } else  if (tvValue.contains("÷")){
                    val splitValue = tvValue.split("÷")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult?.text = removeDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeDot(result:String):String{
        var value = result
        if (value.contains(".0"))
            value =result.substring(0,result.length-2)
        return value
    }
}