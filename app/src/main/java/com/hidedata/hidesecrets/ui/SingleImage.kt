package com.hidedata.hidesecrets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hidedata.hidesecrets.databinding.ActivitySingleImageBinding

class SingleImage : AppCompatActivity() {
    private lateinit var binding: ActivitySingleImageBinding
    private lateinit var imageName: String
    private lateinit var imagePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        imageName = bundle!!.getString("imageName")!!
        imagePath = bundle.getString("imagePath")!!
    }

    override fun onResume() {
        super.onResume()
        binding.singleImageImageName.text = imageName
        val imageView = binding.singleImage
        Glide.with(this).load(imagePath).centerCrop()
            .into(imageView)
    }
}