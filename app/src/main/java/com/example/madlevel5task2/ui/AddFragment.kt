package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_add.*
import java.time.LocalDate
import java.util.*

class AddFragment : Fragment() {
    private val viewModel: AddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab_save.setOnClickListener {
            viewModel.addGame(
                input_title.text.toString(),
                input_platform.text.toString(),
                input_day.text.toString(),
                input_month.text.toString(),
                input_year.text.toString()
            )
        }
    }

    private fun initViewModel() {
        viewModel.error.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            findNavController().popBackStack()
        })
    }
}