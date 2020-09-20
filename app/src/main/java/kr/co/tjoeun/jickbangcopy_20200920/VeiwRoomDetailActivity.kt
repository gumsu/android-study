package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.jickbangcopy_20200920.datas.Room

class VeiwRoomDetailActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veiw_room_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }
    override fun setValues() {
        val roomData = intent.getSerializableExtra("roomInfo") as Room

    }
}