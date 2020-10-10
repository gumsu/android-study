package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
    }

    override fun setValues() {
        getProjectListFromServer()
    }

//    서버에서 프로젝트 목록 가져오는 코드를 별도 함수로 분리하여 작성
    fun getProjectListFromServer(){

//           서버에 프로젝트 목록 요청 => 응답을 분석 => 목록에 담아주는 코드
    ServerUtil.getRequestProjectList(object : ServerUtil.JsonResponseHandler{
        override fun onResponse(json: JSONObject) {

            val dataObj = json.getJSONObject("data")
            val projectArr = dataObj.getJSONArray("projects")

//            projectArr의 내용물을 0~끝 번까지 뽑아내자.
            for(i in 0 until projectArr.length()){
//                상황에 맞는 JSONObject를 추출
                val projectObj = projectArr.getJSONObject(i)

//                추출된 JSONObject를 가지고 => Project 클래스로 변환
                val tempProject = Project()
                tempProject.id = projectObj.getInt("id")
                tempProject.title = projectObj.getString("title")
                tempProject.imageURL = projectObj.getString("img_url")
                tempProject.desc = projectObj.getString("description")

//                완성된 Project 클래스를 => mProjectList에 추가
                mProjectList.add(tempProject)
            }
        }
    })
}

}