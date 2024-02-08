package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<View>(R.id.textView) as TextView
        val textView2 = findViewById<View>(R.id.textView2) as TextView
        val textView3 = findViewById<View>(R.id.textView3) as TextView
        val textView4 = findViewById<View>(R.id.textView4) as TextView
        val textView5 = findViewById<View>(R.id.textView5) as TextView
        val textView6 = findViewById<View>(R.id.textView6) as TextView
        
        //pressure value placeholder/initialization
        val pressureOne = 0
        val pressureTwo = 0
        val pressureThree = 0
        val pressureThreshold = 65

        //calling method to get pressure levels
        val pressureLevels = pressureDetection(pressureOne, pressureTwo, pressureThree)

        //delay method
        val runnable = Runnable {
            textView3.text = "Moisture Detected: " + moistureDetection()
            textView2.text = "Pressure level on sensor one: " + pressureLevels[0]
            textView6.text = "Pressure level on sensor two: " + pressureLevels[1]
            textView5.text = "Pressure level on sensor three: " + pressureLevels[2]
            if (pressureLevels[0] > pressureThreshold || pressureLevels[1] > pressureThreshold || pressureLevels[2] > pressureThreshold) {
                textView4.text = "Pressure levels Exceede Threshold!"
            } else {
                textView4.text = "Pressure levels are Okay!"
            }
            textView.text = "Reading Done."
        }
        
        //setting up parameters for the delay method
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 15000)
        textView.text = "Reading Please Wait..."
    }

    companion object {

        //Getting true or false moisture value
        fun moistureDetection(): Boolean {
            // Using the random library class
            val randomNum = Random()

            // Initializing variables for moisture
            val moisture: Boolean

            // 50/50 chance it will land as true or false
            val result: Int = randomNum.nextInt(2)
            moisture = result == 0

            // returns the value as either true or false
            return moisture
        }

        //getting random pressure value
        fun pressureDetection(
            pressureNumOne: Int,
            pressureNumTwo: Int,
            pressureNumThree: Int
        ): IntArray {

            // Initializing pressure limit, threshold, and sensors
            // How we get randomized integer
            var pressureNumOne = pressureNumOne
            var pressureNumTwo = pressureNumTwo
            var pressureNumThree = pressureNumThree
            val pressureRand = Random()

           // How we get randomized integer
            val pressureLimit = 101
            val pressureThreshold = 65
            pressureNumOne = pressureRand.nextInt(pressureLimit)
            pressureNumTwo = pressureRand.nextInt(pressureLimit)
            pressureNumThree = pressureRand.nextInt(pressureLimit)

            //adding in the random values and putting them into an array
            val arr = IntArray(3)
            arr[0] = pressureNumOne
            arr[1] = pressureNumTwo
            arr[2] = pressureNumThree

            // print statement to console to show result for pressure level
            println("Pressure level on sensor one: $pressureNumOne")
            println("Pressure level on sensor two: $pressureNumTwo")
            println("Pressure level on sensor three: $pressureNumThree")

            //return random values
            return arr
        }
    }
}
