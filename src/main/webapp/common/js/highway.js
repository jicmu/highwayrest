let routeCd = "";
$(document).ready(function() {
    $.ajax({
        url: "http://data.ex.co.kr/openapi/roadEtcInfo/spinRouteList?key=1835622522&type=json&useYn=Y",
        type: "get",
        datatype: "json",
        success: function(res) {
            console.log("전국 고속도로 개수: " + res.count);
            let road = document.getElementById("road");
            let txt = "";
            for(let list of res.list) {
                let info = list.routeNo + "/" + list.routeCd + "/" + list.routeNm;
                txt += "<option value=" + info + ">" + list.routeNm + "</option>"
            }
            road.innerHTML += txt;

            road.options[0].disabled = "disabled";
        }
    })
})

// 고속도로 선택시
const roadMove = (value) => {
    let upDown = document.getElementById("upDown");
    let rest = document.getElementById("rest");

    rest.disabled = true;
    rest.innerHTML = "<option value='-1'>휴게소 선택</option>";

    upDown.disabled = false;
    upDown.options[0].selected = true;
    upDown.options[0].disabled = "disabled";

    routeCd = value.split("/")[1];

    console.log("routeCd: " + routeCd);
}

// 상,하행 선택시
const going = (value) => {
    console.log("선택된 값: " + value);

    $.ajax({
        url: "http://data.ex.co.kr/openapi/restinfo/hiwaySvarInfoList?key=1835622522&type=json&svarGsstClssCd=0&routeCd=" + routeCd +"&gudClssCd=" + value,
        type: "get",
        datatype: "json",
        success: function(result) {
            console.log("성공!");
            console.log(result);
            let list = result.list;
            let rest = document.getElementById("rest");
            rest.innerHTML = "<option value='-1'>휴게소 선택</option>";
            let txt = "";
            for(let item of list) {
                let restloc = item.svarNm;
                if(restloc.indexOf("쉼터") == -1){
                    console.log(item);

                    let addr = item.svarAddr;

                    let value = item.svarCd + "/" + item.svarNm + "/" +item.gudClssCd + "/" + item.rprsTelNo + "/" + item.routeCd + "/" + addr.replaceAll(" ", ",");
                    console.log("value: " + value);
                    txt += "<option value=" + value +">" + item.svarNm + "</option>";
                }
            }
            rest.innerHTML += txt;
            rest.disabled = false;
        }, error: function() {
            console.log("휴게소 정보 불러오기 실패!");
        }
    })
}