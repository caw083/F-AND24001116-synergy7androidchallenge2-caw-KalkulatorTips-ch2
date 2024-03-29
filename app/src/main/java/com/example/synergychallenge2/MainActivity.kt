package com.example.synergychallenge2
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import kotlin.math.roundToInt



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonEnglish = findViewById<Button>(R.id.btnEnglish)
        val buttonIndonesian = findViewById<Button>(R.id.btnIndonesian)
        val buttonJapanese = findViewById<Button>(R.id.btnJapanese)

        // Set click listeners for buttons
        buttonEnglish.setOnClickListener {setLocale("en") }
        buttonIndonesian.setOnClickListener {setLocale("id") }
        buttonJapanese.setOnClickListener {setLocale("ja")}


        val costOfServiceEditText: EditText = findViewById(R.id.cost_of_service)
        val tipOptionsRadioGroup: RadioGroup = findViewById(R.id.tip_options)
        val calculateButton: Button = findViewById(R.id.calculate_button)
        val tipAmountButtonView: Button = findViewById(R.id.answer_of_calculate)
        val roundUpSwitch: Switch = findViewById(R.id.round_up_switch)

        calculateButton.setOnClickListener {
            // Retrieve the cost of service from the EditText
            val costOfServiceString = costOfServiceEditText.text.toString()
            val costOfService = costOfServiceString.toDoubleOrNull() ?: return@setOnClickListener

            // Determine which radio button is selected and get the tip percentage
            val tipPercentage = when (tipOptionsRadioGroup.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 20
                R.id.option_eighteen_percent -> 18
                R.id.option_fifteen_percent -> 15
                R.id.option_ten_percent -> 10
                R.id.option_five_percent -> 5
                else -> return@setOnClickListener
            }

            // Calculate the tip amount
            var tipAmount = (costOfService * tipPercentage) / 100 + costOfService
            if (roundUpSwitch.isChecked) {
                tipAmount = tipAmount.roundToInt().toDouble()
            } else {
                tipAmount = (costOfService * tipPercentage) / 100 + costOfService
            }
            
            val formattedString = getString(R.string.service_payment, tipAmount)
            tipAmountButtonView.text = formattedString
        }






    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate() // Restart activity to apply the new configuration
    }
}
