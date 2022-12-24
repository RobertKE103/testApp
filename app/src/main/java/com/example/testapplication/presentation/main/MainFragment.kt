package com.example.testapplication.presentation.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMainBinding
import com.example.testapplication.domain.modules.network.CardDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeningToMistakes()
        listeningToCardData()
        setupEditText()
        listeningEnterBin()
        listeningIntent()
    }



    private fun listeningIntent(){


        binding.bankNumber.setOnClickListener {
            startCallIntent(binding.bankNumber.text.toString())
        }

        binding.bankUrl.setOnClickListener {
            startUrlIntent(binding.bankUrl.text.toString())
        }

        binding.adapterCardApp.setOnClickListener {
            val lat = binding.coordinatesLatitudeContent.text.toString()
            val lon = binding.coordinatesLongitudeContent.text.toString()
            startCartIntent(lat, lon)
        }

    }

    private fun startCartIntent(lat: String, lon: String){

        val location = Uri.parse("geo:$lat, $lon?z=14")
        val intent = Intent(Intent.ACTION_VIEW, location)
        startActivity(intent)

    }

    private fun startCallIntent(number: String){
        val call = Uri.parse("tel:${number}")
        val callIntent = Intent(Intent.ACTION_DIAL, call)
        val chooser = Intent.createChooser(callIntent, "Звонок")

// Try to invoke the intent.
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            // Define what your app should do if no activity can handle the intent.
        }
    }

    private fun startUrlIntent(url: String){
        val uri = Uri.parse("https://${url}")
        val uriIntent = Intent(Intent.ACTION_VIEW, uri)

// Try to invoke the intent.
        try {
            startActivity(uriIntent)
        } catch (e: ActivityNotFoundException) {
            // Define what your app should do if no activity can handle the intent.
        }
    }






    private fun listeningEnterBin() {

        binding.adapterSearchFragment.editText?.setOnEditorActionListener(
            TextView.OnEditorActionListener { v, id, _ ->
                if (id == EditorInfo.IME_ACTION_DONE) {
                    viewModel.checkBin(v.text.toString())
                    return@OnEditorActionListener true
                }
                false
            })

    }

    private fun setupEditText() {
        binding.adapterSearchFragment.setOnClickListener {
            binding.adapterSearchFragment.error = null
        }

        viewModel.listBin.observe(viewLifecycleOwner) { it ->
            val adapter =
                ArrayAdapter(requireContext(), R.layout.list_item, it.toSet().map { it.bin })
            (binding.adapterSearchFragment.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }
    }

    private fun listeningToCardData() {
        viewModel.cardData.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                bindDataWithUi(it.body()!!)
            }
        }
    }

    private fun listeningToMistakes() {
        viewModel.errorEnterBin.observe(viewLifecycleOwner) {
            if (it) {
                binding.adapterSearchFragment.error = "Invalid bin"
            } else {
                binding.adapterSearchFragment.error = null
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun bindDataWithUi(data: CardDetails) {

        with(binding) {
            with(data) {
                schemeContent.text = scheme
                typeContent.text = type
                bankName.text = bank.name
                bankUrl.text = bank.url
                bankNumber.text = bank.phone
                brandContent.text = brand
                prepaidContent.text = if (prepaid) "Yes" else "No"
                lengthContent.text = number.length.toString()
                luhnContent.text = if (number.luhn) "Yes" else "No"
                countryContent.text = country.name
                coordinatesLatitudeContent.text = country.latitude.toString()
                coordinatesLongitudeContent.text = country.longitude.toString()
            }
        }
    }


}