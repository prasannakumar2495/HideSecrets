package com.hidedata.hidesecrets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hidedata.hidesecrets.databinding.ActivityApkactivityBinding

class APKActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApkactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApkactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}