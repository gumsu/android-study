package com.gdh.daily10minutes.datas

class Project {

//    서버에서 내려주는 참여가능 프로젝트의 데이터(하위정보)를 담기 위한 변수들
//    http://15.164.153.174/api/docs/ 에서 get/project에서 execute를 하여 참여 가능한 프로젝트를 확인한다.
//    JSON 포매터로 데이터를 확인하고 저장할 변수를 선언한다.

    var id = 0 // id는 int라고 명시
    var title = "" // title은 String이라고 명시
    var imageURL = ""
    var desc = ""
}