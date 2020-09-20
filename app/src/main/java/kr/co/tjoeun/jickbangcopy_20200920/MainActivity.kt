package kr.co.tjoeun.jickbangcopy_20200920

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.jickbangcopy_20200920.adpaters.RoomAdapter
import kr.co.tjoeun.jickbangcopy_20200920.datas.Room

class MainActivity : BasicActivity() {

    val mRooms = ArrayList<Room>()

    lateinit var mRoomAdapter: RoomAdapter

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

        mRooms.add(Room(8500, "서울시 은평구", 5, "은평구의 5층 방 입니다."))
        mRooms.add(Room(78000, "서울시 은평구", 3, "은평구의 3층 방 입니다."))
        mRooms.add(Room(2400, "서울시 은평구", 0, "은평구의 반지하 방 입니다."))
        mRooms.add(Room(23500, "서울시 서대문구", -1, "서대문구의 지하 1층 방 입니다."))
        mRooms.add(Room(175000, "서울시 서대문구", 4, "서대문구의 4층 방 입니다."))
        mRooms.add(Room(55000, "서울시 서대문구", 7, "서대문구의 7층 방 입니다."))
        mRooms.add(Room(9600, "서울시 동대문구", 0, "동대문구의 반지하 방 입니다."))
        mRooms.add(Room(38000, "서울시 동대문구", 2, "동대문구의 2층 방 입니다."))
        mRooms.add(Room(57000, "서울시 동대문구", -2, "동대문구의 지하 2층 방 입니다."))
        mRooms.add(Room(85000, "서울시 동대문구", 5, "동대문구의 5층 방 입니다."))

        mRoomAdapter = RoomAdapter(mContext, R.layout.room_list_item, mRooms)
        roomListView.adapter = mRoomAdapter
    }
}