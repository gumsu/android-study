package kr.co.tjoeun.kotlinbasic_20200823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        clickMeBtn.setOnClickListener { 
//            클릭미 버튼이 눌리면 실행될 코드 { }
            Log.d("메인화면", "클릭 미 버튼 눌림")
        }

        makeLogBtn.setOnClickListener {
            Log.d("메인화면", "로그 남기기 버튼 눌림")
//            xml에 추가한 버튼을 makeLogBtn으로 이름지어서 => 눌리면 "로그 남기기 버튼 눌림" 로그 남겨보기
        }

        toastBtn.setOnClickListener {
//            사용자에게 "버튼 눌림" 안내 문구 제공
            Toast.makeText(this, "버튼 눌림", Toast.LENGTH_SHORT).show()
        }

//        입력한 문구를 받아서 => 토스트로 출력
        contentToastBtn.setOnClickListener {
////            contentEdt의 문구 (text 속성의 값) => (String으로) 받아서 => 코틀린 변수에 저장
//            val inputContent = contentEdt.text.toString()
////            변수에 저장된 문구를 => 토스트의 재료로 사용
//            Toast.makeText(this, inputContent, Toast.LENGTH_SHORT).show()

//            입력된 문구가 "안녕하세요" 라고 입력되었다면 => "인사"라고 토스트 출력
            val inputContent = contentEdt.text.toString()

            when (inputContent) {
                "안녕하세요" -> {
                    Toast.makeText(this, "인사", Toast.LENGTH_SHORT).show()
                }
                "안녕히 가세요" -> {
                    Toast.makeText(this, "작별 인사", Toast.LENGTH_SHORT).show()
                }
                "맛있게 드세요" -> {
                    Toast.makeText(this, "식사 인사", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "그 외의 문장", Toast.LENGTH_SHORT).show()
                }
            }
        }

        changeTextBtn.setOnClickListener {
//            messageEdt에 저장된 문구를 String으로 받아서 (get)
             val inputMessage = messageEdt.text.toString()
//            resultTxt의 문구(text속성의 값)로 반영 (set)
            resultTxt.text = inputMessage
        }

        greetingBtn.setOnClickListener {
//            입력된 이름을 가지고 "안녕하세요 저는 ~~입니다." 문장 출력
            val inputName = nameEdt.text.toString()

            val printMessage = "안녕하세요 저는 ${inputName} 입니다"

            Toast.makeText(this,printMessage,Toast.LENGTH_SHORT).show()
        }
    }
}