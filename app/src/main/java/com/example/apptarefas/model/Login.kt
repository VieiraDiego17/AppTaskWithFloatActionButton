package com.example.apptarefas.model

import java.io.Serializable

data class Login(
    var email: String,
    var password: String
): Serializable
