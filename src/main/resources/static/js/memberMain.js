document.addEventListener("DOMContentLoaded", function () {
    let selectedBox = null;

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

        $.ajax({
            type: "POST",
            url: "/api/players",
            contentType: "application/json",
            datatype: "json",
            data: JSON.stringify(player),
            success: function (res) {
                if (selectedBox) {
                    var freeTier = '';
                    var freeRank = '';
                    var soloTier = '';
                    var soloRank = '';

                    for (const object of res) {
                        if(object.queueType === "RANKED_SOLO_5x5") {
                            soloTier = object.tier;
                            soloRank = object.rank;
                        }
                        if(object.queueType === "RANKED_FLEX_SR") {
                            freeTier = object.tier;
                            freeRank = object.rank;
                        }
                    }

                    selectedBox.innerHTML = nickname + '#' + tag + '<br>' +
                        'SR : ' + soloTier + ' ' + soloRank + '<br>' +
                        'FR : ' + freeTier + ' ' + freeRank;
                }
                closeModal();
            },
            error: function () {
                alert("검색 실패!");
            }
        });
    }

    document.getElementById("shuffleBtn").addEventListener("click", shuffleTeams);

    document.getElementById("saveBtn").addEventListener("click", saveTeams);

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

    function saveTeams() {
        const teams = [];

        // "플레이어 n"을 제외하고 팀 저장
        document.querySelectorAll(".box").forEach(box => {
            if (!box.innerHTML.startsWith("플레이어")) {
                teams.push(box.innerHTML);
            }
        });

        // 저장할 팀 데이터 처리 (예: API 요청)
        $.ajax({
            type: "POST",
            url: "/api/save-teams", // 저장할 API 엔드포인트
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({ teams: teams }),
            success: function(response) {
                alert("팀이 저장되었습니다!");
            },
            error: function() {
                alert("저장 실패!");
            }
        });
    }

});

document.addEventListener("DOMContentLoaded", function () {
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
});