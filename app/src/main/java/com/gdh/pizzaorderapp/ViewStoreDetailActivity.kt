package com.gdh.pizzaorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gdh.pizzaorderapp.datas.Store
import kotlinx.android.synthetic.main.activity_view_store_detail.*

class ViewStoreDetailActivity : BaseActivity() {

    lateinit var mStore : Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mStore = intent.getSerializableExtra("storeData") as Store

        Glide.with(mContext).load(mStore.logoUrl).into(storeLogoImg)
        storeNameTxt.text = mStore.brandName
        phoneNumTxt.text = mStore.phoneNum
    }
}