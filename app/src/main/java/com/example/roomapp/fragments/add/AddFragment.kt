package com.example.roomapp.fragments.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.Password
import com.example.roomapp.data.PasswordViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.security.MessageDigest

class AddFragment : Fragment() {

    private lateinit var mPasswordViewModel: PasswordViewModel
    private var password = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mPasswordViewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)


        view.hash_btn.setOnClickListener{
            password = hashPassword(password_et.text.toString().trim()).toString().trim()
            hashPassword_et.text = password
        }

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }
    private fun hashPassword(base: String): String {
        try {
            val digest: MessageDigest = MessageDigest.getInstance(hashtype_et.text.toString())
            val hash: ByteArray = digest.digest(base.toByteArray(Charsets.UTF_8))
            val hexString = StringBuilder()
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }
    private fun insertDataToDatabase() {
        val names = resources.getStringArray(R.array.names)
        val firstName = password_et.text.toString()
        val lastName = hashPassword_et.text.toString()
        val age = hashtype_et.text.toString()

        if(inputCheck(firstName, lastName, age)){
            // Create User Object
            val password = Password(0, firstName, lastName, age)
            // Add Data to Database
            mPasswordViewModel.addPassword(password)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}