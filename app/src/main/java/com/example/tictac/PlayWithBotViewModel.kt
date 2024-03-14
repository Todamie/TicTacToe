package com.example.tictac

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PlayWithBotViewModel : ViewModel() {
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

    var massivOfButtons = mutableListOf(
        buttonr1Text, buttonm1Text, buttonl1Text, buttonr2Text, buttonm2Text, buttonl2Text,
        buttonr3Text, buttonm3Text, buttonl3Text
    )
    var massivOfColors = mutableListOf(
        buttonColorr1, buttonColorm1, buttonColorl1, buttonColorr2, buttonColorm2, buttonColorl2,
        buttonColorr3, buttonColorm3, buttonColorl3
    )

    var massivOfButtonsName = mutableListOf(
        "buttonr1Text",
        "buttonm1Text",
        "buttonl1Text",
        "buttonr2Text",
        "buttonm2Text",
        "buttonl2Text",
        "buttonr3Text",
        "buttonm3Text",
        "buttonl3Text"
    )

    var massivSize = MutableLiveData<String>()
    var massivColor = MutableLiveData<String>()
    var massivId = MutableLiveData<String?>()
    var i: Int = 0

    fun botTurn(button: String) {
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

        massivOfButtons.remove(buttonText)//8
        massivOfColors.remove(buttonColor)

        /*if (massivOfButtons.isNotEmpty()) {
            //val randomIndex = Random.nextInt(massivOfButtons.size)
            val randomIndex = massivOfButtons.indices.random() //8
            //massivId.value = massivOfButtonsName[randomIndex]
            massivOfColors[randomIndex].value = R.color.orange
            massivOfButtons[randomIndex].value = "O"


            //massivColor.value = massivOfColors[randomIndex].value.toString()
            massivOfButtons.removeAt(randomIndex)
            massivOfColors.removeAt(randomIndex)
        }
        massivSize.value = massivOfButtons.size.toString()*/

        if (i == 0) {
            val randomIndex = massivOfButtons.indices.random()
            massivOfColors[randomIndex].value = R.color.orange
            massivOfButtons[randomIndex].value = "O"
            massivOfButtons.removeAt(randomIndex)
            massivOfColors.removeAt(randomIndex)
            i++
        } else {
            val buttons = arrayOf(
                buttonr1Text,
                buttonm1Text,
                buttonl1Text,
                buttonr2Text,
                buttonm2Text,
                buttonl2Text,
                buttonr3Text,
                buttonm3Text,
                buttonl3Text,
            )
            val buttonsColor = arrayOf(
                buttonColorr1,
                buttonColorm1,
                buttonColorl1,
                buttonColorr2,
                buttonColorm2,
                buttonColorl2,
                buttonColorr3,
                buttonColorm3,
                buttonColorl3
            )
            val patterns = arrayOf(
                intArrayOf(0, 1, 2),//1 линия
                intArrayOf(3, 4, 5),//2 линия
                intArrayOf(6, 7, 8),//3 линия
                intArrayOf(0, 3, 6),//левая линия
                intArrayOf(1, 4, 7),//средняя линия
                intArrayOf(2, 5, 8),//правая линия
                intArrayOf(0, 4, 8),//диагональ слева направо
                intArrayOf(2, 4, 6)//диагональ справа налево
            )

            for (pattern in patterns) {
                val (a, b, c) = pattern
                when{
                    (buttons[a].value == buttons[b].value && buttons[b].value !== buttons[c].value && buttons[a].value == "X" && buttons[c].value.isNullOrEmpty()) -> {
                        buttons[c].value = "O"
                        buttonsColor[c].value = R.color.orange
                        massivOfButtons.remove(buttons[c])
                        massivOfColors.remove(buttonsColor[c])
                        break
                    }
                    (buttons[a].value !== buttons[b].value && buttons[b].value == buttons[c].value && buttons[b].value == "X" && buttons[a].value.isNullOrEmpty()) -> {
                        buttons[a].value = "O"
                        buttonsColor[a].value = R.color.orange
                        massivOfButtons.remove(buttons[a])
                        massivOfColors.remove(buttonsColor[a])
                        break
                    }
                    (buttons[a].value == buttons[c].value && buttons[b].value !== buttons[c].value && buttons[a].value == "X" && buttons[b].value.isNullOrEmpty()) -> {
                        buttons[b].value = "O"
                        buttonsColor[b].value = R.color.orange
                        massivOfButtons.remove(buttons[b])
                        massivOfColors.remove(buttonsColor[b])
                        break
                    }
                    else -> continue/*{
                        val randomIndex = massivOfButtons.indices.random()
                        massivOfColors[randomIndex].value = R.color.orange
                        massivOfButtons[randomIndex].value = "O"
                        massivOfButtons.removeAt(randomIndex)
                        massivOfColors.removeAt(randomIndex)
                    }*/

                }
                /*if (buttons[a].value == buttons[b].value && buttons[b].value !== buttons[c].value && buttons[a].value == "X" && buttons[c].value.isNullOrEmpty()) {
                    buttons[c].value = "O"
                    buttonsColor[c].value = R.color.orange
                    massivOfButtons.remove(buttons[c])
                    massivOfColors.remove(buttonsColor[c])
                } else if (buttons[a].value !== buttons[b].value && buttons[b].value == buttons[c].value && buttons[b].value == "X" && buttons[a].value.isNullOrEmpty()) {
                    buttons[a].value = "O"
                    buttonsColor[a].value = R.color.orange
                    massivOfButtons.remove(buttons[a])
                    massivOfColors.remove(buttonsColor[a])
                } else if (buttons[a].value == buttons[c].value && buttons[b].value !== buttons[c].value && buttons[a].value == "X" && buttons[b].value.isNullOrEmpty()) {
                    buttons[b].value = "O"
                    buttonsColor[b].value = R.color.orange
                    massivOfButtons.remove(buttons[b])
                    massivOfColors.remove(buttonsColor[b])
                } else {
                    continue
                }*/
            }


        }
    }


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
        massivOfButtons = mutableListOf(
            buttonr1Text, buttonm1Text, buttonl1Text, buttonr2Text, buttonm2Text, buttonl2Text,
            buttonr3Text, buttonm3Text, buttonl3Text
        )
        massivOfColors = mutableListOf(
            buttonColorr1,
            buttonColorm1,
            buttonColorl1,
            buttonColorr2,
            buttonColorm2,
            buttonColorl2,
            buttonColorr3,
            buttonColorm3,
            buttonColorl3
        )
        i = 0
    }

    fun onClick(button: String) {
        newText = "X"//if (count % 2 == 0) "X" else "O"
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

        colorOfButton = R.color.blue//if (count % 2 == 0) R.color.blue else R.color.orange
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