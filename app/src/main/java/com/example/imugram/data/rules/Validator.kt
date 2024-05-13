package com.example.imugram.data.rules

object Validator {

    fun validateUserName(uName: String):ValidationResult{
        return ValidationResult(
            (uName.isNotEmpty() && uName.length > 4)
        )
    }
    fun validateEmail(eMail: String):ValidationResult{
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        return ValidationResult(
            eMail.matches(emailRegex.toRegex())
        )
    }
    fun validateDOB(dOB: String):ValidationResult{
        return ValidationResult(
            (dOB.isNotEmpty())
        )
    }
    fun validatePassword(pWord: String):ValidationResult{
        val minLength = 5
        val hasUpperCase = pWord.any { it.isUpperCase() }
        val hasLowerCase = pWord.any { it.isLowerCase() }
        val hasDigit = pWord.any { it.isDigit() }
        val hasSpecialChar = pWord.any { !it.isLetterOrDigit() }

        val isStrongPassword = pWord.length >= minLength &&
                hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar

        val isMediumPassword = pWord.length >= minLength &&
                ((hasUpperCase && hasLowerCase) || (hasLowerCase && hasDigit) || (hasUpperCase && hasDigit) || (hasUpperCase && hasSpecialChar) || (hasLowerCase && hasSpecialChar) || (hasDigit && hasSpecialChar))

        return ValidationResult(isStrongPassword || isMediumPassword)

    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult {
        return ValidationResult(statusValue)
    }

}
data class ValidationResult(val status: Boolean = false)