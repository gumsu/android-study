package kr.co.tjoeun.fragmentviewpager_20200926.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kr.co.tjoeun.fragmentviewpager_20200926.fragments.MyAddressFragment
import kr.co.tjoeun.fragmentviewpager_20200926.fragments.MyAgeFragment
import kr.co.tjoeun.fragmentviewpager_20200926.fragments.MyNameFragment

class MainViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        // 뷰페이저가 총 몇 개의 프래그먼트(페이지)를 사용할 것인지???
        return 3
    }

    override fun getItem(position: Int): Fragment {
        // 각각의 position에 맞는 프래그먼트 자리 지정
        return when(position){
            0 -> {MyNameFragment()}
            1 -> {MyAgeFragment()}
            else -> {MyAddressFragment()}
        }
    }

}