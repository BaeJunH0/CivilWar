var currentPage = 0;
var pageSize = 10;

$(document).ready(function() {
    if(localStorage.getItem('token') === null) {
        window.location.href = '/adminLogin';
    }
    getTeams(currentPage);

    $('#prevPage').click(function() {
        if (currentPage > 0) {
            getTeams(currentPage - 1);
        }
    });

    $('#nextPage').click(function() {
        getTeams(currentPage + 1);
    });
});

function getTeams(page){
    $.ajax({
        url: '/api/admin/teams' + '?page=' + page + '&size=' + pageSize,
        method: 'GET',
        headers : {
            Authorization: 'Bearer ' + localStorage.getItem("token"),
        },
        success: function(data) {
            var contents = data.content;
            var tbody = $('#team-tbody');
            tbody.empty(); // 초기화

            // 데이터에 따라 테이블 행을 생성
            contents.forEach(function(team) {
                // 한 행의 내용
                var row = `
                        <tr>
                            <td>${team.id}</td>
                            <td>${team.name}</td>
                            <td>${team.owner}</td>
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
            window.location.href = '/adminLogin';
        }
    });
}