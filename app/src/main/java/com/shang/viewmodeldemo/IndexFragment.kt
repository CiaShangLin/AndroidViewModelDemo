package com.shang.viewmodeldemo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IndexFragment : Fragment(R.layout.fragment_index) {

    companion object {
        private const val TEXT = "TEXT"
        fun newInstance(text: String): IndexFragment {
            return IndexFragment().apply {
                this.arguments = Bundle().apply {
                    this.putString(TEXT, text)
                }
            }
        }
    }

//    private val parentFragment by lazy { ViewModelProvider(requireParentFragment()).get(AFragmentViewModel::class.java) }
    private val parentFragment by lazy { ViewModelProvider(
        requireActivity().supportFragmentManager.findFragmentByTag(AFragment.TAG)!!
    ).get(AFragmentViewModel::class.java) }


    private lateinit var tvIndex: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvIndex = view.findViewById(R.id.tvIndex)
        tvIndex.text = arguments?.getString(TEXT) ?: "null"

        parentFragment.switchPage(2)
    }
}