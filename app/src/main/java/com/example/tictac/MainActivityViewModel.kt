package com.example.tictac

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var count = 0
    var countWin = ""
    var colorOfButton = 0

    //var buttonAll:Array<MutableLiveData<String>> = arrayOf(MutableLiveData<String>())
    val buttonr1Text = MutableLiveData<String>()
    val buttonm1Text = MutableLiveData<String>()
    val buttonl1Text = MutableLiveData<String>()
    val buttonr2Text = MutableLiveData<String>()
    val buttonm2Text = MutableLiveData<String>()
    val buttonl2Text = MutableLiveData<String>()
    val buttonr3Text = MutableLiveData<String>()
    val buttonm3Text = MutableLiveData<String>()
    val buttonl3Text = MutableLiveData<String>()
    var newText = ""

    var buttonColorr1 = MutableLiveData<Int>()
    var buttonColorm1 = MutableLiveData<Int>()
    var buttonColorl1 = MutableLiveData<Int>()
    var buttonColorr2 = MutableLiveData<Int>()
    var buttonColorm2 = MutableLiveData<Int>()
    var buttonColorl2 = MutableLiveData<Int>()
    var buttonColorr3 = MutableLiveData<Int>()
    var buttonColorm3 = MutableLiveData<Int>()
    var buttonColorl3 = MutableLiveData<Int>()

    fun clear() {
        count = 0
        countWin = ""
        buttonr1Text.value = ""
        buttonm1Text.value = ""
        buttonl1Text.value = ""
        buttonr2Text.value = ""
        buttonm2Text.value = ""
        buttonl2Text.value = ""
        buttonr3Text.value = ""
        buttonm3Text.value = ""
        buttonl3Text.value = ""
        newText = ""
    }

    fun onClick(button: String) {
        newText = if (count % 2 == 0) "X" else "O"
        var buttonText = when (button) {
            "buttonr1" -> buttonr1Text
            "buttonm1" -> buttonm1Text
            "buttonl1" -> buttonl1Text
            "buttonr2" -> buttonr2Text
            "buttonm2" -> buttonm2Text
            "buttonl2" -> buttonl2Text
            "buttonr3" -> buttonr3Text
            "buttonm3" -> buttonm3Text
            "buttonl3" -> buttonl3Text
            else -> null
        }
        buttonText?.value = newText

        colorOfButton = if (count % 2 == 0) R.color.blue else R.color.orange
        var buttonColor = when (button) {
            "buttonr1" -> buttonColorr1
            "buttonm1" -> buttonColorm1
            "buttonl1" -> buttonColorl1
            "buttonr2" -> buttonColorr2
            "buttonm2" -> buttonColorm2
            "buttonl2" -> buttonColorl2
            "buttonr3" -> buttonColorr3
            "buttonm3" -> buttonColorm3
            "buttonl3" -> buttonColorl3
            else -> null
        }

        buttonColor?.value = colorOfButton

        count++
    }

    fun xOrO(): String {
        var xOrO: String = countWin// % 2 == 0
        if (xOrO == "O") return "Победили нолики!"
        if (xOrO == "X") return "Победили крестики!"
        else return "Ничья!"
    }

    fun win(
        buttonr1: String,
        buttonr2: String,
        buttonr3: String,
        buttonm1: String,
        buttonm2: String,
        buttonm3: String,
        buttonl1: String,
        buttonl2: String,
        buttonl3: String
    ): Boolean {
        val buttons = arrayOf(
            buttonr1,
            buttonr2,
            buttonr3,
            buttonm1,
            buttonm2,
            buttonm3,
            buttonl1,
            buttonl2,
            buttonl3
        )
        val patterns = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (pattern in patterns) {
            val (a, b, c) = pattern
            if (buttons[a] == buttons[b] && buttons[b] == buttons[c] && buttons[a].isNotBlank()) {
                countWin = buttons[a]
                count = 0
                return true

            }
        }

        if (buttons.all { it.isNotBlank() }) {
            countWin = "Ничья"
            count = 0
            return true
        }
        return false
    }
}