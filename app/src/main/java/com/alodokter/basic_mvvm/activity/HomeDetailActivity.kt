package com.alodokter.basic_mvvm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alodokter.basic_mvvm.R
import com.alodokter.basic_mvvm.model.ProductModel
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home_detail.*


class HomeDetailActivity : AppCompatActivity() {

    private var listImage = ArrayList<String>()
    private var imgSlider = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)
        init()
    }

    private fun init(){
        val bundle :Bundle ?=intent.extras
        val data = bundle!!.getString("data")
        val homeDetail: ProductModel = Gson().fromJson(data, ProductModel::class.java)

        if (homeDetail.imgList != null) {
            listImage.addAll(homeDetail.imgList!!)
            for (i in 0 until listImage.size) {
                imgSlider.add(SlideModel(homeDetail.imgList!![i]))
            }

            if (imgSlider.size > 0){
                sliderHome.setImageList(imageList = imgSlider)
            }
        }
    }

}