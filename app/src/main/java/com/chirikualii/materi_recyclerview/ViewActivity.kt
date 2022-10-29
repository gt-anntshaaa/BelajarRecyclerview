package com.chirikualii.materi_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chirikualii.materi_recyclerview.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Profile>("KEY")


        if (item != null) {
            binding.viewNama.text = item.nama.toString()
            binding.viewPhone.text = item.phone.toString()
        }else{
            return
        }

}
    }