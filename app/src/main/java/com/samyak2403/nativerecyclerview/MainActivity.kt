package com.samyak2403.nativerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.com.samyak2403.nativerecyclerview.databinding.ActivityMainBinding
import com.google.rvadapter.AdmobNativeAdAdapter
import com.samyak2403.nativerecyclerview.adapter.MyAdapter
import com.samyak2403.nativerecyclerview.model.UserModel


class MainActivity : AppCompatActivity() {

    private val myAdapter by lazy { MyAdapter() }
    private lateinit var binding: ActivityMainBinding
    private var dataList = arrayOf(
        "Samyak Kamble",
        "Tanushree ",
        "Samruddhi",
        "Ramisvari",
        "Sam game",
        "Pratiksh Patil",
        "Samruddhi Powar"
    )

    private var data = mutableListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            val admobNativeAdAdapter: AdmobNativeAdAdapter = AdmobNativeAdAdapter.Builder
                .with(
                    "ca-app-pub-3940256099942544/2247696110",  //Create a native ad id from admob console
                    myAdapter,  //The adapter you would normally set to your recyClerView
                    "small" //Set it with "small","medium" or "custom"
                )
                .adItemIterval(2) //native ad repeating interval in the recyclerview
                .build()
            recyclerView.adapter = admobNativeAdAdapter
        }

        for (i in 0 until dataList.size) {
            data.add(UserModel(dataList[i]))
        }

        myAdapter.differ.submitList(data)
    }
}