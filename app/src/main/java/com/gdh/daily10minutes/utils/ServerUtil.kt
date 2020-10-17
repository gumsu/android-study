package com.gdh.daily10minutes.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    화면(액티비티)의 입장에서 서버 응답이 돌아왔을 때 어떤 행동을 실행할 지
//    행동 지침을 담아주기 위한 인터페이스 (가이드북/메뉴얼) 정의

    interface JsonResponseHandler{
        fun onResponse(json : JSONObject)
    }

    companion object{

        val HOST_URL = "http://15.164.153.174"

//        로그인 할 때 아이디, 비번, 가이드북까지 담는다.
        fun postRequestLogin(id:String, pw:String, handler : JsonResponseHandler?){

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
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우
//                    인터넷 연결 실패 등 물리적 실패
//                    아이디/비번 틀림 등 로그인 실패는 여기가 아니다.
                }

                override fun onResponse(call: Call, response: Response) {
//                    검사 결과가 성공이든 실패든 관계 없이 서버가 무언가 답변을 해주면 무조건 실행

//                    서버가 내려준 응답 중 본문(body)만 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 가지고 => 분석하기 용이한 JSONObject로 변환
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

//                    어떤 서버 응답 처리를 해줄지 가이드북 (인터페이스)가 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행해달라는 코드
                    handler?.onResponse(jsonObj)
                }

            })
        }

//        회원가입
        fun putRequestSignUp(id:String, pw:String, nickName:String, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//            로그인 주소: http://15.164.153.174/user
//            http://15.164.153.174/api/docs/
//            Intent(mContext, 목적지) => 목적지 대응 : 기능 주소
            val urlString = "${HOST_URL}/user"

//            putExtra("이름표", 실제값) => 폼데이터.add("서버 요구 이름표", 실제 첨부 값)
            val formData = FormBody.Builder()
                .add("email",id)
                .add("password",pw)
                .add("nick_name",nickName)
                .build()

//            요청의 모든 정보를 담고 있는 Request를 생성하자.
//            Intent 덩어리에 대응되는 개념
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
//                .header() API가 header 데이터를 요구하면 달아주는 곳
                .build()

//            완성된 Request를 가지고 실제로 서버에 연결하는 (클라이언트의 일) 코드
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우
//                    인터넷 연결 실패 등 물리적 실패
//                    아이디/비번 틀림 등 로그인 실패는 여기가 아니다.
                }

                override fun onResponse(call: Call, response: Response) {
//                    검사 결과가 성공이든 실패든 관계 없이 서버가 무언가 답변을 해주면 무조건 실행

//                    서버가 내려준 응답 중 본문(body)만 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 가지고 => 분석하기 용이한 JSONObject로 변환
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

//                    어떤 서버 응답 처리를 해줄지 가이드북 (인터페이스)가 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행해달라는 코드
                    handler?.onResponse(jsonObj)
                }

            })
        }

//        중복확인
        fun getRequestEmailCheck(emailAddress: String, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
            urlBuilder.addEncodedQueryParameter("email",emailAddress)

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
//                .header() 필요시 첨부
                .build()

                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val bodyString = response.body!!.string()
                        val jsonObj = JSONObject(bodyString)

                        Log.d("서버 응답 본문",jsonObj.toString())

                        handler?.onResponse(jsonObj)
                    }
                })
        }

//        프로젝트 리스트
        fun getRequestProjectList(context:Context, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
//            urlBuilder.addEncodedQueryParameter("email",emailAddress)

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context))
                .build()

//            ServerUtil에서 context에 접근할 방법이 없기 때문에 함수 파라미터에 context를 추가해서 불러와준다.

                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val bodyString = response.body!!.string()
                        val jsonObj = JSONObject(bodyString)

                        Log.d("서버 응답 본문",jsonObj.toString())

                        handler?.onResponse(jsonObj)
                    }
                })
        }

//        프로젝트 디테일 화면
        fun getRequestProjectInfoById(context:Context, projectId: Int, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
//            urlBuilder.addEncodedQueryParameter("email",emailAddress)

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context))
                .build()

//            ServerUtil에서 context에 접근할 방법이 없기 때문에 함수 파라미터에 context를 추가해서 불러와준다.

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)
                }
            })
        }

//        프로젝트 참여 멤버 확인
        fun getRequestProjectMembersById(context:Context, projectId: Int, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
            urlBuilder.addEncodedQueryParameter("need_user_list","true")

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context))
                .build()

//            ServerUtil에서 context에 접근할 방법이 없기 때문에 함수 파라미터에 context를 추가해서 불러와준다.

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)
                }
            })
        }

//        프로젝트 참여
        fun postRequestApplyProject(context: Context, projectId: Int, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//            로그인 주소: http://15.164.153.174/user
//            http://15.164.153.174/api/docs/
//            Intent(mContext, 목적지) => 목적지 대응 : 기능 주소
            val urlString = "${HOST_URL}/project"

//            putExtra("이름표", 실제값) => 폼데이터.add("서버 요구 이름표", 실제 첨부 값)
            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .build()

//            요청의 모든 정보를 담고 있는 Request를 생성하자.
//            Intent 덩어리에 대응되는 개념
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context)) // API가 header 데이터를 요구하면 달아주는 곳
                .build()

//            완성된 Request를 가지고 실제로 서버에 연결하는 (클라이언트의 일) 코드
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우
//                    인터넷 연결 실패 등 물리적 실패
//                    아이디/비번 틀림 등 로그인 실패는 여기가 아니다.
                }

                override fun onResponse(call: Call, response: Response) {
//                    검사 결과가 성공이든 실패든 관계 없이 서버가 무언가 답변을 해주면 무조건 실행

//                    서버가 내려준 응답 중 본문(body)만 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 가지고 => 분석하기 용이한 JSONObject로 변환
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

//                    어떤 서버 응답 처리를 해줄지 가이드북 (인터페이스)가 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행해달라는 코드
                    handler?.onResponse(jsonObj)
                }

            })
        }

//        프로젝트 포기
        fun deleteRequestGiveUpProject(context:Context, projectId: Int, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
            urlBuilder.addEncodedQueryParameter("project_id",projectId.toString())

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .delete()
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context))
                .build()

//            ServerUtil에서 context에 접근할 방법이 없기 때문에 함수 파라미터에 context를 추가해서 불러와준다.

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)
                }
            })
        }

//        인증 목록 받아오기(아이디와 날짜)
        fun getRequestProjectProofByIdAndDate(context:Context, projectId: Int, date: String, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//              어느 주소로 가야하는가? 통일
//              차이점: 주소를 적을 때 => 어떤 데이터가 첨부되는지(파라미터)도 같이 적어야 한다.
//              POST/PUT 등은 formData 를 이용하지만, GET에서는 주소에 적는다.

//              URL에 파라미터들을 쉽게 첨부하도록 도와주는 URL 가공기 생성
            val urlBuilder = "${HOST_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()
//              URL 가공기를 이용해서 필요한 파라미터들을 쉽게 첨부할 수 있다.
            urlBuilder.addEncodedQueryParameter("proof_date", date)

//              가공이 끝난 URL을 urlString으로 완성
            val urlString = urlBuilder.build().toString()

//              임시 확인: 어떻게 URL이 완성되었는 지 확인
            Log.d("완성된URL",urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context))
                .build()

//            ServerUtil에서 context에 접근할 방법이 없기 때문에 함수 파라미터에 context를 추가해서 불러와준다.

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)
                }
            })
        }

//        좋아요 클릭한 것 전송
        fun postRequestLikeProof(context: Context, proofId: Int, handler : JsonResponseHandler?){

            val client = OkHttpClient()

//            로그인 주소: http://15.164.153.174/user
//            http://15.164.153.174/api/docs/
//            Intent(mContext, 목적지) => 목적지 대응 : 기능 주소
            val urlString = "${HOST_URL}/like_proof"

//            putExtra("이름표", 실제값) => 폼데이터.add("서버 요구 이름표", 실제 첨부 값)
            val formData = FormBody.Builder()
                .add("proof_id", proofId.toString())
                .build()

//            요청의 모든 정보를 담고 있는 Request를 생성하자.
//            Intent 덩어리에 대응되는 개념
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .header("X-Http-Token",ContextUtil.getLoginUserToken(context)) // API가 header 데이터를 요구하면 달아주는 곳
                .build()

//            완성된 Request를 가지고 실제로 서버에 연결하는 (클라이언트의 일) 코드
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우
//                    인터넷 연결 실패 등 물리적 실패
//                    아이디/비번 틀림 등 로그인 실패는 여기가 아니다.
                }

                override fun onResponse(call: Call, response: Response) {
//                    검사 결과가 성공이든 실패든 관계 없이 서버가 무언가 답변을 해주면 무조건 실행

//                    서버가 내려준 응답 중 본문(body)만 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 가지고 => 분석하기 용이한 JSONObject로 변환
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버 응답 본문",jsonObj.toString())

//                    어떤 서버 응답 처리를 해줄지 가이드북 (인터페이스)가 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행해달라는 코드
                    handler?.onResponse(jsonObj)
                }

            })
        }
    }
}