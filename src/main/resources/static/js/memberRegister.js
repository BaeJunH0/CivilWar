$(document).ready(function() {
    localStorage.clear();

    // 입력 필드에서 Enter 키를 눌렀을 때 로그인 버튼 클릭
    $("#userId, #password, #nickname").keydown(function(event) {
        if (event.key === "Enter") { // Enter 키가 눌렸을 때
            $("#register").click(); // 로그인 버튼 클릭
        }
    });

    // 회원가입 버튼 클릭 시 동작
    $("#register").click(function(){
        var user = {
            nickname : $('#nickname').val(),
            email: $("#userId").val(),
            password : $("#password").val()
        };

        $.ajax({
            type : "POST",
            url : "/api/auth/register",
            contentType: "application/json",
            datatype : "json",
            data : JSON.stringify(user),
            success : function(res) {
                var value = res['accessToken'];
                successRegister(value);
            },
            error : function(err){
                alert("회원가입 실패! 아이디나 이메일 중복을 확인하세요");
            }
        });
    });
});

function successRegister(value) {
    localStorage.clear();
    localStorage.setItem('token', value);
    window.location.href = "/member/main";
}