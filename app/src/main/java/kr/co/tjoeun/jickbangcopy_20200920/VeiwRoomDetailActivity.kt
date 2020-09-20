package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_veiw_room_detail.*
import kotlinx.android.synthetic.main.room_list_item.*
import kotlinx.android.synthetic.main.room_list_item.descriptionTxt
import kotlinx.android.synthetic.main.room_list_item.priceTxt
import kr.co.tjoeun.jickbangcopy_20200920.datas.Room

class VeiwRoomDetailActivity : BasicActivity() {

    lateinit var mRoonData:Room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veiw_room_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }
    override fun setValues() {
        mRoonData = intent.getSerializableExtra("roomInfo") as Room

        priceTxt.text = mRoonData.getFormatedPrice()

        descriptionTxt.text = mRoonData.description

        addressTxt.text = mRoonData.address
        floorTxt.text = mRoonData.getFormatedFloor()
    }
}