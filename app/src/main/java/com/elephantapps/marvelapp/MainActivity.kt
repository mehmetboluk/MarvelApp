package com.elephantapps.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elephantapps.marvelapp.databinding.ActivityMainBinding
import com.elephantapps.marvelapp.domain.model.Character
import com.elephantapps.marvelapp.ui.CharacterList.CharactersViewModel
import com.elephantapps.marvelapp.util.CharacterListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : CharactersViewModel by viewModels()
    var flag = 3
    private lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerAdapter()
        observer()
    }

    private fun observer() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag){
                viewModel.marvelValue.collect{
                    when{
                        it.isLoading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_SHORT).show()
                        }
                        it.characterList.isNotEmpty() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            characterListAdapter.setData(it.characterList as ArrayList<Character>)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerAdapter(){
        characterListAdapter = CharacterListAdapter(this, ArrayList())
        val characterListLayoutManager = GridLayoutManager(this,2)
        binding.rvCharacterList.apply {
            adapter = characterListAdapter
            layoutManager = characterListLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (characterListLayoutManager.findLastVisibleItemPosition() == characterListLayoutManager.itemCount-1){
                        viewModel.apply {
                            getAllCharacters(paginatedValue)
                        }
                    }
                }
            })
        }
    }
}