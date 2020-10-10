package com.gdh.daily10minutes.dapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.gdh.daily10minutes.R
import com.gdh.daily10minutes.datas.Project

class ProjectAdapter(val mContext:Context,
                     resId:Int,
                     val mList:List<Project> ) : ArrayAdapter<Project>(mContext,resId,mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = inf.inflate(R.layout.project_list_item,null)
        }

        val row = tempRow!!

//        실제 데이터 반영 필요

        return row
    }
}