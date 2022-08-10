package com.hidedata.hidesecrets.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hidedata.hidesecrets.adapters.VideosAdapter
import com.hidedata.hidesecrets.databinding.ActivityVideoBinding
import com.hidedata.hidesecrets.model.DataClass

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var layoutManager: RecyclerView
    private val myAdapter by lazy {
        VideosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getAllVideos()
        }
    }

    override fun onResume() {
        super.onResume()
        checkPhotoPermission()
        createDocumentsRecyclerView()
    }

    private fun checkPhotoPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                Array(1) { Manifest.permission.READ_EXTERNAL_STORAGE },
                101)
        }
        getAllVideos()
        createDocumentsRecyclerView()
    }

    private fun getAllVideos(): ArrayList<DataClass> {
        val videos = ArrayList<DataClass>()
        val allVideoURI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Media.DISPLAY_NAME)

        val cursor = this.contentResolver.query(allVideoURI, projection, null, null, null)

        try {
            cursor?.moveToFirst()
            do {
                val video = DataClass()
                video.dataPath =
                    cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                video.dataName =
                    cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))

                videos.add(video)
            } while (cursor!!.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return videos
    }

    private fun createDocumentsRecyclerView() {
        layoutManager = binding.videoRecyclerView
        layoutManager.layoutManager = GridLayoutManager(this, 3)
        layoutManager.adapter = myAdapter

        myAdapter.inputMethod(getAllVideos())
    }
}