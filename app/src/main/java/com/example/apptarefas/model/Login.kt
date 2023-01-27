package com.example.apptarefas.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Login(
    var email: String,
    var password: String,
    var imagem: Uri?
): Parcelable
