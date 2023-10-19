// const storeorder = (value) => {
//     if(value != -1) {
//         $.ajax({
//             url: "http://localhost:8080/highwayrest/store/orders",
//             data: {"loginNum" : value},
//             type: "get",
//             datatype: "json",
//             success: function (result) {
//                 let cl = document.getElementById("cl");
//                 cl.innerHTML = "<jsp:include page='${view}' />"
//             }, error: function () {
//                 console.log("스토어 실패!");
//             }
//         })
//     }
// }