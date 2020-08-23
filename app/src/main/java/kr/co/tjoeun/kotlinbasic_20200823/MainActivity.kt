package kr.co.tjoeun.kotlinbasic_20200823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        clickMeBtn.setOnClickListener { 
//            클릭미 버튼이 눌리면 실행될 코드 { }

            Log.d("메인화면", "클릭 미 버튼 눌림")
        }
        makeLogBtn.setOnClickListener {
            Log.d("메인화면", "로그 남기기 버튼 눌림")
//            xml에 추가한 버튼을 makeLogBtn으로 이름지어서 => 눌리면 "로그 남기기 버튼 눌림" 로그 남겨보기
        }

        toastBtn.setOnClickListener {
//            사용자에게 "버튼 눌림" 안내 문구 제공
            Toast.makeText(this, "버튼 눌림", Toast.LENGTH_SHORT).show()
        }
    }
}