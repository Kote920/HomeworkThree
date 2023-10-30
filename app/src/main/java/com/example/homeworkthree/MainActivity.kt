package com.example.homeworkthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.homeworkthree.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            binding.tvNotify.text = null
            if (validation(binding.etEmail, binding.etUsername, binding.etFirstName, binding.etLastName,
                binding.etAge, binding.tvNotify))

                binding.linearLayout.visibility = View.INVISIBLE

                binding.tvEmail.text = binding.etEmail.text.toString()
                binding.tvUsername.text = binding.etUsername.text.toString()
                binding.tvFirstLastName.text =
                    getString(
                        R.string.Succ,
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString()
                    )
                binding.tvAge.text = binding.etAge.text.toString()
                binding.linearSuccess.visibility = View.VISIBLE

        }

        binding.btnClear.setOnLongClickListener {
            clearForm(binding.etEmail, binding.etUsername, binding.etFirstName, binding.etLastName,
                binding.etAge)

            true
        }

        binding.btnAgain.setOnClickListener {
            binding.linearLayout.visibility = View.VISIBLE
            clearForm(binding.etEmail, binding.etUsername, binding.etFirstName, binding.etLastName,
                binding.etAge)
            binding.linearSuccess.visibility = View.INVISIBLE
        }
    }
    fun validation(email: AppCompatEditText, username: AppCompatEditText, firstName : AppCompatEditText,
                   lastName: AppCompatEditText, age: AppCompatEditText, notification: AppCompatTextView): Boolean{
        if (!isFilled(email, username, firstName, lastName, age)){
            notification.text = getString(R.string.please_fill_everything)
            return false
        }
        if(!isEmailValid(email)){
            notification.text = getString(R.string.please_enter_a_valid_e_mail)
            return false
        }
        if(!isUsernameValid(username)){
            notification.text = getString(R.string.please_enter_a_valid_username_at_least_10_symbols)
            return false
        }
        if (!isAgeValid(age)){
            notification.text = getString(R.string.plese_enter_a_valid_age)
            return false
        }

        return true

    }

    fun clearForm(email: AppCompatEditText, username: AppCompatEditText, firstName: AppCompatEditText,
                  lastName: AppCompatEditText, age: AppCompatEditText){
        email.editableText.clear()
        username.editableText.clear()
        firstName.editableText.clear()
        lastName.editableText.clear()
        age.editableText.clear()

    }

    fun isFilled(vararg input:AppCompatEditText): Boolean{
        input.forEach {
            if (it.text.toString().trim().isEmpty()){
                return false
            }
        }
        return true
    }

    fun isUsernameValid(input: AppCompatEditText): Boolean{
        return input.text.toString().trim().length >= 10
    }

    fun isEmailValid(input: AppCompatEditText): Boolean {
        val email = input.text.toString()
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun isAgeValid(input: AppCompatEditText): Boolean{
        try {


        val age = input.text.toString().toInt()
        return age > 0
        }catch (e: NumberFormatException){
            return false
        }
    }
}