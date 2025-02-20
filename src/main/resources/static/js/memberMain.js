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


                    selectedBox.textContent = nickname + '#' + tag + '\n'
                        + 'SR : ' + soloTier + ' ' + soloRank + '\n'
                        + 'FR : ' + freeTier + ' ' + freeRank;
                }
                closeModal();
            },
            error: function () {
                alert("검색 실패!");
            }
        });
    }
});
