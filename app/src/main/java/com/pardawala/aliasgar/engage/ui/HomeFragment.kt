package com.pardawala.aliasgar.engage.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.pardawala.aliasgar.engage.R
import com.pardawala.aliasgar.engage.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), IOnBackPressed {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        testString()
        startAnimation()

        binding.tvBio.setOnClickListener {
            testString()
        }

        return view
    }

    private fun testString() {
        if(binding.tvBio.text.toString().endsWith("...more")) {
            binding.tvBio.text = Html.fromHtml(resources.getString(R.string.test) + "..."+"<font color='white'> <b><u>...less</u></b></font>")
        }
        else if (binding.tvBio.text.toString().length > 90) {
            binding.tvBio.text = Html.fromHtml(resources.getString(R.string.test).substring(0,90)+"..."+"<font color='white'> <b><u>...more</u></b></font>")
        }
        else {
            binding.tvBio.text = resources.getString(R.string.test)
        }
    }

    private fun startAnimation() {
        binding.fabTop.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right))
        binding.fabFilter.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right))
        binding.fabLike.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right))
        binding.fabStar.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right))
        binding.fabNext.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right))
        binding.fabTime.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.fabReset.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.imageView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvBio.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvCrossPath.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvPlace.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvTime.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.imName.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvName.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        binding.tvAge.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
    }

    fun closeAnimation() {
        binding.fabTop.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right))
        binding.fabFilter.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right))
        binding.fabLike.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right))
        binding.fabStar.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right))
        binding.fabNext.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right))
        binding.fabTime.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.fabReset.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.imageView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvBio.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvCrossPath.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvPlace.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvTime.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.imName.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvName.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
        binding.tvAge.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left))
    }

    override fun onBackPressed(): Boolean {
        closeAnimation()
        return true
    }

    override fun onDetach() {
        binding.fabTop.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left))
        super.onDetach()
    }

}