package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import org.json.JSONObject

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

        proofMethodTxt.text = mProject.proofMethod

        getProjectInfoFromServer()
    }

//    현재 참여 인원 등 최신정보 (프로젝트 상세 정보)를 서버에서 가져오는 함수
    fun getProjectInfoFromServer(){
        ServerUtil.getRequestProjectInfoById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")

                val projectObj = dataObj.getJSONObject("project")

//                mProject 내용물 전부 교체
                mProject = Project.getProjectFromJSON(projectObj)

                runOnUiThread {
//                    새로 갱신된 mProject를 이용해 화면에 새로 데이터를 반영
                    userCountTxt.text = "${mProject.ongoingUserCount}명"

                    proofMethodTxt.text = "${mProject.proofMethod}"
                }
            }
        })
    }
}