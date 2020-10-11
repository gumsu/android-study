package com.gdh.daily10minutes.utils

import android.content.Context

class ContextUtil {

    companion object{

//        메모장의 파일 이름처럼 SharedPreferences의 이름을 짓고 변수로 만들어서 활용
//        ContextUtil 내부에서만 사용하기 위한 변수 (다른 코틀린 파일 등 외부에 공개X) -> private

        private  val prefName = "Daily10MinutesPref"

//        저장해줄 항목 이름을 변수로 생성 (오타 방지용)
        private  val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

//        실제 데이터 저장 함수 (setter) 생성
        fun setLoginUserToken(context: Context, token:String){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

//        저장된 토큰 조회 함수 (getter) 생성
        fun getLoginUserToken(context: Context) : String{
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN,"")!!
        }
    }
}