package kr.co.tjoeun.jickbangcopy_20200920.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kr.co.tjoeun.jickbangcopy_20200920.R
import kr.co.tjoeun.jickbangcopy_20200920.datas.Room

class RoomAdapter(
    val mContext:Context,
    val resId:Int,
    val mList:ArrayList<Room>):ArrayAdapter<Room>(mContext,resId,mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = inf.inflate(R.layout.room_list_item,null)
        }

        val row = tempRow!!

        val priceTxt = row.findViewById<TextView>(R.id.priceTxt)
        val addressAndFloorTxt = row.findViewById<TextView>(R.id.addressAndFloorTxt)
        val descriptionTxt = row.findViewById<TextView>(R.id.descriptionTxt)

        val roomData = mList[position]

        descriptionTxt.text = roomData.description

        addressAndFloorTxt.text = "${roomData.address}, ${roomData.getFormatedFloor()}"

        priceTxt.text = roomData.getFormatedPrice()
        return  row
    }
}