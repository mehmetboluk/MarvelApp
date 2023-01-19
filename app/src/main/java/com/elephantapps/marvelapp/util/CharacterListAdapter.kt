package com.elephantapps.marvelapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elephantapps.marvelapp.databinding.RvCharactersBinding
import com.elephantapps.marvelapp.domain.model.Character

class CharacterListAdapter(private val context: Context, var listItem: ArrayList<Character>): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private lateinit var binding: RvCharactersBinding

    inner class CharacterListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item: Character){
            binding.character = item
            binding.imageUrl = "${item.thumbnail.replace("http","https")}/portrait_xlarge.${item.thumbnailExt}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        binding = RvCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    fun setData(newList: ArrayList<Character>){
        this.listItem.addAll(newList)
        notifyDataSetChanged()
    }

    fun setSearchData(newList: ArrayList<Character>){
        this.listItem.clear()
        this.listItem.addAll(newList)
        notifyDataSetChanged()
    }
}