package com.dneklyudov.triangles

import androidx.lifecycle.ViewModel

class InputViewModel: ViewModel() {

    fun areValuesCorrect(): Boolean {
        return true
    }

    fun resultMessage(): String {
        var message = ""
        message = "Полученный треугольник равносторонний"
        return message
    }
}