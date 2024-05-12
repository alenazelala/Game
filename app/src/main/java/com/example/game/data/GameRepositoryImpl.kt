package com.example.game.data

import com.example.game.domain.entity.GameSettings
import com.example.game.domain.entity.Level
import com.example.game.domain.entity.Question
import com.example.game.domain.repository.GameRepository
import java.util.Random
import kotlin.math.max
import kotlin.math.min

object GameRepositoryImpl: GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = kotlin.random.Random.nextInt(MIN_SUM_VALUE, maxSumValue+1)
        val visibleNumber = kotlin.random.Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue-1, rightAnswer+countOfOptions)
        while (options.size < countOfOptions){
            options.add(kotlin.random.Random.nextInt(from,to))
        }
        return Question(sum, visibleNumber,options.toList())
    }


    override fun getGameSettings(level: Level): GameSettings {
        return when(level){
            Level.TEST -> GameSettings(10,3,50,8)
            Level.EASY -> GameSettings(10, 10,70,60)
            Level.NORMAL -> GameSettings(30,10,80,40)
            Level.HARD -> GameSettings(50,10,90,30)
        }
    }
}