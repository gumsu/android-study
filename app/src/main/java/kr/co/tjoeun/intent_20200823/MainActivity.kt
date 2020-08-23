package kr.co.tjoeun.intent_20200823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        다른화면으로 이동하기 버튼이 눌리면
        moveToOtherActivityBtn.setOnClickListener {
            val myIntent = Intent(this, OtherActivity::class.java)

            startActivity(myIntent)
        }
    }
}