const selectRest = (value) => {
    if(value != -1) {
        $.ajax({
            url: "http://localhost/highwayrest/food/menu",
            type: "get",
            datatype: "json",
            data: {"svarCd" : value},
            success: function (result) {
                console.log(result);

                let list = result;
                let txt = "";
                let menuList = document.getElementById("menuList");
                menuList.innerHTML = "";
                console.log("length: " + list.length);
                if(list.length != 0) {
                    for (let item of list) {
                        txt += "<div class=\"col-4 \">";
                        txt += "    <div class=\"card\" style=\"\">";
                        txt += "        <div class=\"card-body\">";
                        txt += "            <h5 class=\"card-title\">" + item.name + "</h5>";
                        txt += "            <p class=\"card-text\">" + item.foodCost + "</p>";
                        txt += "            <a href=\"#\" class=\"btn btn-primary\">삭제</a>";
                        txt += "        </div>";
                        txt += "    </div>";
                        txt += "</div>"
                    }
                }
                menuList.innerHTML = txt;
            }
        })
    }
}