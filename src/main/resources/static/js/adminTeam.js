$(document).ready(function() {
    if(localStorage.getItem('token') === null) {
        alert("로그인이 필요한 서비스입니다!");
        window.location.href = '/member/login';
    }
    getTeams();
});

function getTeams(){
    $.ajax({
        url: '/api/teams/my',
        method: 'GET',
        headers : {
            Authorization: 'Bearer ' + localStorage.getItem("token"),
        },
        success: function(contents) {
            var tbody = $('#team-tbody');
            tbody.empty(); // 초기화

            // 데이터에 따라 테이블 행을 생성
            contents.forEach(function(team) {
                // 한 행의 내용
                var row = `
                        <tr>
                            <td>${team.id}</td>
                            <td>${team.name}</td>
                            <td>
                                <a href="#" onclick="getDetail(${team.id})">상세보기</a>
                            </td>
                        </tr>
                    `;
                tbody.append(row);

                currentPage = data.number;

                $('#prevPage').prop('disabled', data.first);
                $('#nextPage').prop('disabled', data.last);
            });
        },
        error: function(err) {
            alert("권한이 없습니다!");
            localStorage.clear();
            window.location.href = '/admin/login';
        }
    });

    function getDetail(id) {

    }
}