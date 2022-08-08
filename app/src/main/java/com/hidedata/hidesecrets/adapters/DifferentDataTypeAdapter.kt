package com.hidedata.hidesecrets.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hidedata.hidesecrets.databinding.CardviewDifferentDataTypeBinding

class DifferentDataTypeAdapter :
    RecyclerView.Adapter<DifferentDataTypeAdapter.DifferentDataViewHolder>() {
    var dataNameList = emptyList<String>()
    private lateinit var mListener: OnItemClickMainActivity

    interface OnItemClickMainActivity {
        fun itemClick(position: Int, dataName: String)
    }

    fun setOnItemClickListener(listener: OnItemClickMainActivity) {
        mListener = listener
    }

    class DifferentDataViewHolder(
        binding: CardviewDifferentDataTypeBinding,
        listener: OnItemClickMainActivity,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val dataName = binding.dataTypeName
        val cardName = binding.mainActivityCardView

        init {
            cardName.setOnClickListener {
                listener.itemClick(adapterPosition, dataName = dataName.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DifferentDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardviewDifferentDataTypeBinding.inflate(inflater, parent, false)
        return DifferentDataViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: DifferentDataViewHolder, position: Int) {
        holder.dataName.text = dataNameList[position]
        val listOfColors = listOf("#3D190F", "#5265C2", "#C74812", "#11C700")
        holder.cardName.setCardBackgroundColor(Color.parseColor(listOfColors[position]))
    }

    override fun getItemCount(): Int {
        return dataNameList.size
    }
}