package kr.co.tjoeun.intent_20200823

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_nick_name.*

class EditNickNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nick_name)

        okBtn.setOnClickListener {
//            입력한 닉네임 받아오기
            val inputNickName = nickNameEdt.text.toString()

//            입력값을 담기 위한 용도의 Intent 생성
            val resultIntent = Intent()

//            입력값을 resultIntent에 첨부
            resultIntent.putExtra("nick",inputNickName)

//            이 화면의 최종 결과를 설정
            setResult(Activity.RESULT_OK,resultIntent)

//            메인화면으로 복귀
            finish()
        }
    }
}