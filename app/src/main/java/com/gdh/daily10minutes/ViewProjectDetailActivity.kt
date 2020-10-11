package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gdh.daily10minutes.datas.Project
import kotlinx.android.synthetic.main.activity_view_project_detail.*

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

//        화면에 데이터 반영
        titleTxt.text = mProject.title
        descTxt.text = mProject.desc

        Glide.with(mContext).load(mProject.imageURL).into(projectImg)
    }
}