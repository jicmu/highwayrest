<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 - 휴게소 음식 주문</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

    <style>
        .bg-hover-kakao:hover {
            background-color: #FFDC00;
            color: #8A7813;
        }

        .border-kakao {
            border-color: #FFDC00;
        }
    </style>
</head>
<body>
    <main class="container-sm">
        <h1 class="p-1 pb-2 border-bottom border-muted">주문</h1>
        <section id="orders" class="row">
            <form action="" method="get" name="form-payment">
                <article class="card p-0 mb-3">
                    <div class="card-header">
                        상품명<input type="hidden" name="items" value="상품명1">
                        <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                            </svg>
                        </div>
                    </div>
                    <div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="quantity-1">수량</label>
                            <select name="quantity" id="quantity-1" class="form-select form-select-sm">
                                <option value="0">0</option>
                                <option value="1" selected>1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="unit-1">단가</label>
                            <input class="form-control form-control-sm" type="number" name="unit" id="unit-1" value="1000" readonly>
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="price-1">가격</label>
                            <input class="form-control form-control-sm" type="number" name="price" id="price-1" readonly>
                        </div>
                    </div>
                </article>

                <article class="card p-0 mb-3">
                    <div class="card-header">
                        상품명<input type="hidden" name="items" value="상품명2">
                        <div id="cancel-2" class="float-end text-muted" style="cursor: pointer;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                            </svg>
                        </div>
                    </div>
                    <div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="quantity-2">수량</label>
                            <select name="quantity" id="quantity-2" class="form-select form-select-sm">
                                <option value="0">0</option>
                                <option value="1" selected>1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="unit-2">단가</label>
                            <input class="form-control form-control-sm" type="number" name="unit" id="unit-2" value="2500" readonly>
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="price-2">가격</label>
                            <input class="form-control form-control-sm" type="number" name="price" id="price-2" readonly>
                        </div>
                    </div>
                </article>
            
                <div class="clearfix">
                    <span class="d-inline-block">
                        <label for="total">총액</label>
                        <input class="form-control form-control-sm" type="number" name="total" id="total" readonly>
                    </span>
                    <div class="btn-group float-end">
                        <button type="button" class="btn btn-outline-danger">취소</button>
                        <button type="button" class="btn btn-outline-primary bg-hover-kakao" id="pay-by-kakao" style="border-color: #FFDC00; color: #8A7813">결제</button>
                    </div>
                </div>
            </form>
        </section>
    </main>
    <script>
        let quantityElem = document.querySelectorAll("select[name=quantity]");

        let setTotalPrice = () => {
            let price = document.querySelectorAll("input[name=price]");

            let sum = 0;
            for (let p of price) {
                sum += parseInt(p.value);
            }

            return sum;
        };

        quantityElem.forEach((elem) => {
            let calculatePrice = (element) => {
                let elemNum = elem.id.replace("quantity-", "");

                let unit = document.querySelector("#unit-" + elemNum);
                let price = document.querySelector("#price-" + elemNum);

                price.value = unit.value * elem.value;
            }

            calculatePrice();

            elem.addEventListener("change", () => {
                calculatePrice();

                document.querySelector("#total").value = parseInt(setTotalPrice());
            });
        });

        window.onload = () => {
            document.querySelector("#total").value = parseInt( setTotalPrice() );

            // kakao
            let payByKakao = document.querySelector("#pay-by-kakao");

            payByKakao.addEventListener("click", () => {
                let form = document.forms["form-payment"];
                form.action = "${pageContext.request.contextPath}" + "/payment/kakao";

                form.submit();
            });
        }
    </script>
</body>
</html>