package com.example.apptarefas.model

import android.net.Uri
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Task(
    var title: String,
    var description: String,
    var utensils: String,
    var imagem: Uri?
): Parcelable
