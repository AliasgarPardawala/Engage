package com.pardawala.aliasgar.engage.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pardawala.aliasgar.engage.MainActivity
import com.pardawala.aliasgar.engage.R
import com.pardawala.aliasgar.engage.data.Hobby
import com.pardawala.aliasgar.engage.databinding.FragmentMessagesBinding
import com.pardawala.aliasgar.engage.repository.HobbyRepository
import kotlinx.coroutines.flow.collect

class MessagesFragment : Fragment(R.layout.fragment_messages), MessageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMessagesBinding
    private lateinit var viewModel: MessageViewModel
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMessagesBinding.inflate(inflater, container, false)
        val view = binding.root
        startAnimation()
        viewModel = (activity as MainActivity).viewModel

        messageAdapter = MessageAdapter(this)


        binding.apply {
            rvChats.apply {
                adapter = messageAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        return view
    }
 
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hobbies.observe(viewLifecycleOwner,
            Observer { messageAdapter.submitList(it) })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.hobbyEvent.collect { event ->
                when (event) {
                    is MessageViewModel.HobbyEvent.ResponseError -> {
                        Toast.makeText(activity, "Some error occurred while getting data", Toast.LENGTH_SHORT).show()
                    }
                    is MessageViewModel.HobbyEvent.NoInternetConnection -> {
                        Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun startAnimation() {
        binding.cvEngage.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_top))
    }

    fun closeAnimation() {
        binding.cvEngage.startAnimation(
            AnimationUtils.loadAnimation(
                activity,
                R.anim.slide_out_top
            )
        )
    }

    override fun onItemClicked(hobby: Hobby) {
        Toast.makeText(activity, "${hobby.title} Clicked!!", Toast.LENGTH_SHORT).show()
    }
}