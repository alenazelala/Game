package com.example.game.domain.repository

import com.example.game.domain.entity.GameSettings
import com.example.game.domain.entity.Level
import com.example.game.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question
    fun getGameSettings(level: Level): GameSettings
}