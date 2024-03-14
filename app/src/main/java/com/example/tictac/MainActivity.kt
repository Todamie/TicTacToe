package com.example.tictac

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tic_tac)



        val buttonr1: Button = findViewById(R.id.buttonr1)
        //buttonr1.textSize = screenWidth * 0.01f
        val buttonr2: Button = findViewById(R.id.buttonr2)
        val buttonr3: Button = findViewById(R.id.buttonr3)

        val buttonm1: Button = findViewById(R.id.buttonm1)
        val buttonm2: Button = findViewById(R.id.buttonm2)
        val buttonm3: Button = findViewById(R.id.buttonm3)

        val buttonl1: Button = findViewById(R.id.buttonl1)
        val buttonl2: Button = findViewById(R.id.buttonl2)
        val buttonl3: Button = findViewById(R.id.buttonl3)

        val buttonBot: Button = findViewById(R.id.buttonPlayWithBot)
        buttonBot.text = "Играть с ботом"
       /* val text:TextView = findViewById(R.id.id)
        text.text = "Main"*/

        vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        vm.buttonr1Text.observe(this, Observer { buttonr1.text = it })
        vm.buttonr2Text.observe(this, Observer { buttonr2.text = it })
        vm.buttonr3Text.observe(this, Observer { buttonr3.text = it })
        vm.buttonl1Text.observe(this, Observer { buttonl1.text = it })
        vm.buttonl2Text.observe(this, Observer { buttonl2.text = it })
        vm.buttonl3Text.observe(this, Observer { buttonl3.text = it })
        vm.buttonm1Text.observe(this, Observer { buttonm1.text = it })
        vm.buttonm2Text.observe(this, Observer { buttonm2.text = it })
        vm.buttonm3Text.observe(this, Observer { buttonm3.text = it })

        vm.buttonColorr1.observe(this, Observer {findViewById<Button>(R.id.buttonr1).setTextColor(resources.getColor(it))})
        vm.buttonColorm1.observe(this, Observer {findViewById<Button>(R.id.buttonm1).setTextColor(resources.getColor(it))})
        vm.buttonColorl1.observe(this, Observer {findViewById<Button>(R.id.buttonl1).setTextColor(resources.getColor(it))})
        vm.buttonColorr2.observe(this, Observer {findViewById<Button>(R.id.buttonr2).setTextColor(resources.getColor(it))})
        vm.buttonColorm2.observe(this, Observer {findViewById<Button>(R.id.buttonm2).setTextColor(resources.getColor(it))})
        vm.buttonColorl2.observe(this, Observer {findViewById<Button>(R.id.buttonl2).setTextColor(resources.getColor(it))})
        vm.buttonColorr3.observe(this, Observer {findViewById<Button>(R.id.buttonr3).setTextColor(resources.getColor(it))})
        vm.buttonColorm3.observe(this, Observer {findViewById<Button>(R.id.buttonm3).setTextColor(resources.getColor(it))})
        vm.buttonColorl3.observe(this, Observer {findViewById<Button>(R.id.buttonl3).setTextColor(resources.getColor(it))})





        fun setClickListener(button: Button) {
            button.setOnClickListener {
                vm.onClick(resources.getResourceEntryName(button.id))
                if (vm.win(
                        buttonr1.text.toString(),
                        buttonr2.text.toString(),
                        buttonr3.text.toString(),
                        buttonm1.text.toString(),
                        buttonm2.text.toString(),
                        buttonm3.text.toString(),
                        buttonl1.text.toString(),
                        buttonl2.text.toString(),
                        buttonl3.text.toString()
                    )
                ) {
                    showWinnerDialog(vm.xOrO())
                }
                button.isEnabled = false
            }
        }

        setClickListener(buttonr1)
        setClickListener(buttonr2)
        setClickListener(buttonr3)
        setClickListener(buttonm1)
        setClickListener(buttonm2)
        setClickListener(buttonm3)
        setClickListener(buttonl1)
        setClickListener(buttonl2)
        setClickListener(buttonl3)


        buttonBot.setOnClickListener{
            intent = Intent(this, PlayWithBot::class.java)
            startActivity(intent)
        }
    }

    private fun showWinnerDialog(winner: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("$winner")
            .setCancelable(false)
            .setPositiveButton("Новая игра") { dialog, id ->
                vm.clear()
                recreate()
            }
            /*.setNegativeButton("Закрыть приложение") { dialog, id ->
                finish()
            }*/

        val alert = dialogBuilder.create()
        alert.setTitle("Игра окончена")
        alert.show()
    }
}