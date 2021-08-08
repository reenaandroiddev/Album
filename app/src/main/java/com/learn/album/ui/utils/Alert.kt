package com.learn.album.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

interface OnProceedClickListener {
  fun proceed()
}

fun showAlert(
  context: Context, message: String, title: String, listener: OnProceedClickListener,
) {
  val dialogBuilder = AlertDialog.Builder(context)

  dialogBuilder.setMessage(message)
    .setCancelable(true)
    .setPositiveButton("Ok") { dialog, id ->
      listener.proceed()
      dialog.dismiss()
    }

  val alert = dialogBuilder.create()
  alert.setTitle(title)
  alert.show()
}

const val TITLE = "Something went wrong!!!"
