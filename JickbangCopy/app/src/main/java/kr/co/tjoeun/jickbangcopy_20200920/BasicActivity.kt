package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class BasicActivity : AppCompatActivity() {
//    화면을 나타내주기 위해 작성
    val mContext = this

//    자주 구현하는 함수를 추상 클래스로 만들어서 반드시 구현하도록 강제
    abstract fun setupEvents()
    abstract fun setValues()
}