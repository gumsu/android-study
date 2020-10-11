package com.gdh.daily10minutes

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        viewMembersBtn.setOnClickListener {
            val myIntent = Intent(mContext, ViewProjectMemberActivity::class.java)
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)
        }

        applyBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("프로젝트 참가 신청")
            alert.setMessage("${mProject.title} 프로젝트에 도전하시겠습니까?")
            alert.setPositiveButton("도전", DialogInterface.OnClickListener { dialog, which ->

//                참여 신청 API 호출
                ServerUtil.postRequestApplyProject(
                    mContext,
                    mProject.id,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(json: JSONObject) {

                            val code = json.getInt("code")

                            if (code == 200) {

                            } else {
                                val message = json.getString("message")

                                runOnUiThread {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
            })
            alert.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(mContext, "준비가 되면 도전해주세요", Toast.LENGTH_SHORT).show()
            })
            alert.show()
        }
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