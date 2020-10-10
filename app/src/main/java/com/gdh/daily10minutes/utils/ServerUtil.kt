package com.gdh.daily10minutes.utils

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class ServerUtil {

    companion object{

        val HOST_URL = "http://15.164.153.174"

        fun postRequestLogin(id:String, pw:String){

            val client = OkHttpClient()

//            로그인 주소: http://15.164.153.174/user
//            http://15.164.153.174/api/docs/
//            Intent(mContext, 목적지) => 목적지 대응 : 기능 주소
            val urlString = "${HOST_URL}/user"

//            putExtra("이름표", 실제값) => 폼데이터.add("서버 요구 이름표", 실제 첨부 값)
            val formData = FormBody.Builder()
                .add("email",id)
                .add("password",pw)
                .build()

//            요청의 모든 정보를 담고 있는 Request를 생성하자.
//            Intent 덩어리에 대응되는 개념
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
//                .header() API가 header 데이터를 요구하면 달아주는 곳
                .build()

//            완성된 Request를 가지고 실제로 서버에 연결하는 (클라이언트의 일) 코드
            client.newCall(request)
        }
    }
}