package kr.co.tjoeun.intent_20200823

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
//    1000이라는 숫자만 봐서는 => 닉네임을 받는 요청이다라는 사실을 인지하기가 힘들다.
    val REQUEST_FOR_NICKNAME = 1000
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        다른화면으로 이동하기 버튼이 눌리면
        moveToOtherActivityBtn.setOnClickListener {
            val myIntent = Intent(this, OtherActivity::class.java)
            startActivity(myIntent)
        }

//        메시지 전달 버튼이 눌리면
        sendMessageBtn.setOnClickListener {
            val inputMessage = messageEdt.text.toString()
            val myIntent = Intent(this, MessageActivity::class.java)
            myIntent.putExtra("message", inputMessage)
            startActivity(myIntent)
        }

//        닉네임 변경 버튼이 눌리면
        changeNickNameBtn.setOnClickListener { 
            
//            닉네임 입력화면으로 이동 (입력 결과를 받으러 가는 것이다)
            val myIntent = Intent(this,EditNickNameActivity::class.java)
            
//            (닉네임 입력 => 1000 숫자로 대신 표기) 결과를 받으러 이동한다고 별도로 명시
//            1000 대신 => 멤버변수로 만들어둔 REQUEST_FOR_NICKNAME를 활용, 가독성 향상
            startActivityForResult(myIntent,REQUEST_FOR_NICKNAME)
        }

//        DIAL 액션 예제
         dialBtn.setOnClickListener {
//             입력한 폰번 받아오기
             val inputPhoneNum = phoneNumEdt.text.toString()

             val myUri = Uri.parse("tel:${inputPhoneNum}")
             val myIntent = Intent(Intent.ACTION_DIAL,myUri)
             startActivity(myIntent)
         }

//        CALL 액션 예제 => 실행 불가
        callBtn.setOnClickListener {
            val inputPhoneNum = phoneNumEdt.text.toString()

            val myUri = Uri.parse("tel:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_CALL,myUri)
            startActivity(myIntent)
        }

        smsBtn.setOnClickListener {
            val inputPhoneNum = phoneNumEdt.text.toString()

            val myUri = Uri.parse("smsto:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_SENDTO,myUri)

//            문자 내용을 미리 작성해서 첨부
            myIntent.putExtra("sms_body","[공유] 이 앱을 다운로드 해주세요")
            startActivity(myIntent)
        }
    }

    /*
    결과를 가지고 돌아올 때 실행되는 함수
    닉네임, 이메일, 폰번 등 모든 입력결과가 다 이 함수에서 실행됨
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        닉네임을 가지러 갔다가 오는 길인가?
        if (requestCode == REQUEST_FOR_NICKNAME){

//            ok를 누른게 맞나? (취소를 누르면 아무일도 X)
            if (resultCode == Activity.RESULT_OK){

//                둘 다 맞아야만 닉네임 (다른 화면에서 입력해준 값으로)을 변경 처리 할 것
                val newNickName = data?.getStringExtra("nick")

                nickNameTxt.text = newNickName
            }
        }
    }
}