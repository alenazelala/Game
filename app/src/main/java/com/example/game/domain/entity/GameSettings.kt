package com.example.game.domain.entity

import java.io.Serializable

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPersentOfRightAnswers : Int,
    val gameTimeInSeconds: Int
) :Serializable{
}