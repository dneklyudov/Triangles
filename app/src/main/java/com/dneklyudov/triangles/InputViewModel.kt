package com.dneklyudov.triangles

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class InputViewModel: ViewModel() {

    private val _len1 = MutableLiveData<String>("0")
    val len1: LiveData<String>
        get() = _len1

    private val _len2 = MutableLiveData<String>("0")
    val len2: LiveData<String>
        get() = _len2

    private val _len3 = MutableLiveData<String>("0")
    val len3: LiveData<String>
        get() = _len3

    private val _isButtonActive = MutableLiveData<Boolean>(false)
    val isButtonActive: LiveData<Boolean>
        get() = _isButtonActive

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _errorMessageVisibility = MutableLiveData<Int>(View.GONE)
    val errorMessageVisibility: LiveData<Int>
        get() = _errorMessageVisibility

    private var isChanged1: Boolean = false
    private var isChanged2: Boolean = false
    private var isChanged3: Boolean = false

    private var n1: Int = 0
    private var n2: Int = 0
    private var n3: Int = 0

    private fun areValuesCorrect() {

        n1 = when (isChanged1) {
            true -> getNumber(_len1.value.toString())
            else -> 0
        }
        n2 = when (isChanged2) {
            true -> getNumber(_len2.value.toString())
            else -> 0
        }
        n3 = when (isChanged3) {
            true -> getNumber(_len3.value.toString())
            else -> 0
        }

        var tempErrorMessage = ""
        var tempIsButtonActive = true

        if (isChanged1 && n1 == 0) {
            tempErrorMessage += "Введите длину первой стороны треугольника (целое положительное число).\n"
            tempIsButtonActive = false
        }
        if (isChanged2 && n2 == 0) {
            tempErrorMessage += "Введите длину второй стороны треугольника (целое положительное число).\n"
            tempIsButtonActive = false
        }
        if (isChanged3 && n3 == 0) {
            tempErrorMessage += "Введите длину третьей стороны треугольника (целое положительное число).\n"
            tempIsButtonActive = false
        }

        if (isChanged1 && isChanged2 && isChanged3 && n1 > 0 && n2 > 0 && n3 > 0) {
            if ((n1 + n2 <= n3) || (n1 + n3 <= n2) || (n2 + n3 <= n1)) {
                tempIsButtonActive = false
                tempErrorMessage += "Сумма длин двух сторон должна быть больше длины третьей стороны."
            }
        } else {
            tempIsButtonActive = false
        }

        _isButtonActive.value = tempIsButtonActive
        _errorMessage.value = tempErrorMessage.trim()
        _errorMessageVisibility.value = getErrorMessageVisibility(_errorMessage.value.toString())

    }

    private fun getErrorMessageVisibility(s: String): Int {
        return when (s) {
            "" -> View.GONE
            else -> View.VISIBLE
        }
    }

    fun changeLen1(s: CharSequence) {
        if (_len1.value == s.toString()) return
        isChanged1 = true
        _len1.value = s.toString()
        areValuesCorrect()
    }

    fun changeLen2(s: CharSequence) {
        if (_len2.value == s.toString()) return
        isChanged2 = true
        _len2.value = s.toString()
        areValuesCorrect()
    }

    fun changeLen3(s: CharSequence) {
        if (_len3.value == s.toString()) return
        isChanged3 = true
        _len3.value = s.toString()
        areValuesCorrect()
    }

    fun resultMessage(): String {
        return if ((n1 == n2) && (n2 == n3)) {
            "Полученный треугольник равносторонний."
        } else if ((n1 == n2) || (n2 == n3) || (n1 == n3)) {
            "Полученный треугольник равнобедренный."
        } else {
            "Полученный треугольник разносторонний."
        }
    }

    private fun getNumber(str: String): Int {
        try {
            val parsedInt = str.toInt()
            if (parsedInt > 0) {
                return parsedInt
            }
            return 0
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}