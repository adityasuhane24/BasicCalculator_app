package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var lastNumeric=false
    var lastDot=false



    fun onDigit(view: View){
       tvInput.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View){
        tvInput.text=""
        lastDot=false
        lastNumeric=false
    }



    fun onDecimalPoint(view:View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    fun onEqual(view:View){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix=""
            if(tvInput.text.startsWith("-")){
                prefix="-"
                tvValue=tvValue.substring(1)
            }
            try {
                if(tvValue.contains("-")){
                    val z=tvValue.split("-")
                    var one=z[0]
                    var two=z[1]

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }
                    tvInput.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }else  if(tvValue.contains("+")){
                    val z=tvValue.split("+")
                    var one=z[0]
                    var two=z[1]

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }
                    tvInput.text= removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }else  if(tvValue.contains("/")){
                    val z=tvValue.split("/")
                    var one=z[0]
                    var two=z[1]

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }
                    tvInput.text=removeZeroAfterDot((
                            one.toDouble() / two.toDouble()).toString())
                }else  if(tvValue.contains("*")){
                    val z=tvValue.split("*")
                    var one=z[0]
                    var two=z[1]

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }
                    tvInput.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
                    e.printStackTrace()
            }

        }
    }

    private fun removeZeroAfterDot(result:String) : String{
        var result= result
            if(result.contains(".0")){
            result = result.substring(0,result.length-2)
            }
        return result
    }

    private fun isOperatorAdded(value:String) : Boolean {
       return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("-") || value.contains("+") || value.contains("*")
        }
    }
}