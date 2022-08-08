package com.hidedata.hidesecrets.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hidedata.hidesecrets.adapters.ImagesAdapter
import com.hidedata.hidesecrets.databinding.ActivityPhotosBinding
import com.hidedata.hidesecrets.model.DataClass

class PhotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotosBinding
    private lateinit var layoutManager: RecyclerView
    private var allPictures: ArrayList<DataClass>? = null
    private val myAdapter by lazy {
        ImagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPhotoPermission()
        allPictures = ArrayList()

        if (allPictures!!.isEmpty()) {
            allPictures = getAllPictures()
        }
        getAllPictures()
        createPhotoRecyclerView()
    }

    /**
     * This method will fetch all the images from the Local Storage.
     */
    private fun getAllPictures(): ArrayList<DataClass> {
        val images = ArrayList<DataClass>()
        val allImageURI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME)

        val cursor = this.contentResolver.query(allImageURI, projection, null, null, null)
        try {
            cursor?.moveToFirst()
            do {
                val image = DataClass()
                image.dataPath =
                    cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.dataName =
                    cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))

                images.add(image)
            } while (cursor!!.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 121 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getAllPictures()
            createPhotoRecyclerView()
        }
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
        getAllPictures()
    }


    private fun createPhotoRecyclerView() {
        layoutManager = binding.photosRecyclerView
        layoutManager.layoutManager = GridLayoutManager(this, 3)
        layoutManager.adapter = myAdapter

        myAdapter.inputMethod(getAllPictures())
    }
}