package com.shang.viewmodeldemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class AFragment : Fragment(R.layout.fragment_a) {
    companion object {
        const val TAG = "A"
    }

    //    val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    val fragmentViewModel by lazy { ViewModelProvider(this).get(AFragmentViewModel::class.java) }
    val mainViewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
//    val mainViewModel by lazy { ViewModelProvider(requireParentFragment()).get(MainViewModel::class.java) }
//
//    val mainViewModel by lazy {
//        ViewModelProvider(
//            requireActivity().supportFragmentManager.findFragmentByTag("TAG")!!
//        ).get(MainViewModel::class.java)
//    }
//
//    val mainViewModel by lazy {
//        ViewModelProvider(
//            requireParentFragment().childFragmentManager.findFragmentByTag("TAG")!!
//        ).get(MainViewModel::class.java)
//    }

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: FragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DEBUG", "AFragment : ${mainViewModel.hashCode()}")

//        mainViewModel.jumpFragment(0)
        viewPager = view.findViewById(R.id.viewPager)
        adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return IndexFragment.newInstance("$position")
            }
        }
        viewPager.adapter = adapter

        fragmentViewModel.switchPageLiveData.observe(viewLifecycleOwner,{
            viewPager.setCurrentItem(it)
        })

    }
}