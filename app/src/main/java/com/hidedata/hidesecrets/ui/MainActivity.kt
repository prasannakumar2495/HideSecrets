package com.hidedata.hidesecrets.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hidedata.hidesecrets.adapters.DifferentDataTypeAdapter
import com.hidedata.hidesecrets.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: RecyclerView
    private val myAdapter by lazy {
        DifferentDataTypeAdapter()
    }
    private val data = listOf("Photos", "Documents", "Videos", "APK")


    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAuthentication()
        createDifferentDataTypeRecyclerView()
    }

    private fun userAuthentication() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@MainActivity,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                    finishAndRemoveTask()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(this@MainActivity,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(this@MainActivity, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun createDifferentDataTypeRecyclerView() {
        layoutManager = binding.mainActivityRecyclerView
        layoutManager.layoutManager = GridLayoutManager(this, 2)
        layoutManager.adapter = myAdapter

        myAdapter.dataNameList = data

        myAdapter.setOnItemClickListener(object : DifferentDataTypeAdapter.OnItemClickMainActivity {
            override fun itemClick(position: Int, dataName: String) {
                when (dataName) {
                    data[0] -> {
                        startActivity(Intent(this@MainActivity, PhotosActivity::class.java))
                    }
                    data[1] -> {
                        startActivity(Intent(this@MainActivity, DocumentsActivity::class.java))
                    }
                    data[2] -> {
                        startActivity(Intent(this@MainActivity, VideoActivity::class.java))
                    }
                    data[3] -> {
                        startActivity(Intent(this@MainActivity, APKActivity::class.java))
                    }
                }
            }

        })
    }
}