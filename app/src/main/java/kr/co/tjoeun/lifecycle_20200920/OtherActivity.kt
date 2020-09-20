package kr.co.tjoeun.lifecycle_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        Log.d("다른화면","onCreate 실행됨")

        goBackBtn.setOnClickListener {
            finish()
        }
    }
}