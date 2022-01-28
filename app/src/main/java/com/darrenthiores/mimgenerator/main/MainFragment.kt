package com.darrenthiores.mimgenerator.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.darrenthiores.core.model.presenter.Image
import com.darrenthiores.core.ui.MainAdapter
import com.darrenthiores.mimgenerator.R
import com.darrenthiores.mimgenerator.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding
    private val viewModel:MainViewModel by inject()
    private val mainAdapter:MainAdapter by lazy {
        MainAdapter(::onClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            rvImages.layoutManager = GridLayoutManager(activity, 3)
            rvImages.adapter = mainAdapter

        }

        viewModel.getImages().observe(viewLifecycleOwner, {

            if(it!=null){

                mainAdapter.setData(it)
                mainAdapter.notifyDataSetChanged()

            }

        })

    }

    private fun onClick(image: Image){



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}