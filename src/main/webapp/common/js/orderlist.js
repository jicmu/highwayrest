const restOrder = (value) => {
    // console.log("value: " + value);
    // if(value != -1) {
    //     $.ajax({
    //         url: "http://localhost:8080/highwayrest/store/orders",
    //         type: "get",
    //         datatype: "text",
    //         data: {"loginNum" : value},
    //         success: function (result) {
    //         }, error: function() {
    //             console.log("실패!");
    //         }
    //     })
    // }

    window.location.href="http://localhost/highwayrest/store/orders?loginNum=" + value;
}