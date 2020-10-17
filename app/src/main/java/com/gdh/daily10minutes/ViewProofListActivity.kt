package com.gdh.daily10minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdh.daily10minutes.datas.Project
import kotlinx.android.synthetic.main.activity_view_proof_list.*
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

//        날짜 선택 버튼이 눌리면 => 달력을 띄워서 날짜 선택 받기
        selectDateBtn.setOnClickListener {

//            DatePickerDialog 활용
            val dpd = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

//                선택된 년/월/일을 가지고 할 일 작성
//                선택된 년월일 => 2020-05-06양식으로 가공해서 로그 출력
                val selectedDate = Calendar.getInstance() //오늘 날짜가 들어있는 상태
//                선택된 년월일로 일괄 적용
                selectedDate.set(year,month,dayOfMonth)
//                날짜 변환 양식 생성
                val sdf = SimpleDateFormat("yyyy-MM-dd")
//                Log.d("선택된 날짜",sdf.format(selectedDate.time))
                selectedDateTxt.text = sdf.format(selectedDate.time)

            }, 2020, Calendar.JUNE, 10) //기본값 설정(달력을 클릭했을 때 2020년6월10일이 기본으로 나온다)

            dpd.show()
        }
    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

//        이 화면이 열릴때는, 오늘 날짜를 알아내서 => 2020-08-08 형태로 가공해서 출력

//        1) 오늘 날짜 알아내기 =? 날짜를 어떻게 변수에?
        val today = Calendar.getInstance()
//        2) 날짜를 => 2020-08-08 String으로 바꾸는 방법? => 텍스트뷰에 반영
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        selectedDateTxt.text = sdf.format(today.time)
    }
}