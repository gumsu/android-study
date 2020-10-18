package com.gdh.daily10minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.gdh.daily10minutes.datas.Proof
import com.gdh.daily10minutes.utils.TimeUtil
import kotlinx.android.synthetic.main.activity_view_proof_reply_list.*

class ViewProofReplyListActivity : BaseActivity() {

    lateinit var mProof : Proof

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_reply_list)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProof = intent.getSerializableExtra("proof") as Proof

        Glide.with(mContext).load(mProof.writer.profileImageList[0]).into(writerProfileImg)
        writerNickNameTxt.text = mProof.writer.nickName
        contentTxt.text = mProof.content

        if (mProof.imageList.size == 0) {
            proofImg.visibility = View.GONE
        }
        else {
            proofImg.visibility = View.VISIBLE
            Glide.with(mContext).load(mProof.imageList[0]).into(proofImg)
        }

        writtenDateTimeTxt.text = TimeUtil.getTimeAgoByCalendar(mProof.proofTime)
    }
}