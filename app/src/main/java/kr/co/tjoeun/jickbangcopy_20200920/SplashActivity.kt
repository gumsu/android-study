package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
    }
}