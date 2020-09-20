package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        버튼 이벤트 / 리스트뷰 이벤트 등 이벤트처리만 모아두는 코드
    }

    override fun setValues() {
//        .text 값 대입 등 화면 문구/데이터 반영하는 코드만.
    }
}