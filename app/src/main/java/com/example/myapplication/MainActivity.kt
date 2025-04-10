package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var validator: Validator = Validator.Success

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.etLogIn.setText("shdv@gmail.com")
        binding.etPassword.setText("ashgdchascdahsvd")

        binding.btnLogIn.setOnClickListener { validateFields() }
    }

    private fun validateFields() {
        binding.apply {
            if (etLogIn.text.isEmpty() || etPassword.text.isEmpty())
                validator = Validator.Error(ErrorCases.EmptyFields("Text Fields cannot be empty"))


            if (etLogIn.text.endsWith("@gmail.com") && etPassword.text.length >= 8) {
                validator = Validator.Success
                val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
                    putExtra("logIng", etLogIn.text.toString())
                    putExtra("password", etPassword.text.toString())
                }

                startActivity(intent)
                finish()
            } else {
                validator = when {
                    etLogIn.text.endsWith("@gmail.com")
                        .not() && etPassword.text.length < 8 -> Validator.Error(
                        ErrorCases.IncorrectEmailAndPassword("Email and password are incorrect")
                    )

                    etLogIn.text.endsWith("@gmail.com")
                        .not() -> Validator.Error(ErrorCases.IncorrectEmail("Email is incorrect"))

                    etPassword.text.length < 8 -> Validator.Error(ErrorCases.IncorrectPassword("Password is incorrect"))
                    else -> Validator.Error(ErrorCases.GeneralError("Something went wrong"))
                }
            }

            tvErrorString.isVisible = validator != Validator.Success
            tvErrorString.text = when (validator) {
                is Validator.Error -> {
                    when (val errorCase = (validator as Validator.Error).errorCase) {
                        is ErrorCases.EmptyFields -> errorCase.errorMessage
                        is ErrorCases.GeneralError -> errorCase.errorMessage
                        is ErrorCases.IncorrectEmail -> errorCase.errorMessage
                        is ErrorCases.IncorrectEmailAndPassword -> errorCase.errorMessage
                        is ErrorCases.IncorrectPassword -> errorCase.errorMessage
                    }
                }

                Validator.Success -> ""
            }
        }
    }

    sealed interface Validator {
        data object Success : Validator
        data class Error(val errorCase: ErrorCases) : Validator
    }

    sealed interface ErrorCases {
        data class EmptyFields(val errorMessage: String) : ErrorCases
        data class IncorrectEmail(val errorMessage: String) : ErrorCases
        data class IncorrectPassword(val errorMessage: String) : ErrorCases
        data class IncorrectEmailAndPassword(val errorMessage: String) : ErrorCases
        data class GeneralError(val errorMessage: String) : ErrorCases
    }
}