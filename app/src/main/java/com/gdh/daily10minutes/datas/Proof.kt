package com.gdh.daily10minutes.datas

import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class Proof {
    var id = 0
    var content = ""

//    사진 주소 목록
    val imageList = ArrayList<String>()

//    인증 시간 기록 (String으로 내려오지만, Calendar로 보관하고 싶음)
    val proofTime = Calendar.getInstance()

//    작성자 정보 - User 형태로 보관
    var writer = User()

    companion object{

        fun getProofFromJson(json: JSONObject): Proof{
            val proof = Proof()

            proof.id = json.getInt("id")
            proof.content = json.getString("content")

            return proof
        }
    }
}