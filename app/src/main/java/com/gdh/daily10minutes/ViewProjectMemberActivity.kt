package com.gdh.daily10minutes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdh.daily10minutes.dapters.ProjectMemberAdapter
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.datas.User
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kotlinx.android.synthetic.main.activity_view_project_member.*
import org.json.JSONObject
import java.util.ArrayList

class ViewProjectMemberActivity : BaseActivity() {

    val mMemberList = ArrayList<User>()

    lateinit var mProject : Project

    lateinit var mAdapter : ProjectMemberAdapter

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

        mAdapter = ProjectMemberAdapter(mContext, R.layout.user_list_item, mMemberList)
        projectMemberListView.adapter = mAdapter
    }

//    프로젝트의 참여한 사람들이 누구누구 있는지 (서버에서) 불러내는 함수
    fun getMembersFromServer(){

        ServerUtil.getRequestProjectMembersById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val ongoingUsersArr = projectObj.getJSONArray("ongoing_users")

                for (i in 0 until ongoingUsersArr.length()){
                    val ongoingUserObj = ongoingUsersArr.getJSONObject(i)

//                    진행 중인 사람들의 JSONObj 추출된 상황 => User 형태로 변환
                    val user = User.getUserFromJSON(ongoingUserObj)

//                    만들어진 User 객체들 => 목록에 추가
                    mMemberList.add(user)
                }
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }
            }
        })
}
}