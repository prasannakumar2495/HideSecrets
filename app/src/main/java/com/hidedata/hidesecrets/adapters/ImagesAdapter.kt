package com.hidedata.hidesecrets.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hidedata.hidesecrets.databinding.CardviewImagesIndividualItemsBinding
import com.hidedata.hidesecrets.model.DataClass
import com.hidedata.hidesecrets.ui.SingleImage

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ItemsViewHolder>() {

    private lateinit var data: ArrayList<DataClass>

    fun inputMethod(dataInput: ArrayList<DataClass>) {
        data = dataInput
    }

    class ItemsViewHolder(binding: CardviewImagesIndividualItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.individualItemImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardviewImagesIndividualItemsBinding.inflate(inflater, parent, false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val path = data[position].dataPath
        val name = data[position].dataName
        Glide.with(holder.image.context)
            .load(path).centerCrop()
            .into(holder.image)

        holder.image.setOnClickListener {
            val intent = Intent(holder.image.context, SingleImage::class.java)
            intent.putExtra("imagePath", "$path")
            intent.putExtra("imageName", "$name")
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     *  val iv = ImageView(p2?.context)
    data.moveToPosition(p0)
    val path = data.getString(0)
    val bitMap = BitmapFactory.decodeFile(path)
    iv.setImageBitmap(bitMap)
    iv.layoutParams = AbsListView.LayoutParams(300, 300)
     */
}