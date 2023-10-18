const restOrder = (value) => {
    console.log("value: " + value);
    if(value != -1) {
        $.ajax({
            url: "http://localhost:8080/highwayrest/store/orders",
            type: "post",
            datatype: "json",
            data: {"loginStore" : value},
            success: function (result) {
                console.log("통과!")
                console.log(result);
            }, error: function() {
                console.log("실패!")
            }
        })
    }
}