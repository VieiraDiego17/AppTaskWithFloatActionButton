package com.example.apptarefas.model

import java.io.Serializable

data class Task(
    var title: String,
    var description: String,
    var utensils: String
): Serializable
