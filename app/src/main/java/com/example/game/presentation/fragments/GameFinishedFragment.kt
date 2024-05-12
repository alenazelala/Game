package com.example.game.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.game.R
import com.example.game.databinding.FragmentGameFinishedBinding
import com.example.game.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()
    private lateinit var binding: FragmentGameFinishedBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }


        })
        binding.retryBtn.setOnClickListener {
            retryGame()
        }
    }



    private fun bindViews(){
        with(binding){
            emajiResult.setImageResource(getSmileResId())
            requiredAnswers.text = getString(getResultTitle())
        }
    }

    private fun getSmileResId(): Int{
        return if(args.gameResult.winner){
            R.drawable.finish_s
        } else{
            R.drawable.loser
        }
    }
    private fun getResultTitle(): Int{
        return if(args.gameResult.winner){
            R.string.winner
        }else{
            R.string.loser
        }
    }
    private fun retryGame(){
       findNavController().popBackStack()}


}