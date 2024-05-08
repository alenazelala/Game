package com.example.game.domain.usecases

import com.example.game.domain.entity.GameSettings
import com.example.game.domain.entity.Level
import com.example.game.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
   operator fun invoke(level: Level): GameSettings{
       return repository.getGameSettings(level)
   }
}