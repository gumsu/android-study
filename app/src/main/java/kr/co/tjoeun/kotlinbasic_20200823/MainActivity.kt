package kr.co.tjoeun.kotlinbasic_20200823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        clickMeBtn.setOnClickListener { 
//            클릭미 버튼이 눌리면 실행될 코드 { }
            
            Log.d("메인화면", "클릭 미 버튼 눌림")
        }
    }
}