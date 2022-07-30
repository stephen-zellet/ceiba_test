package com.example.ceiba_test.core

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.ceiba_test.R
import com.example.ceiba_test.core.util.Dialog
import com.example.ceiba_test.core.util.ProgressDialog
import com.google.android.material.snackbar.Snackbar


var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.GONE
    }

fun Fragment.notifyErrorWithAction(
    message: String,
    actionText: String,
    action: () -> Any
){
    val snackBar = Snackbar.make(this.requireView(), message, Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction(actionText) { _ -> action.invoke() }
    snackBar.setActionTextColor(ContextCompat.getColor(this.requireContext(), R.color.green_primary))
    snackBar.show()

}

fun Fragment?.showDialog(
    subTitle: String = "",
    message: String = "",
    textBtnOK:  Int = R.string.aceptar,
    textBtnCancel: Int = R.string.empty,
    iconDrawable: Int = R.drawable.ic_info,
    showCancelBtn:Boolean=false,
    onClickCancel: () -> Unit = {},
    onClickOK: () -> Unit = {}
): Dialog {
    val dialog = Dialog()
    dialog.model = Dialog.Model(subTitle, message, textBtnOK, textBtnCancel, iconDrawable,showCancelBtn, onClickCancel, onClickOK)
    this?.let {
        dialog.show(it.childFragmentManager, Dialog::class.java.name)
    }
    return dialog
}


fun Fragment?.showProgressDialog(): ProgressDialog {
    val dialog = ProgressDialog()
    this?.let { dialog.show(it.childFragmentManager, ProgressDialog::class.java.name) }
    return dialog
}

fun Fragment?.closeProgressDialog() {
    val progressDialog = this?.childFragmentManager?.findFragmentByTag(ProgressDialog::class.java.name)
    if (progressDialog is ProgressDialog) {
        try {
            progressDialog.dismissAllowingStateLoss()
        } catch (ex: Exception) {
        }
    }
}