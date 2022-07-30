package com.example.ceiba_test.core.util

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.ceiba_test.databinding.DialogProgressBinding

class ProgressDialog:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity ?: return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)

        val binding = DialogProgressBinding.inflate(activity.layoutInflater)

        builder.setView(binding.root)
        builder.setCancelable(false)
        return builder.create()
    }
}