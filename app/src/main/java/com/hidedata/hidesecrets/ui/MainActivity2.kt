package com.hidedata.hidesecrets.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.VideoView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.hidedata.hidesecrets.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var pickImageButton: MaterialButton
    private lateinit var pickVideoButton: MaterialButton
    private lateinit var image: ShapeableImageView
    private lateinit var video: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        pickImageButton = binding.imageButton
        pickVideoButton = binding.videoButton

        image = binding.pickImage
        video = binding.pickVideo

        pickImageButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }

        pickVideoButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "video/*"
            startActivityForResult(intent, 102)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /**
         * code for images.
         */
        if (requestCode == 101) {
            /**
             * checking if we have selected any image or not.
             */
            if (data != null) {
                val uri = data.data
                image.setImageURI(uri)
            } else {
                Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }
        /**
         * code for videos.
         */
        if (requestCode == 102) {
            /**
             * checking if we have selected any image or not.
             */
            if (data != null) {
                val uri = data.data
                video.setVideoURI(uri)
                video.start()
            } else {
                Toast.makeText(this, "No Video Selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

}