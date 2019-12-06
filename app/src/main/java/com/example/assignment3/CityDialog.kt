package com.example.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_city_dialog.view.*
import java.util.*

class CityDialog : DialogFragment() {
    interface CityHandler {
        fun cityCreated(string: String)
    }

    private lateinit var cityHandler: CityHandler


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CityHandler) { //checking if the activity implements the TodoHandler interface, will throw an exception if it does not
            cityHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the TodoHandlerInterface"
            )
        }

    }

    private lateinit var etCityName: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog { //
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New City")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_city_dialog, null
        )
        etCityName = rootView.etCity
        builder.setView(rootView)


        builder.setPositiveButton("OK") { _, _ ->
            // empty
        }

        return builder.create()
    }

    override fun onResume() {
        super.onResume()
        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etCityName.text.isNotEmpty()) { // has the user filled the dialog?
                handleCityCreate()
                (dialog as AlertDialog).dismiss()
            } else {
                etCityName.error = "This field cannot be empty"
            }
        }


    }

    private fun handleCityCreate() {

        cityHandler.cityCreated(etCityName.text.toString().formatEachWord())

    }

    private fun String.formatEachWord(): String =
        toLowerCase(Locale.US).split(" ").joinToString(" ") { it.capitalize() }


}