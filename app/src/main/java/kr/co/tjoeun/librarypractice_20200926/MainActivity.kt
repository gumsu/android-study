package kr.co.tjoeun.librarypractice_20200926

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        loadWebImageButton.setOnClickListener {

        }
    }

    override fun setValues() {

        profilePhotoImg.setOnClickListener {
//            Toast.makeText(mContext, "사진 클릭됨", Toast.LENGTH_SHORT).show()
            val myIntent = Intent(mContext,ViewProfilePhotoActivity::class.java)
            startActivity(myIntent)
        }

        loadWebImageButton.setOnClickListener {
            Glide.with(mContext).load("https://img.sbs.co.kr/newimg/news/20200221/201405004_1280.jpg").into(otherPersonImg)
        }
    }
}