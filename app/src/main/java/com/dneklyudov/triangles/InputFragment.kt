package com.dneklyudov.triangles

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dneklyudov.triangles.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: InputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(InputViewModel::class.java)

        binding.length1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                viewModel.changeLen1(s.toString())
            }
        })

        binding.length2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                viewModel.changeLen2(s.toString())
            }
        })

        binding.length3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                viewModel.changeLen3(s.toString())
            }
        })

        viewModel.len1.observe(viewLifecycleOwner) { _ ->
            viewModel.areValuesCorrect()
        }
        viewModel.len2.observe(viewLifecycleOwner) { _ ->
            viewModel.areValuesCorrect()
        }
        viewModel.len3.observe(viewLifecycleOwner) { _ ->
            viewModel.areValuesCorrect()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { newValue ->
            binding.errorTextView.text = newValue
            if (newValue == "") {
                binding.errorTextView.visibility = View.GONE
            }
            else {
                binding.errorTextView.visibility = View.VISIBLE
            }
        }

        viewModel.isButtonActive.observe(viewLifecycleOwner) { newValue ->
            binding.button.isEnabled = newValue
        }

        binding.button.setOnClickListener {
            val action = InputFragmentDirections.actionInputFragmentToResultFragment(viewModel.resultMessage())
            view.findNavController().navigate(action)
        }

        binding.length1.setSelectAllOnFocus(true)
        binding.length2.setSelectAllOnFocus(true)
        binding.length3.setSelectAllOnFocus(true)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}