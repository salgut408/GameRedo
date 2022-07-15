package com.example.android.gamesredo.ui.today

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.gamesredo.R
import com.example.android.gamesredo.Resource
import com.example.android.gamesredo.databinding.FragmentTodayBinding
import com.example.android.gamesredo.db.VenueDatabase
import com.example.android.gamesredo.repository.GameRepository
import com.example.android.gamesredo.ui.adapters.GamesAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodayFragment : Fragment(R.layout.fragment_today) {

    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!

    lateinit var todayViewModel: TodayViewModel
    lateinit var gameAdapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gameRepository = GameRepository(VenueDatabase(context?.applicationContext!!))
        val viewModelProviderFactory = TodayVMProviderFactory(gameRepository)
        todayViewModel = ViewModelProvider(this, viewModelProviderFactory).get(TodayViewModel::class.java)


        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.todaysDate?.text = setDate()
        setUpRecyclerView()
        todayViewModel.allGames.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { mlbresponse ->
                        gameAdapter.differ.submitList(mlbresponse.dates[0].games)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("Tag", "response not successfull")
                    }
                }
                is Resource.Loading -> {
                    hideProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun setUpRecyclerView(){
        gameAdapter = GamesAdapter()
        binding.rvGames.apply {
            adapter = gameAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
    }

   @RequiresApi(Build.VERSION_CODES.O)
   private fun setDate(): String {
       val current = LocalDateTime.now()
       val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
       val formatted = current.format(formatter)
       return formatted
   }

}






































