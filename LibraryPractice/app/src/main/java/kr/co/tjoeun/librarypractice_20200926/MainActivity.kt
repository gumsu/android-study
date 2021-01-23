package kr.co.tjoeun.librarypractice_20200926

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        callPhoneBtn.setOnClickListener {

            val pl = object : PermissionListener{

                override fun onPermissionGranted() {
                    val phoneNum = phoneNumTxt.text.toString()

                    val myUri = Uri.parse("tel:${phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL,myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "권한이 거부되어 통화가 안됩니다", Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("권한이 거부되었습니다. [설정]에서 권한을 켜주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()
        }

        profilePhotoImg.setOnClickListener {
//            Toast.makeText(mContext, "사진 클릭됨", Toast.LENGTH_SHORT).show()
            val myIntent = Intent(mContext,ViewProfilePhotoActivity::class.java)
            startActivity(myIntent)
        }

        loadWebImageButton.setOnClickListener {
            Glide.with(mContext).load("https://img.sbs.co.kr/newimg/news/20200221/201405004_1280.jpg").into(otherPersonImg)
        }
    }

    override fun setValues() {

    }
}