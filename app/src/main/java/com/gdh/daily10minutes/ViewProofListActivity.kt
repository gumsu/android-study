package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdh.daily10minutes.datas.Project
import java.text.SimpleDateFormat
import java.util.*

class ViewProofListActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_list)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

//        이 화면이 열릴때는, 오늘 날짜를 알아내서 => 202년 10월 17일 형태로 가공해서 출력

//        1) 오늘 날짜 알아내기 =? 날짜를 어떻게 변수에?
        val today = Calendar.getInstance() // 현재 시간을 가지고 today에 저장

//        today 내부의 데이터 조회? get
        Log.d("캘린더 연습-연도", today.get(Calendar.YEAR).toString())
        Log.d("캘린더 연습-월", today.get(Calendar.MONTH).toString())
        Log.d("캘린더 연습-일", today.get(Calendar.DAY_OF_MONTH).toString())

        /*
        오늘이 아닌, 다른 날짜를 세팅?
        1. 항목을 지정하고 그 항목의 값을 변경 -> 일을 1일로 변경하고 싶다면
         */
        today.set(Calendar.DAY_OF_MONTH, 1)
        /*
        2. 년/월/일 데이터 한꺼번에 변경하고 싶다면 => 2020 / 6 / 10
         */
        today.set(2020, Calendar.JUNE,10) // 월은 영어 이름으로 해야 정확히 들어감


//        2) 날짜를 => 2020년 10월 17일 String으로 바꾸는 방법?
    }
}