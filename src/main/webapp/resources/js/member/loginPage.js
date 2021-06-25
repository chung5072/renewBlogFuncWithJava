window.onload = function() {
    //* 변수 선언
    // 로그인 및 회원가입 페이지
    var changeJoinPage = document.getElementById("changeJoinPage");
    var changeLoginPage = document.getElementById("changeLoginPage");
    // 비밀번호 관련
    var memberPW = document.getElementsByName("joinMemberPW")[0].value;
    var pwCheck = document.getElementById("pwCheck");
    var pwCheckButton = document.getElementById("pwCheckButton");

    //* 초기 상태
    // 로그인 및 회원 가입 페이지
    document.getElementById("loginPage").style.display = "block";
    document.getElementById("joinPage").style.display = "none";
    // 회원 가입 버튼
    // 회원가입 버튼 숨김
    document.getElementById("joinMemberButton").style.display = "none";

    //* 함수 실행
    // 로그인 및 회원 가입 페이지
    // 회원 가입 버튼 누르면
    // 로그인 페이지 숨기고, 회원 가입 페이지 보임
    changeJoinPage.addEventListener("click", function() {
        document.getElementById("loginPage").style.display = "none";
        document.getElementById("joinPage").style.display = "block";
    });
    // 로그인 버튼 누르면
    // 로그인 페이지 보이고, 회원 가입 페이지 숨김
    changeLoginPage.addEventListener("click", function() {
        document.getElementById("loginPage").style.display = "block";
        document.getElementById("joinPage").style.display = "none";
    });

    // 회원 가입 버튼
    pwCheckButton.addEventListener("click", function() {
        if (memberPW != null) {
            if (memberPW == pwCheck) {
                alert("비밀번호가 일치합니다.");
                document.getElementById("joinMemberButton").style.display = "block";
            }
            else {
                alert("비밀번호가 일치하지 않습니다.");
                document.getElementById("joinMemberButton").style.display = "none";
            }
        }
        else {
            alert("비밀번호를 입력해주세요");
            document.getElementById("joinMemberButton").style.display = "none";
        }
    })
}

