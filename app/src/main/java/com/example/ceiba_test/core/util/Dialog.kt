package com.example.ceiba_test.core.util

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.ceiba_test.R
import com.example.ceiba_test.databinding.DialogInfoBinding

class Dialog:DialogFragment() {

    var model: Model = Model()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity ?: return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        val binding = DialogInfoBinding.inflate(activity.layoutInflater)
        binding.clase = this
        binding.model = model
        builder.setView(binding.root)

        return builder.create()

    }

    fun accept() {
        model.onClickOK.invoke()
        dismiss()
    }

    fun cancel() {
        model.onClickCancel.invoke()
        dismiss()
    }


    data class Model(

        val subTitle: String = "",

        val message: String = "",

        @StringRes val textBtnOK:  Int = R.string.aceptar,

        @StringRes val textBtnCancel: Int = R.string.empty,

        @DrawableRes val iconDrawable: Int = R.drawable.ic_info,

        var showCancelBtn:Boolean=false,

        var onClickCancel: () -> Unit = {},

        var onClickOK: () -> Unit = {}

    )
}