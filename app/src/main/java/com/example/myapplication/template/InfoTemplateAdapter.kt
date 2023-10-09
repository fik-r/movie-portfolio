package com.example.myapplication.template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TemplateTitleDescriptionBinding

class InfoTemplateAdapter() :
    RecyclerView.Adapter<InfoTemplateAdapter.TitleDescriptionViewHolder>() {

    val listInfo: MutableList<InfoTitleDescription> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleDescriptionViewHolder {
        return TitleDescriptionViewHolder(
            TemplateTitleDescriptionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listInfo.size

    override fun onBindViewHolder(holder: TitleDescriptionViewHolder, position: Int) {
        holder.bindData(listInfo[position])
    }

    class TitleDescriptionViewHolder(private val binding: TemplateTitleDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(titleDescription: InfoTitleDescription) {
            with(binding) {
                tvTitle.text = titleDescription.title
                tvDescription.text = titleDescription.description
            }
        }
    }
}