package kr.co.tjoeun.lifecycle_20200920

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        다른 화면을 갔다와도 또 실행되지 않는다. 재활용함
        Log.d("메인화면","onCreate 실행됨")

        moveToOtherBtn.setOnClickListener {
            val myIntent = Intent(this,OtherActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("메인화면","onResume 실행됨")
    }

    override fun onPause() {
        super.onPause()

        Log.d("메인화면","onPause 실행됨")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("메인화면","onDestroy 실행됨")
    }
}