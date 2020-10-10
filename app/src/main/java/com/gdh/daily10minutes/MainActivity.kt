package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            ServerUtil을 이용해서 실제 로그인 시도, 가이드북 추가
            ServerUtil.postRequestLogin(inputId,inputPw,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

//                    로그인 실행 결과에 따라 행동할 내용을 적는 공간


                    Log.d("메인화면","로그인 응답 확인")
                }
            })
        }
    }

    override fun setValues() {
    }
}