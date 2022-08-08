package com.hidedata.hidesecrets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hidedata.hidesecrets.databinding.ActivityDocumentsBinding

class DocumentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDocumentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocumentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}