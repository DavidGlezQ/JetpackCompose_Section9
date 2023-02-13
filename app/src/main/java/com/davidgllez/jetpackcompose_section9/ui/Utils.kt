package com.davidgllez.jetpackcompose_section9.ui

/**
 * Created by davidgonzalez on 13/02/23
 */
object Utils {
    fun joinData(vararg fields: String): String {
        var result = ""

        fields.forEach { field ->
            if (field.isNotEmpty()) {
                result += "$field\n"
            }
        }
        return result
    }
}