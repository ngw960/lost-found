**Lost and Found Platform**

![Image](https://github.com/user-attachments/assets/73ea46bc-9be4-481c-962a-4f754d0e5345)

**팀원 구성**

| **박소라** | **유영훈** | **노광우** | **김혜림** |
| --- | --- | --- | --- |
| P | Y | N | K |
| [@yuuminaegger](https://github.com/yuuminaegger) | [@TyulRip](https://github.com/TyulRip) | [@ngw960](https://github.com/ngw960) | [@khr316](https://github.com/khr316) |

**역할 분담**

**박소라 P**

- 백엔드 Controller, Dao, 프론트엔드 Html, css, javascript
- 
    1. 회원가입 아이디 중복체크, 빈 칸 입력 필수, 비밀번호 & 비밀번호 확인 일치
    2. 로그인 DB 에 저장 된 회원만 로그인 가능 잘못된 아이디 or 비밀번호 입력 시 에러 메세지 출력
    3. 관리자 페이지 회원 탈퇴, 게시글 & 댓글 삭제
    4. 게시글 로그인 = 글 작성자 : 게시글 수정 & 삭제 로그인 = 댓글 작성자 : 댓글 수정 & 삭제 로그인 = 관리자 : 게시글, 댓글 삭제
- 
- 
    1. 메인페이지
    2. 로그인
    3. 회원가입

**유영훈 Y**

- 프론트엔드 Html수정 + css , javascript
- 
    1. 분실물 게시판
    2. 습득물 게시판
    3. 분실물 검색 결과
    4. 습득물 검색 결과
    5. 쪽지 확인
    6. 쪽지 보내기
    7. 쪽지 목록
    8. 로그인
    9. 회원가입
    10. 마이페이지
    11. 관리자 페이지
    12. 글쓰기
    13. 게시글

**노광우 N**

- 조장
- 백엔드 Controller, Dao, Html, javascript
- 
    1. 메인페이지
    2. 분실물 게시판
    3. 습득물 게시판
    4. 분실물 검색 결과
    5. 습득물 검색 결과 최신순, 오래된 순
    6. 비밀번호 찾기 가입한 이메일로 임시 비밀번호 발급 => K 가 구현한 비밀번호 찾기 기능 개선
- 총괄 관리 및 서류 작성

**김혜림 K**

- 백엔드 Controller, Dao, 프론트엔드 Html, css, javascript
- 
    1. 마이페이지 회원 정보 수정 (비밀번호, 전화번호, 이메일) 쪽지 확인 작성한 게시글
    2. 글쓰기 위치 검색, 물품 선택, 이미지 등록
    3. 쪽지 받은 & 보낸 쪽지 확인, 답장기능, 새로운 쪽지 알림
    4. 비밀번호 찾기 아이디 입력 시 비밀번호 발급 => 개인정보 보호 X, 다른 사람도 아이디를 안다면 비번 확인 가능
- 
- 
    1. 메인페이지
    2. 로그인
    3. 회원가입

**프로젝트 소개**

**LUCKY PYNK**

- 물건을 분실한 경우 글을 등록하여 분실물을 찾을 수 있는 플랫폼
- 물건을 습득한 경우 글을 등록하여 물건의 주인을 찾을 수 있는 플랫폼
- 회원가입 필수 (회원만 이용 가능)
- 키워드, 지역 별로 게시글 검색 가능
- 게시글에 댓글 기능 추가
- 쪽지 기능을 넣어 회원들간의 소통을 중시

**목표**

- 효율적인 분실물 관리 서비스 플랫폼 구축
- 사용친화적이고 직관적인 UI / UX 디자인 구현

**목적**

- 분실물 찾기 성공률 향상
- 커뮤니티 형성 및 신뢰 구축
- 시간 및 비용 절감
- 사회적 문제 해결 기여

**요구사항 분석**

1. 분실물 등록 사용자가 분실물 정보를 입력하여 게시글 등록
2. 분실물 검색 키워드 및 지역 별로 검색 가능
3. 사용자 인증 및 관리 회원가입, 로그인, 로그아웃
4. 분실물 상태 관리 분실물의 상태 업데이트 가능 (찾는 중 or 찾기 완료)

**개발 환경**

- FE : HTML CSS JavaScript
- BE : Java JDBC SpringBoot Maven Spring-Data-JPA Lombok
- DataBase : MySQL (JDBC)
- 협업 툴 : Github, FileZilla

**데이터베이스 설계**

![Image](https://github.com/user-attachments/assets/5964e869-b923-4bed-b47a-276ffab752e8)

**프로젝트 구조**

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── project
│   │           └── sprint1
│   │               ├── controller
│   │               │   ├── AdminController.java
│   │               │   ├── BoardController.java
│   │               │   ├── MainController.java
│   │               │   ├── MessageController.java
│   │               │   ├── MypageController.java
│   │               │   ├── PasswordController.java
│   │               │   └── UserController.java
│   │               ├── dao
│   │               │   ├── AdminDao.java
│   │               │   ├── BoardDao.java
│   │               │   ├── MainDao.java
│   │               │   ├── MessageDao.java
│   │               │   ├── MypageDao.java
│   │               │   ├── PasswordDao.java
│   │               │   ├── PostDao.java
│   │               │   └── UserDao.java
│   │               ├── service
│   │               │   └── EmailService.java
│   │               └── Sprint1Application.java
│   └── resources
│       ├── static
│       │   ├── css
│       │   └── img
│       ├── js
│       └── templates
│           ├── board
│           │   ├── category.html
│           │   ├── detail.html
│           │   ├── insert.html
│           │   └── location.html
│           ├── menu
│           │   ├── finditem.html
│           │   ├── lostitem.html
│           │   ├── searchfi.html
│           │   └── searchli.html
│           ├── message
│           │   └── message_insert.html
│           │   └── message_notice.html
│           │   └── message.html
│           ├── adminpage.html
│           ├── find-password.html
│           ├── login.html
│           ├── main.html
│           ├── mypage.html
│           ├── password.html
│           └── signup.html
│       └── application.properties

```

**메인 페이지**

![Image](https://github.com/user-attachments/assets/6ab43a7b-8357-45ce-84fd-31aec89e4e78)
간단한 사이트 이용 정보 확인

**회원가입**

![Image](https://github.com/user-attachments/assets/db7de3b0-a14d-49e0-9084-da0a6655e45e)

**로그인**

![Image](https://github.com/user-attachments/assets/77614e8b-f116-45f0-81a1-04123fc2df85)

**로그인 후 메인페이지**

![Image](https://github.com/user-attachments/assets/63b5834e-5569-4843-8a47-035d2129d338)
로그인 상태에 따라 상단바 변화

**마이페이지**

![Image](https://github.com/user-attachments/assets/d99979e4-718a-4688-a6fd-1caaab28fe67)
1. 개인정보 확인 및 수정 가능
2. 작성한 글 확인 분실물 상태 변경기능(찾는중 -> 찾기완료)

**글쓰기**

![Image](https://github.com/user-attachments/assets/56cd6b5a-7640-43a3-834f-6e10ac33308d)
1. 분실물 or 습득물 을 선택하여 각각 게시판에 등록 가능
2. 위치 선택 후 상세위치 입력
3. 이미지 파일 등록
4. 물품 카테고리 선택 후 등록 가능

**게시판**

![Image](https://github.com/user-attachments/assets/892a5ccc-829a-4c93-acff-c5e64e83523f)
![Image](https://github.com/user-attachments/assets/164a0996-f174-4f6b-8b17-68f51940ed5d)
1. 우측 상단 버튼을 통해 분실물, 습득물 게시판 자유롭게 이동 가능
2. 제목 키워드 검색 기능
3. 검색창 우측 select 를 통해 지역별 검색 가능
4. 게시글 등록 최신순, 오래된순 조회 가능

**게시글**

![Image](https://github.com/user-attachments/assets/aaedcd2f-07a8-4107-aa7b-63f3997e4560)
1. 본인인 경우 게시글 수정 및 삭제 가능
2. 댓글 기능 - 본인인 경우 수정 및 삭제 가능
3. 작성자에게 다이렉트 쪽지 보내기 가능

**쪽지 페이지**

![Image](https://github.com/user-attachments/assets/ea48afe7-46d9-423b-abfe-2343d6f0f62d)
1. 사이트에 가입한 회원들에게 쪽지 보내기 가능 (관리자 아이디는 표시 X)
2. 미확인 쪽지 알림 기능


**쪽지 확인**

![Image](https://github.com/user-attachments/assets/c901822a-cdcb-4cf1-8998-3625ed180284)
![Image](https://github.com/user-attachments/assets/48527f50-1d55-4341-b5f0-91f7264ca738)
보낸쪽지, 받은쪽지 확인 및 답장

**쪽지 보내기**

![Image](https://github.com/user-attachments/assets/777fc6b4-931c-4727-9bc1-947c750bd1f8)
보내는 이와 받는 이 아이디 확인 가능








