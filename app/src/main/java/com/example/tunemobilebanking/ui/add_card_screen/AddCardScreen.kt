package com.example.tunemobilebanking.ui.add_card_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tunemobilebanking.R
import com.example.tunemobilebanking.common.Resource
import com.example.tunemobilebanking.databinding.FragmentAddCardScreenBinding
import com.example.tunemobilebanking.domain.model.Card
import com.example.tunemobilebanking.ui.home_screen.CardViewModel


class AddCardScreen : Fragment() {

    private val binding by lazy { FragmentAddCardScreenBinding.inflate(layoutInflater) }
    private val viewModel : CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initViews()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return binding.root
    }

    private fun initViews() {


        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.homeScreen)
        }

        binding.bContinue.setOnClickListener {
            val card = Card((System.currentTimeMillis()/2).toString(),binding.etCardNumber.text.toString(),binding.etExpireDate.text.toString())
            viewModel.addCard(card)
        }



        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val cardNumber = binding.etCardNumber.text.toString()
                val expireDate = binding.etExpireDate.text.toString()
                binding.bContinue.isEnabled = cardNumber.length == 19 && expireDate.length == 5
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        binding.etCardNumber.addTextChangedListener(textWatcher)
        binding.etExpireDate.addTextChangedListener(textWatcher)
    }

}