package kr.co.tjoeun.listview_20200919

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.listview_20200919.adapters.StudentAdapter
import kr.co.tjoeun.listview_20200919.datas.Student
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val mStudentList = ArrayList<Student>()

    lateinit var mAdapter: StudentAdapter

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

        mAdapter = StudentAdapter(this,R.layout.student_list_item,mStudentList)
        studentListView.adapter = mAdapter

        studentListView.setOnItemClickListener { parent, view, position, id ->
            val clickedStudent = mStudentList[position]

            Toast.makeText(this,clickedStudent.name,Toast.LENGTH_SHORT).show()
        }
        studentListView.setOnItemLongClickListener { parent, view, position, id ->
//            val longClickedStudent = mStudentList[position]

            val alert = AlertDialog.Builder(this)   //빌더는 덧붙여서 만들어나간다는 의미로 이해하자
            alert.setTitle("학생 삭제 확인")
            alert.setMessage("정말 해당 학생을 삭제하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                mStudentList.removeAt(position)

                mAdapter.notifyDataSetChanged()
            })
            alert.setNegativeButton("취소",null)

            alert.show()

            /*
            removeAt만 사용하면 삭제된 데이터 반영이 되지 않아 강제 종료된다.
            데이터가 갱신되었음을 어댑터가 알아차려야 한다.
             */
            return@setOnItemLongClickListener true
        }
    }
}