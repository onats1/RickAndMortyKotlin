package com.example.rickandmortykotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.adapters.CharacterRecycleradapter.*
import com.example.rickandmortykotlin.remoteDatabase.responses.Characters
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterRecycleradapter(var listener: CharacterListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    var charactersList: List<Characters>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return charactersList!!.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.character_name.text = charactersList!![position].name
    }


    inner class CharacterViewHolder(itemView: View, listener: CharacterListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        val onCharacterClick: CharacterListener
        init {
            onCharacterClick = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onCharacterClick.onCharacterClick(adapterPosition)
        }

    }
}