document.addEventListener("DOMContentLoaded", function () {
    let selectedBox = null;
    let currentTeamId = null;
    let currentTeamName = null;



    // 팀 정보를 업데이트하는 함수
    function updateCurrentTeamInfo() {
        const teamNameElement = document.getElementById('teamName');

        if(currentTeamName == null) {
            teamNameElement.textContent = `현재 팀 : 지정되지 않음`;
        }
        else {
            teamNameElement.textContent = `현재 팀 : ` + currentTeamName;
        }
    }

    // 플레이어 검색 동작
    document.querySelectorAll(".box").forEach(box => {
        box.addEventListener("click", function () {
            selectedBox = this; // 클릭한 박스를 저장
            openModal();
        });
    });

    document.querySelector(".close-btn").addEventListener("click", closeModal);

    document.querySelector(".submit-btn").addEventListener("click", function () {
        const inputValue1 = document.getElementById("inputField1").value;
        const inputValue2 = document.getElementById("inputField2").value;

        if (inputValue1 && inputValue2) {
            search(inputValue1, inputValue2);
        } else {
            alert("두 입력값을 모두 입력해주세요.");
        }
    });

    document.querySelector(".init-btn").addEventListener("click", function () {
        const id = selectedBox.getAttribute('data-player-id');
        selectedBox.innerHTML = `플레이어 ${id}`;
        closeModal();
    })

    function openModal() {
        document.getElementById("modal").style.display = "flex";
    }

    function closeModal() {
        document.getElementById("modal").style.display = "none";
    }

    function search(nickname, tag) {
        var player = {
            nickname: nickname,
            tag: tag
        };

        $("#loading-overlay").show();

        $.ajax({
            type: "POST",
            url: "/api/players",
            contentType: "application/json",
            datatype: "json",
            data: JSON.stringify(player),
            success: function (res) {
                if (selectedBox) {
                    var freeRank = res.freeRank;
                    var soloRank = res.soloRank;

                    selectedBox.innerHTML = res.nickname + '#' + res.tag + '<br>' +
                        'SR : '  + soloRank + '<br>' +
                        'FR : '  + freeRank;
                }
                closeModal();
            },
            error: function () {
                alert("검색 실패!");
            },
        }).always(function () {
            // 요청이 끝나면 로딩 숨김 (성공/실패와 관계없이 실행됨)
            $("#loading-overlay").hide();
        });
    }

    // 팀 저장 동작

    document.getElementById("saveBtn").addEventListener("click", openTeamModal);

    document.querySelector(".close-team-btn").addEventListener("click", closeTeamModal);

    document.querySelector(".submit-team-btn").addEventListener("click", function () {
        const teamNameValue = document.getElementById("teamNameField").value;

        if (teamNameValue) {
            saveTeams(teamNameValue);
        } else {
            alert("입력값을 입력해주세요.");
        }
    });

    function openTeamModal() {
        document.getElementById("teamModal").style.display = "flex";
    }

    function closeTeamModal() {
        document.getElementById("teamModal").style.display = "none";
    }

    function saveTeams(teamName) {
        const playerRequests = [];

        document.querySelectorAll(".box").forEach(box => {
            // "플레이어 n"이 아닌 경우에만 처리
            if (!box.innerHTML.startsWith("플레이어")) {
                const playerData = box.innerHTML.split('<br>'); // 줄바꿈을 기준으로 나누기
                const nicknameTag = playerData[0].split('#'); // 닉네임과 태그 분리
                const soloRankData = playerData[1].split(':')[1].trim(); // Solo Rank 추출
                const freeRankData = playerData[2].split(':')[1].trim(); // Free Rank 추출

                // player 객체로 만들기
                const playerRequest = {
                    nickname: nicknameTag[0],
                    tag: nicknameTag[1],
                    soloRank: soloRankData,
                    freeRank: freeRankData
                };
                playerRequests.push(playerRequest); // players 배열에 추가
            }
            else {
                // player 객체로 만들기
                const playerRequest = {
                    nickname: " ",
                    tag: " ",
                    soloRank: " ",
                    freeRank: " "
                };
                playerRequests.push(playerRequest); // players 배열에 추가
            }
        });

        const teamRequest = {
            playerRequests : playerRequests,
            name : teamName
        }

        $.ajax({
            type: "POST",
            url: "/api/teams",
            headers : {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            },
            contentType: "application/json",
            data: JSON.stringify(teamRequest),
            dataType : 'json',
            success: function(response) {
                alert("팀이 저장되었습니다!");

                currentTeamId = response;
                currentTeamName = teamName;
                updateCurrentTeamInfo();

                closeTeamModal();
            },
            error: function(res) {
                alert("이미 존재하는 팀 이름입니다!");
            }
        });
    }


    // 상단 바 로직
    const authLink = document.getElementById("authLink");

    if (localStorage.getItem("token") !== null) {
        authLink.textContent = "Logout";
        authLink.href = "/member/main";
        authLink.addEventListener("click", function () {
            localStorage.removeItem("token"); // 로그아웃 처리
        });
    } else {
        authLink.textContent = "Login";
        authLink.href = "/member/login";
    }

    // 팀 조회 동작
    document.getElementById("viewTeamsBtn").addEventListener("click", getMyTeamList);
    document.getElementById("closeTeamListModal").addEventListener("click", closeTeamListModal);

    function openTeamListModal() {
        document.getElementById("teamListModal").style.display = "flex";
    }

    function closeTeamListModal() {
        document.getElementById("teamListModal").style.display = "none";
    }

    function getMyTeamList() {
        $.ajax({
            type: "GET",
            url: "/api/teams/my", // 저장할 API 엔드포인트
            headers : {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            },
            datatype: "json",
            success: function(response) {
                openTeamListModal();
                renderTeamList(response);
            },
            error: function(res) {
                alert("저장 실패!");
            }
        });
    }

    function renderTeamList(teams) {
        const teamListContainer = document.getElementById("teamListContainer");
        teamListContainer.innerHTML = "";

        teams.forEach(team => {
            const teamItem = document.createElement("div");
            teamItem.classList.add("team-item");
            teamItem.textContent = `${team.name} (Owner: ${team.owner}) `;
            teamItem.dataset.teamId = team.id;

            // 조회 버튼 만들기
            const viewButton = document.createElement("button");
            viewButton.textContent = "View";
            viewButton.addEventListener("click", function (event) {
                event.stopPropagation();  // 클릭 이벤트 버블링을 막아 teamItem의 클릭 이벤트가 실행되지 않도록
                loadTeam(team.id);  // 조회
                currentTeamId = team.id;
            });

            // 삭제 버튼 만들기
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.addEventListener("click", function (event) {
                event.stopPropagation();  // 클릭 이벤트 버블링을 막아 teamItem의 클릭 이벤트가 실행되지 않도록
                deleteTeam(team.id);  // 삭제
            });

            // 팀 아이템에 버튼 추가
            teamItem.appendChild(viewButton);
            teamItem.appendChild(deleteButton);

            // 팀 아이템을 리스트에 추가
            teamListContainer.appendChild(teamItem);
        });
    }

    function loadTeam(id) {
        $.ajax({
            type: "GET",
            url: `/api/teams/${id}`,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            },
            success: function (teamData) {
                alert("팀 정보를 불러왔습니다.");
                updateMainScreen(teamData);

                currentTeamName = teamData.name;
                currentTeamId = id;
                updateCurrentTeamInfo();

                closeTeamListModal();
            },
            error: function () {
                alert("팀 정보를 불러오는데 실패했습니다.");
            }
        });
    }

    function updateMainScreen(teamData) {
        const players = teamData.playerResponses;
        const boxes = document.querySelectorAll(".box");

        boxes.forEach((box, index) => {
            if (players[index] && !players[index].nickname.startsWith("defaultPlayer")) {
                box.innerHTML = `${players[index].nickname}#${players[index].tag}<br>
                                 SR: ${players[index].soloRank}<br>
                                 FR: ${players[index].freeRank}`;
            } else {
                box.innerHTML = `플레이어 ${index + 1}`;
            }
        });
    }

    function deleteTeam(id) {
        $.ajax({
            type: "DELETE",
            url: `/api/teams/${id}`,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            },
            success: function () {
                if (id === currentTeamId) {
                    currentTeamId = null;
                    currentTeamName = null;
                    updateCurrentTeamInfo();
                }
                getMyTeamList();
            },
            error: function () {
                alert("팀을 삭제하지 못했습니다.");
            }
        });
    }

    // 팀 랜덤 셔플 동작
    document.getElementById("shuffleBtn").addEventListener("click", shuffleTeams);

    function shuffleTeams() {
        const teams = [];

        // "플레이어 n"이 아닌 박스들만 배열에 추가
        document.querySelectorAll(".box").forEach(box => {
            if (!box.innerHTML.startsWith("플레이어")) { // "플레이어 n"이 아닌 경우만 추가
                teams.push(box.innerHTML);
            }
        });

        // 팀 셔플 로직
        const shuffledTeams = teams.sort(() => Math.random() - 0.5);
        let index = 0;

        // 셔플된 텍스트를 각 박스에 다시 넣기
        document.querySelectorAll(".box").forEach(box => {
            if (!box.innerHTML.startsWith("플레이어")) { // "플레이어 n"이 아닌 경우에만 셔플된 값 적용
                box.innerHTML = shuffledTeams[index++];
            }
        });
    }

    // 티어 셔플 동작
    document.getElementById("tierShuffleBtn").addEventListener("click", tierShuffleTeams);

    function tierShuffleTeams() {
        if(currentTeamId === null) {
            alert("저장 또는 조회 이후에 사용할 수 있습니다!");
            return
        }

        $.ajax({
            type: "POST",
            url: `/api/teams/${currentTeamId}`,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            },
            success: function (res) {
                updateMainScreen(res);
            },
            error: function () {
                alert("셔플에 실패했습니다!");
            }
        });
    }
});

function isTokenExpired(token) {
    if (!token) return true;

    try {
        const payload = JSON.parse(atob(token.split(".")[1]));
        const now = Math.floor(Date.now() / 1000); // 현재 시간(초 단위)

        return payload.exp < now; // 만료 시간(exp)이 현재 시간보다 과거면 true
    } catch (e) {
        return true; // 파싱 실패 시 만료된 것으로 처리
    }
}

function checkTokenOnPageLoad() {
    const token = localStorage.getItem("token"); // 또는 sessionStorage

    if (isTokenExpired(token)) {
        alert("로그인이 만료되었습니다. 다시 로그인해 주세요.");
        localStorage.removeItem("token"); // 토큰 삭제
        window.location.href = "/member/login"; // 로그인 페이지로 리디렉트
    }
}

// 페이지 로드 시 JWT 확인
document.addEventListener("DOMContentLoaded", checkTokenOnPageLoad);
