# 🚀 MadCampProject1_Galaxy

<img src = "https://github.com/Gloveman/CampProject1/assets/135544903/e45b5365-059a-44de-b029-8a48dd86d7eb" height ="400" weight = "400"/>

## 🖥️ 프로젝트 소개
3개의 Tab을 가진 Android application

## 🧑🏻‍🚀팀원
이창우, 조세연

# Splash screen
![splash_AdobeExpress](https://github.com/Gloveman/CampProject1/assets/135544903/66eb195b-e91c-4ed3-8980-72eb86474a97)

lottieanimation을 startup activity에서 실행하고 일정 시간 후에 mainactivity로 넘어가도록 했다.
# Tab1_contact

|scroll|action|group|
|------|------|------|
|![tab1_scroll](https://github.com/Gloveman/CampProject1/assets/135544903/258962f8-d640-4417-b799-80ed2181efb1)|![tab1_action](https://github.com/Gloveman/CampProject1/assets/135544903/be5cac70-7851-4175-b1ed-051ac22306e4)|![tab1_group](https://github.com/Gloveman/CampProject1/assets/135544903/687a7f6c-3a70-4813-b29b-5909e94a3dfd)

연락처 목록은 휴대폰의 연락처 데이터베이스에서 직접 불러왔다. 이를 위해 ```android.permission.READ_CONTACTS``` 권한을 이용한다.

연락처 항목을 누르면 ```전화 걸기```, ```문자 보내기```, ```그룹에 추가하기``` 중에 하나를 선택할 수 있다. ```전화 걸기```를 누르면 바로 통화가 시작된다. 이를 위해 ```android.permission.CALL_PHONE``` 권한을 이용한다.

```문자 보내기```를 누르면 휴대폰의 기본 메세지 앱이 열려 문자를 보낼 수 있다.
```그룹에 추가하기```를 누르면 어떤 그룹에 추가할지 선택하게 된다. ```즐겨찾기```는 기본적으로 있는 그룹이며, 필요 시 ```새 그룹```을 눌러 자유롭게 그룹을 추가할 수 있다.

그룹 항목을 누르면 해당 그룹에 속한 연락처 목록을 보여준다. ```전화 걸기```나 ```문자 보내기```는 이전과 같으며, ```그룹에서 삭제하기```를 통해 해당 그룹에서 연락처를 제외할 수 있다. 원하면 아래 ```그룹 삭제```버튼을 통해 삭제도 가능하다.

# Tab2_gallery
|scroll|share|
|------|------|
![tab2_scroll](https://github.com/Gloveman/CampProject1/assets/135544903/b82532cb-f311-41e4-a6bf-7d04d6ba2865)|![tab2_share](https://github.com/Gloveman/CampProject1/assets/135544903/74660b38-09ab-4c7b-981d-2ff67281c451)|

갤러리 역시 휴대폰 저장소에서 이미지들을 직접 불러왔다. 이를 위해 ```android.permission.READ_EXTERNAL_STORAGE``` 권한을 이용한다.

이미지를 누르면 해당 이미지를 전체 화면으로 보여주며 좌우 슬라이드를 통해 다음 또는 이전 이미지로 넘어갈 수 있다.

```공유```버튼을 누르면 사진을 다른 앱으로 공유할 수 있다. 이를 위해 ```android.permission.INTERNET``` 권한을 이용한다.

# Tab3_calender & memo
|scroll|add|edit|
|------|------|------|
|![tab3_scroll](https://github.com/Gloveman/CampProject1/assets/135544903/f6efd06d-4ae5-433b-ab24-1406f5e6f855)|![tab3_add](https://github.com/Gloveman/CampProject1/assets/135544903/6aaa9fbb-828d-409c-81a6-ccf20df674e3)|![tab3_edit](https://github.com/Gloveman/CampProject1/assets/135544903/d4009b0f-d414-4aa0-aba5-a6a2f931a5b8)|

Tab3에는 캘린더 메모 기능을 구현하였다.

원하는 날짜를 선택하여 ```새 메모```버튼을 눌러 메모를 추가할 수 있고, ```메모 수정```을 통해 메모 수정도 가능하다. 원하면 ```메모 삭제```를 통해 메모를 삭제할 수 있다.

메모는 모두 앱의 내부 저장소에 JSON 파일로 저장되어 있다.
