package kr.co.tjoeun.jickbangcopy_20200920.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class Room (val price:Int,
            val address:String,
            val floor:Int,
            val description: String) : Serializable{

    fun getFormatedFloor():String{
        if(this.floor > 0){
            return "${this.floor}층"
        }else if(this.floor == 0){
            return "반지하"
        }else{
            return "지하 ${-this.floor}층"
        }
    }

    fun getFormatedPrice():String{
        if (this.price < 10000){
            return NumberFormat.getNumberInstance(Locale.KOREA).format(this.price)
        }else{

//            7억 8,500 => 78500 / 10000 = 7   78500 % 10000 = 8000
            val mk = this.price / 10000
            val thousand = this.price % 10000
            val thousandStr = NumberFormat.getNumberInstance(Locale.KOREA).format(thousand)

            return "${mk}억 ${thousandStr}"
        }
    }
}