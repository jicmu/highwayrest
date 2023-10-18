<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <style>
        /*미리보기*/
        #preview_zone {
            width:700px;
            min-height: 150px;
            padding: 10px;
            border: 1px #000000;
        }
        /*첨부한 파일이 없으면*/
        #preview_zone:empty:before {
            content: attr(placeholder);
            color: #999;
            font-size: .9em;
        }

        /*빈 별*/
        .rating .star {
            width: 20px;
            height: 20px;
            margin-right: 10px;
            display: inline-block;
            background: url('${pageContext.request.contextPath}/review/star.png') no-repeat;
            background-size: 100%;
            box-sizing: border-box;
        }

        /*1점*/
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
        //TODO 이미지 수정 및 삭제
        $(document).ready(function(){

            $(function() {
                var max_file_number = 3,
                    $form = $('#f'), // 폼 아이디
                    $file_upload = $('#btnAtt', $form),// 사진 업로드 필드
                    $button = $('#submit', $form);// 등록 버튼

                $file_upload.on('change', function () {
                    var number_of_images = $(this)[0].files.length;//첨부한 파일 개수
                    if (number_of_images > max_file_number) {//첨부한 파일 개수가 초과하면
                        alert('사진은 최대' + max_file_number + '장까지만 첨부할 수 있습니다.');
                        $(this).val('');
                        $button.prop('disabled', 'disabled');//비활성화
                    } else {
                        $button.prop('disabled', false);//활성화
                    }
                });
            });

            (imageView = function imageView(preview_zone, btn) {//preview_zone: 사진 위치, btn: file태그의 id값
                    var previewZone = document.getElementById(preview_zone);
                    var btnAtt = document.getElementById(btn)
                    var review_files = [];//첨부된 파일들을 저장하는 배열명

                    var div_style = 'display:inline-block;position:relative;'
                        + 'width:150px;height:120px;margin:5px;border:1px solid #000;z-index:1';
                    var preview_style = 'width:100%;height:100%;z-index:none';
                    var check_style = 'width:30px;height:30px;position:absolute;font-size:24px;'
                        + 'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#000';

                    btnAtt.onchange = function (e) {//파일을 선택하는 이벤트가 발생하면
                        var files = e.target.files;//files에 파일 객체를 저장
                        //call(): 상위 context를 변경
                        //arguments: 함수의 매개변수에 접근할 수 있는 속성
                        //Array.prototype.slice.call(arguments)
                        //=>매개변수로 넘어온 값들을 array로 변환
                        var fileArr = Array.prototype.slice.call(files)
                        for (f of fileArr) {
                            imageLoader(f);//imageLoader(): 사진을 읽어 화면에 표시함
                        }
                    }

                    //박스 안으로 드래그
                    previewZone.addEventListener('dragenter', function (e) {//dragenter: 마우스가 대상 객체에 진입할 때
                        e.preventDefault();//preventDefault(): 고유 이벤트가 작동 못하게 함
                        e.stopPropagation();//stopPropagation(): 상위 요소로 이벤트를 전달하지 않고 자신만 이벤트 감지하도록 함
                    }, false)

                    //박스 안에 드래그
                    previewZone.addEventListener('dragover', function (e) {//dragover: 드래그하면서 마우스가 대상 객체에 자리 잡을 때
                        e.preventDefault();
                        e.stopPropagation();
                    }, false)

                    //박스 안에서 드래그를 드롭
                    previewZone.addEventListener('drop', function (e) {//drag: 드래그하던 객체를 놓는 장소에 위치한 객체에서 발생
                        var files = {};
                        e.preventDefault();
                        e.stopPropagation();

                        //dataTransfer: 파일을 드래그앤드롭 했을 때 파일에 대한 정보 가져오기
                        var dt = e.dataTransfer;
                        files = dt.files;
                        for (f of files) {
                            imageLoader(f);
                        }
                    }, false)

                    //첨부한 사진을 배열에 넣어 미리보기
                    imageLoader = function (file) {
                        review_files.push(file);//file 태그에 의해 전달된 파일들을 review_files에 추가
                        var reader = new FileReader();//FileReader(): 파일의 내용을 읽고 컴퓨터에 저장
                        reader.onload = function (ee) {//읽어온 값 갱신
                            let img = document.createElement('img')
                            img.setAttribute('style', preview_style)
                            img.src = ee.target.result;//img에 src를 넣어 나중에 img를 다운받거나 사용할 때를 대비해 경로 설정
                            previewZone.appendChild(makeDiv(img, file));
                        }
                        reader.readAsDataURL(file);//readAsDataURL(): 파일 객체를 읽어 base64 형태의 문자열로 변환
                    }

                    //첨부된 파일이 있는 경우 checkbox와 함께 previewZone에 추가할 div를 만들어 반환
                    makeDiv = function (img, file) {
                        var div = document.createElement('div')
                        div.setAttribute('style', div_style)

                        var btn = document.createElement('input')//input 태그 요소
                        btn.setAttribute('type', 'button')//input type="button"
                        btn.setAttribute('value', 'x')
                        btn.setAttribute('delFile', file.name);
                        btn.setAttribute('style', check_style);
                        btn.onclick = function (ev) {//button을 클릭하면
                            var ele = ev.srcElement;//srcElement: 이벤트가 발생한 객체를 반환/설정
                            var delFile = ele.getAttribute('delFile');
                            for (var i = 0; i < review_files.length; i++) {
                                if (delFile == review_files[i].name) {
                                    review_files.splice(i, 1);//splice(start, delCount): 배열 review_files의 start부터 delCount개의 요소를 삭제함
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

            //별점
            $('.rating > .star').click(function() {
                //$().parent(): 클릭한 객체의 부모
                //children('tag')
                //removeClass('class'): 해당 태그에서 클래스 삭제
                $(this).parent().children('span').removeClass('on');
                //$('tag').prevAll('tag');: 선택한 요소의 이전 모든 형제요소를 선택함
                $(this).addClass('on').prevAll('span').addClass('on');
                //클릭이벤트가 일어날 때 마다 name이 star인 value값이 변경된다. 기본적으로는 1점 세팅.
                var value = $(this).attr("value");//선택한 객체의 value
                $("input[name=star]").val(value);
                // $("").val(value);
                // $(this).attr('star');
            });
            });
    </script>
</head>
<body>
    <div class="container">
        <h3 class="mt-3">후기 수정</h3>
        <form action="${pageContext.request.contextPath}/editReview" method="post" enctype="multipart/form-data">
            <table border="1">
                <input type="hidden" value="${r.reviewNo}" name="reviewNo">
                <input type="hidden" value="${r.restNo}" name="restNo">
                <input type="hidden" value="${param.svarCd}" name="svarCd">
                <input type="hidden" name="star" value="1">

                    <div class="text-start">별점</div>
                    <div class="rating text-start">
                        <span class="star on" value="1"> </span>
                        <span class="star"  value="2"> </span>
                        <span class="star" value="3"> </span>
                        <span class="star" value="4"> </span>
                        <span class="star" value="5"> </span>
                    </div>

                <div class="mb-3 text-start">
                    <label for="content" class="form-label text-start">후기</label>
                    <textarea class="form-control" id="content" rows="10" cols="50" name="content">${r.content}</textarea>
                </div>
<%--                <tr>--%>
<%--                    <th>사진</th>--%>
<%--                    <td> <input type="file" name="file" id="file" accept="image/*" multiple></td>--%>
<%--                </tr>--%>
                <div id="preview_zone" class="text-start" placeholder="첨부한 사진을 미리 볼 수 있습니다."></div>
                <div class="input-group mb-3" id="image_preview">
                    <input type="file" class="form-control" id="btnAtt" name="file" accept="image/*" multiple>
                    <label class="input-group-text" for="btnAtt">첨부</label>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button type="submit" class="btn btn-outline-dark" id="submit">수정</button>
                </div>

            </table>
        </form>
    </div>
</body>
</html>
