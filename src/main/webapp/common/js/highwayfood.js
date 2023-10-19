const restChange = (value) => {
    if(value != -1) {
        $.ajax({
            url: "http://data.ex.co.kr/openapi/restinfo/restBestfoodList?key=1835622522&type=json&numOfRows=100&stdRestCd=" + value,
            type: "get",
            datatype: "json",
            success: function (result) {
                console.log(result);
                let list = result.list;
                let foodlist = document.getElementById("foodlist");
                let txt = "";
                let cnt = 1;
                foodlist.innerHTML = txt;
                for (let item of list) {
                    txt += "<div class='form-check form-check-inline'>";
                    txt += "    <input name='food' class='form-check-input' type='checkbox' id='inlineCheckbox" + cnt + "' value='" + item.foodNm + "/" + item.stdRestCd + "/" + item.foodCost + "'>";
                    txt += "    <label class='form-check-label' for='inlineCheckbox" + cnt + "'>" + cnt + "/" + item.foodNm + "</label>";
                    txt += "</div>"
                    cnt++;
                }
                foodlist.innerHTML += txt;
            }
        })
    }
}