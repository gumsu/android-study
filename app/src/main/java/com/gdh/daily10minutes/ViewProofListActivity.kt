package com.gdh.daily10minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdh.daily10minutes.dapters.ProofAdapter
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.datas.Proof
import com.gdh.daily10minutes.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_proof_list.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewProofListActivity : BaseActivity() {

    lateinit var mProject : Project

    val mProofList = ArrayList<Proof>()

    lateinit var mProofAdapter : ProofAdapter

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
                val selectedDataStr = sdf.format(selectedDate.time)
                selectedDateTxt.text = selectedDataStr

//                선택된 날짜의 게시글을 가져오자
                getProofListFromServerByDate(selectedDataStr)

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

//        어댑터 리스트뷰 연결
        mProofAdapter = ProofAdapter(mContext, R.layout.proof_list_item, mProofList)
        proofListView.adapter = mProofAdapter

//        today / sdf를 이용해서, 오늘 날짜의 게시글 목록 가져오기
        getProofListFromServerByDate(sdf.format(today.time))
    }

    //       서버에서 선택된 날짜별 게시글을 가져오자 - 재료 : "2020-06-09" 등의 String

    fun getProofListFromServerByDate(date : String){
        ServerUtil.getRequestProjectProofByIdAndDate(mContext, mProject.id, date, object:ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

//                기존에 들어있던 인증글들은 모두 날려주고
                mProofList.clear()
//                mProofList에 데이터 채워주고 => 리스트뷰 새로고침
                val dataObj = json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val proofsArr = projectObj.getJSONArray("proofs")

                for(i in 0 until  proofsArr.length()){
//                    JSONObject 추출 => Proof 형태로 변경 => mProofList에 추가
                    mProofList.add(Proof.getProofFromJson(proofsArr.getJSONObject(i)))
                }

//                리스트뷰 어댑터에게 새로고침
                runOnUiThread {

                    mProofAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}