package com.hidedata.hidesecrets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.hidedata.hidesecrets.databinding.ActivitySingleVideoBinding

class SingleVideo : AppCompatActivity() {
    private lateinit var binding: ActivitySingleVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val videoPath = bundle?.getString("videoPath")
        val videoName = bundle?.getString("videoName")

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.singleVideo)

        binding.singleVideo.setMediaController(mediaController)
        binding.singleVideo.setVideoPath(videoPath)
            binding.singleVideo.requestFocus()
            binding.singleVideo.start()
        binding.singleVideoVideoName.text = videoName
    }
}