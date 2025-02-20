$(document).ready(function() {
    localStorage.clear();

    // 입력 필드에서 Enter 키를 눌렀을 때 로그인 버튼 클릭
    $("#userId, #password").keydown(function(event) {
        if (event.key === "Enter") { // Enter 키가 눌렸을 때
            $("#login").click(); // 로그인 버튼 클릭
        }
    });

    // 로그인 버튼 클릭 시 동작
    $("#login").click(function(){
        var user = {
            email: $("#userId").val(),
            password : $("#password").val()
        };

        $.ajax({
            type : "POST",
            url : "/api/auth/login",
            contentType: "application/json",
            datatype : "json",
            data : JSON.stringify(user),
            success : function(res) {
                var value = res['accessToken'];
                successLogin(value);
            },
            error : function(err){
                alert("로그인 실패! 아이디나 비밀번호를 확인하세요");
            }
        });
    });

    $("#register").click(function(){
        window.location.href = "/member/register";
    });
});

function successLogin(value) {
    localStorage.clear();
    localStorage.setItem('token', value);
    window.location.href = "/member/main";
}