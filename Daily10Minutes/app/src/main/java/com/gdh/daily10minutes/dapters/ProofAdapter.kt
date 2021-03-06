package com.gdh.daily10minutes.dapters

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.gdh.daily10minutes.R
import com.gdh.daily10minutes.ViewProofReplyListActivity
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.datas.Proof
import com.gdh.daily10minutes.datas.User
import com.gdh.daily10minutes.utils.ServerUtil
import com.gdh.daily10minutes.utils.TimeUtil
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ProofAdapter(val mContext:Context,
                   resId:Int,
                   val mList:List<Proof> ) : ArrayAdapter<Proof>(mContext,resId,mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = inf.inflate(R.layout.proof_list_item,null)
        }

        val row = tempRow!!

        val proofData = mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val writtenDateTimeTxt = row.findViewById<TextView>(R.id.writtenDateTimeTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        contentTxt.text = proofData.content

        Glide.with(mContext).load(proofData.writer.profileImageList[0]).into(writerProfileImg)
        writerNickNameTxt.text = proofData.writer.nickName

        //        인증글 시간 정보 => TimeUtil의 기능 활용

        writtenDateTimeTxt.text = TimeUtil.getTimeAgoByCalendar(proofData.proofTime)

//        인증글의 이미지가 0개: 이미지뷰 숨기
//        그렇지 않다 (1개 이상): 이미지뷰 보여주기 + Glide 이미지 셋팅
        if(proofData.imageList.size == 0){
            proofImg.visibility = View.GONE
        }else{
            proofImg.visibility = View.VISIBLE
            Glide.with(mContext).load(proofData.imageList[0]).into(proofImg)
        }

//        내 좋아요 여부에 따른 배경색 + 글씨색 변경
        if (proofData.isMyLike){
//            res 폴더의 자원으로 배경설정: setBackgroundResource
            likeBtn.setBackgroundResource(R.drawable.red_border_box)

//            res 폴더의 자원으로 글씨색 설정: 제공되는 함수가 없다.
//            (Context의 도움을 받아서)직접 res 폴더로 가서 그 안의 color 값을 강제로 추출한다.
            likeBtn.setTextColor(mContext.resources.getColor(R.color.red))
        }else{
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            likeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))
        }

//        좋아요 개수
        likeBtn.text = "좋아요 ${proofData.likeCount}개"

//        좋아요 클릭 이벤트 처리
        likeBtn.setOnClickListener {
            ServerUtil.postRequestLikeProof(mContext, proofData.id, object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {

//                        서버에서 주는 응답을 보면 이 인증글의 최신 상태를 내려준다.
//                        이 글을 파싱해서 => 리스트 내용 일부 변경

//                        proofData 변수가 목록에 등장 => proofData 변수의 일부 내용 변경: 목록에 변경 반영
                        proofData.likeCount = json.getJSONObject("data").getJSONObject("like").getInt("like_count")
                        proofData.isMyLike = json.getJSONObject("data").getJSONObject("like").getBoolean("my_like")

                        val message = json.getString("message")

//                        어댑터는 액티비티가 아니다. runOnUIThread 기능을 갖고 있지 않다.
//                        그럼에도 UI 쓰레드에서 토스트를 띄워야 한다.
//                        쓰레드처럼 동작: Handler를 이용해 UIThread에 접근하자.

//                        getMainLooper를 통해 이 Handler는 UI 쓰레드로 접근
                        val myHandler = Handler(Looper.getMainLooper())

//                        ui 스레드에 post { } 내부의 코드를 실행하도록 요청
                        myHandler.post {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

//                            변수의 변경 내용을 리스트뷰에 반영
                            notifyDataSetChanged()
                        }
                    }
                })
        }

//        댓글 달기 버튼을 누르면 해당 인증글의 댓글 목록 화면으로 이동

        replyBtn.setOnClickListener {

            val myIntent = Intent(mContext,ViewProofReplyListActivity::class.java)
            myIntent.putExtra("proof",proofData)

//            어댑터는 startActivity 기능이 없다. mContext(어떤 화면?)의 기능을 활용한다.
            mContext.startActivity(myIntent)
        }
        return row
    }
}