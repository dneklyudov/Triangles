package com.dneklyudov.triangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.dneklyudov.triangles.databinding.FragmentInputBinding
import androidx.lifecycle.ViewModelProvider

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: InputViewModel

    val len1: Int = 0
    val len2: Int = 0
    val len3: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(InputViewModel::class.java)

        binding.button.setOnClickListener {
            if (viewModel.areValuesCorrect()) {
                val action =
                    InputFragmentDirections.actionInputFragmentToResultFragment(viewModel.resultMessage())
                view.findNavController().navigate(action)
            }
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