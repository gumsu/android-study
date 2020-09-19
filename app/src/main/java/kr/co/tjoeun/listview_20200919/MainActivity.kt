package kr.co.tjoeun.listview_20200919

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.listview_20200919.datas.Student
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val mStudentList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStudentList.add(Student("조경진","서울시 은평구",1988))
        mStudentList.add(Student("구도희","인천시 부평구",1995))
        mStudentList.add(Student("모준승","안양시 동안구",1992))
        mStudentList.add(Student("백창주","서울시 관악구",1988))
        mStudentList.add(Student("신동철","서울시 종로구",1987))
        mStudentList.add(Student("신지환","서울시 금천구",1991))
        mStudentList.add(Student("우병현","서울시 관악구",1986))
        mStudentList.add(Student("이도형","서울시 광진구",1974))
        mStudentList.add(Student("임태규","서울시 송파구",1969))
        mStudentList.add(Student("최성호","경기도 부천시",1979))
        mStudentList.add(Student("주지현","서울시 송파구",1993))
    }
}