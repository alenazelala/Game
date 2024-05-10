package com.example.game.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.game.R
import com.example.game.databinding.FragmentGameFinishedBinding
import com.example.game.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private lateinit var binding: FragmentGameFinishedBinding
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }

        })
        binding.retryBtn.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    private fun parseArgs(){
        gameResult = requireArguments().getSerializable(KEY_GAME_RESULT) as GameResult
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    companion object{
        private const val KEY_GAME_RESULT = "game_result"
    fun newInstance(gameResult: GameResult):GameFinishedFragment{
        return GameFinishedFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_GAME_RESULT, gameResult)
            }
        }
    }}
    private fun launchChooseLevelFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ChooseLevelFragment.newInstance())
            .addToBackStack(ChooseLevelFragment.NAME)
            .commit()

    }
}