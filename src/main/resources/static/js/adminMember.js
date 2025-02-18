var currentPage = 0;
var pageSize = 10;

$(document).ready(function() {
    if(localStorage.getItem('token') === null) {
        window.location.href = '/adminLogin';
    }
    getMembers(currentPage);

    $('#prevPage').click(function() {
        if (currentPage > 0) {
            getMembers(currentPage - 1);
        }
    });

    $('#nextPage').click(function() {
        getMembers(currentPage + 1);
    });
});

function getMembers(page){
    $.ajax({
        url: '/api/admin/users' + '?page=' + page + '&size=' + pageSize,
        method: 'GET',
        headers : {
            Authorization: 'Bearer ' + localStorage.getItem("token"),
        },
        success: function(data) {
            var contents = data.content;
            var tbody = $('#member-tbody');
            tbody.empty(); // 초기화

            // 데이터에 따라 테이블 행을 생성
            contents.forEach(function(member) {
                // 한 행의 내용
                var row = `
                        <tr>
                            <td>${member.id}</td>
                            <td>${member.email}</td>
                            <td>${member.nickname}</td>
                            <td>${member.isAdmin}</td>
                            <td>
                                <a href="#" onclick="deleteMemberFunction(${member.id})">삭제</a>
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
            window.location.href = '/adminLogin';
        }
    });
}

function deleteMemberFunction(id){
    $.ajax({
        type: "DELETE",
        url: "/api/admin/users/" + id,
        headers : {
            Authorization: 'Bearer ' + localStorage.getItem("token"),
        },
        success : function(res){
            $('#member-table').find('tr[data-id="' + id + '"]').remove();
            alert("유저 ID : " + id + "가 삭제되었습니다.");
            getMembers(currentPage);
        },
        error : function(error){
            alert("권한이 없습니다");
            window.location.href = '/adminLogin';
        }
    })
}