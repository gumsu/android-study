package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import kotlin.math.log

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
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    로그인 실행 결과에 따라 행동할 내용을 적는 공간
//                    Log.d("메인화면","로그인 응답 확인")
//                    code라는 이름으로 적힌 int 값을 받아서, 200이냐 아니냐에 따라 다른 행동을 한다.
                    val codeNum = json.getInt("code")

                    Log.d("서버가 알려주는 코드 값", codeNum.toString())

                    if (codeNum == 200) {

                    } else {
//                        로그인 실패 => 토스트로 로그인 실패 처리 안내
//                        토스트 : UI 동작 -> UI Thread가 실행하도록 해야함(그냥 하면 앱이 강제종료된다)
                         runOnUiThread {
                             Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                         }
                    }
                }
            })
        }
    }

    override fun setValues() {
    }
}