<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>리뷰 작성</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <style>

        #preview_zone {
            width:700px;
            min-height: 150px;
            padding: 10px;
            border: 1px #000000;
        }

        #preview_zone:empty:before {
            content: attr(placeholder);
            color: #999;
            font-size: .9em;
        }

        .rating {
            width: 100%;
            box-sizing: border-box;
            display: inline-flex;
            float: left;
            flex-direction: row;
            justify-content: flex-start;
        }


        .rating .star {
            width: 20px;
            height: 20px;
            margin-right: 10px;
            display: inline-block;
            background: url('${pageContext.request.contextPath}/review/star.png') no-repeat;
            background-size: 100%;
            box-sizing: border-box;
        }

        .rating .star.on {
            width: 20px;
            height: 20px;
            margin-right: 10px;
            display: inline-block;
            background: url('${pageContext.request.contextPath}/review/filledStar.png') no-repeat;
            background-size: 100%;
            box-sizing: border-box;
        }

    </style>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function () {

        $(function() {
            var max_file_number = 3,
                $form = $('#f'),
                $file_upload = $('#btnAtt', $form),
                $button = $('#submit', $form);

            $file_upload.on('change', function () {
                var number_of_images = $(this)[0].files.length;
                if (number_of_images > max_file_number) {
                    alert('사진은 최대' + max_file_number + '장까지만 첨부할 수 있습니다.');
                    $(this).val('');
                    $button.prop('disabled', 'disabled');
                } else {
                    $button.prop('disabled', false);
                }
            });
        });

            (imageView = function imageView(preview_zone, btn) {
                    var previewZone = document.getElementById(preview_zone);
                    var btnAtt = document.getElementById(btn)
                    var review_files = [];

                    var div_style = 'display:inline-block;position:relative;'
                        + 'width:150px;height:120px;margin:5px;border:1px solid #000;z-index:1';
                    var preview_style = 'width:100%;height:100%;z-index:none';
                    var check_style = 'width:30px;height:30px;position:absolute;font-size:24px;'
                        + 'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#000';

                    btnAtt.onchange = function (e) {
                        var files = e.target.files;
                        var fileArr = Array.prototype.slice.call(files)
                        for (f of fileArr) {
                            imageLoader(f);
                        }
                    }

                    previewZone.addEventListener('dragenter', function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                    }, false)

                    previewZone.addEventListener('dragover', function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                    }, false)

                    previewZone.addEventListener('drop', function (e) {
                        var files = {};
                        e.preventDefault();
                        e.stopPropagation();

                        var dt = e.dataTransfer;
                        files = dt.files;
                        for (f of files) {
                            imageLoader(f);
                        }
                    }, false)

                    imageLoader = function (file) {
                        review_files.push(file);
                        var reader = new FileReader();
                        reader.onload = function (ee) {
                            let img = document.createElement('img')
                            img.setAttribute('style', preview_style)
                            img.src = ee.target.result;
                            previewZone.appendChild(makeDiv(img, file));
                        }
                        reader.readAsDataURL(file);
                    }

                    makeDiv = function (img, file) {
                        var div = document.createElement('div')
                        div.setAttribute('style', div_style)

                        var btn = document.createElement('input')
                        btn.setAttribute('type', 'button')
                        btn.setAttribute('value', 'x')
                        btn.setAttribute('delFile', file.name);
                        btn.setAttribute('style', check_style);
                        btn.onclick = function (ev) {
                            var ele = ev.srcElement;
                            var delFile = ele.getAttribute('delFile');
                            for (var i = 0; i < review_files.length; i++) {
                                if (delFile == review_files[i].name) {
                                    review_files.splice(i, 1);
                                }
                            }

                            dt = new DataTransfer();
                            for (f in review_files) {
                                var file = review_files[f];
                                dt.items.add(file);
                            }
                            btnAtt.files = dt.files;
                            var p = ele.parentNode;
                            previewZone.removeChild(p)
                        }
                        div.appendChild(img)
                        div.appendChild(btn)
                        return div
                    }
                }
            )('preview_zone', 'btnAtt')

            $('.rating > .star').click(function() {
                $(this).parent().children('span').removeClass('on');
                $(this).addClass('on').prevAll('span').addClass('on');
                var value = $(this).attr("value");
                $("input[name=star]").val(value);
            });
        });
    </script>
</head>
<body>
<h3 class="mt-3">리뷰 작성</h3>
    <div class="container">
        <form action="${pageContext.request.contextPath}/addReview" id="f" name="f" style="border:none" method="post" enctype="multipart/form-data">

                <input type="hidden" name="memberNo" value="${sessionScope.loginNum}">
                <input type="hidden" name="orderNo" value="${orderNo}">
                <input type="hidden" name="restNo" value="${restNo}">
                <input type="hidden" name="star" value="1">

                <div class="text-start">별점</div>
                <div class="rating">
                    <span class="star on" value="1"> </span>
                    <span class="star"  value="2"> </span>
                    <span class="star" value="3"> </span>
                    <span class="star" value="4"> </span>
                    <span class="star" value="5"> </span>
                </div>

                <div class="text-start">
                    <label for="exampleFormControlTextarea1" class="form-label text-start">후기</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" cols="50" name="content" placeholder="후기를 작성해 주세요."></textarea>
                </div>

                <div id="preview_zone" class="text-start" placeholder="첨부한 사진을 미리 볼 수 있습니다."></div>
                <div class="input-group mb-3" id="image_preview">
                    <input type="file" class="form-control" id="btnAtt" name="file" accept="image/*" multiple>
                    <label class="input-group-text" for="btnAtt">첨부</label>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button type="submit" class="btn btn-outline-dark" id="submit">작성</button>
                </div>
        </form>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"/>
</body>
</html>
