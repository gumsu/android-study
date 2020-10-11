package com.gdh.daily10minutes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.datas.User
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import org.json.JSONObject
import java.util.ArrayList

class ViewProjectMemberActivity : BaseActivity() {

    val mMemberList = ArrayList<User>()

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_member)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }
    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

        getMembersFromServer()
    }

//    프로젝트의 참여한 사람들이 누구누구 있는지 (서버에서) 불러내는 함수
    fun getMembersFromServer(){

        ServerUtil.getRequestProjectMembersById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

            }
        })
}
}