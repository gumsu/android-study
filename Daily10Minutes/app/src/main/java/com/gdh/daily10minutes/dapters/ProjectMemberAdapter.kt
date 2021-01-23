package com.gdh.daily10minutes.dapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gdh.daily10minutes.R
import com.gdh.daily10minutes.datas.Project
import com.gdh.daily10minutes.datas.User

class ProjectMemberAdapter(val mContext:Context,
                           resId:Int,
                           val mList:List<User> ) : ArrayAdapter<User>(mContext,resId,mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = inf.inflate(R.layout.user_list_item,null)
        }

        val row = tempRow!!

        val userData = mList[position]

        val userProfileImg = row.findViewById<ImageView>(R.id.userProfileImg)
        val userNickNameTxt = row.findViewById<TextView>(R.id.userNickNameTxt)
        val userEmailTxt = row.findViewById<TextView>(R.id.userEmailTxt)

        userNickNameTxt.text = userData.nickName
        userEmailTxt.text = userData.email

//        사용자 프사 중 0번째 프사를 => 이미지뷰에 반영
        Glide.with(mContext).load(userData.profileImageList[0]).into(userProfileImg)
        return row
    }
}