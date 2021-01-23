package com.gdh.daily10minutes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gdh.daily10minutes.utils.ContextUtil
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

//            응용문제 => 앱을 껐다가 켜도 체크 여부가 유지되도록
//            체크되면 저장 + 화면이 열릴 때 저장된 값 불러오기 -> setter + getter
//            Hint. 하나의 메모장에, 여러 항목 저장할 수 있다.
            ContextUtil.setAutoLogin(mContext,isChecked)
        }

        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext,SignUpActivity::class.java)
            startActivity(myIntent)
        }

        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            ServerUtil을 이용해서 실제 로그인 시도, 가이드북 추가
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    로그인 실행 결과에 따라 행동할 내용을 적는 공간
//                    Log.d("메인화면","로그인 응답 확인")
//                    code라는 이름으로 적힌 int 값을 받아서, 200이냐 아니냐에 따라 다른 행동을 한다.
                    val codeNum = json.getInt("code")

                    Log.d("서버가 알려주는 코드 값", codeNum.toString())

                    if (codeNum == 200) {

//                        응용문제 : 로그인 성공시 로그인한 사용자의 닉네임 토스트 출력
//                         json > data > user > nick_name 추출
                        val dataObj = json.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")
                        val userNickname = userObj.getString("nick_name")

//                        서버가 알려주는 토큰값을 기기에 저장하고 => 화면을 이동하자.
//                        토큰 값을 추출 => 변수에 저장(기기에 저장 X)
                        val token = dataObj.getString("token")
//                        SharedPreferences를 이용해서 기기에 저장 (ContextUtil 클래스 활용)
                        ContextUtil.setLoginUserToken(mContext,token)

                        runOnUiThread {

                            Toast.makeText(mContext, "${userNickname}님 환영합니다", Toast.LENGTH_SHORT).show()

                            val myIntent = Intent(mContext,MainActivity::class.java)
                            startActivity(myIntent)

//                            메인화면으로 이동하면 로그인 화면은 종료처리
                            finish()
                        }

                    } else {
//                        로그인 실패 => 토스트로 로그인 실패 처리 안내
//                        토스트 : UI 동작 -> UI Thread가 실행하도록 해야함(그냥 하면 앱이 강제종료된다)

//                        연습문제 : 로그인 실패시 실패 사유를 서버가 알려주는 이유로 출력
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {

//        로그인 화면이 열릴 때, 자동 로그인 체크 여부를 체크박스에 설정
        autoLoginCheckBox.isChecked = ContextUtil.isAutoLogin(mContext)
    }
}