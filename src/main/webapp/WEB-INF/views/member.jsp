<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<h1 class="text-center member-title">회원정보 수정</h1>
<form name="signupForm" action="./member/useredit?${_csrf.parameterName}=${_csrf.token}" 
      method="post" enctype="multipart/form-data">
<ul class="list-group list-group-flush">
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        <span class="text-danger">*</span>
        아이디
     </div> 
     <div class="col-md-4">
        <input type="text" readonly value="${cuser.userid }" name="userid" id="userid" class="form-control" placeholder="아이디" />
     </div>
  </li>
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        <span class="text-danger">*</span>
        비밀번호
     </div> 
     <div class="col-md-4">
        <input type="password" name="userpass" id="userpass" class="form-control" placeholder="비밀번호" />
     </div>
     <div class="col-md-4">
        <input type="password" name="reuserpass" id="reuserpass" class="form-control" placeholder="비밀번호확인" />
     </div>
  </li>
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        <span class="text-danger">*</span>
        이름
     </div> 
     <div class="col-md-4">
        <input type="text" name="username" value="${cuser.username }" id="username" class="form-control" placeholder="이름" />
     </div>
  </li>
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        <span class="text-danger">*</span>
        이메일
     </div> 
     <div class="col-md-6">
        <input type="text" name="useremail" value="${cuser.useremail }" id="useremail" class="form-control" placeholder="이메일" />
     </div>
  </li>
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        <span class="text-danger">*</span>
        전화번호
     </div> 
     <div class="col-md-6">
        <input type="text" name="usertel" id="usertel" value="${cuser.usertel }" class="form-control" placeholder="전화번호" />
     </div>
  </li>
  <li class="list-group-item d-flex flex-wrap">
     <div class="col-md-2 text-right">
        우편번호
     </div>
     
     <div class="col-md-3">
       <input type="text" name="zipcode" id="postcode" value="${cuser.zipcode }" readonly class="form-control" placeholder="우편번호">
     </div>
     <div class="col-md-3">
        <button type="button" class="btn btn-primary" onclick="execPostcode()">우편번호 찾기</button>
     </div>
     <div class="col-md-4"></div>
     
     
     <div class="col-md-10 offset-md-2 mt-2">
        <input type="text" name="address" id="address" value="${cuser.address }" readonly class="form-control" placeholder="주소" />
     </div>
     
     <div class="col-md-6 offset-md-2 mt-2">
        <input type="text" name="detailAddress" id="detailAddress" value="${cuser.detailAddress }" class="form-control" placeholder="주소" />
     </div>
     <div class="col-md-4 mt-2">
        <input type="text" name="extraAddress" id="extraAddress" value="${cuser.extraAddress }" class="form-control" placeholder="주소" />
     </div>
  </li>
  <li class="list-group-item d-flex flex-wrap">
     <div class="col-md-2 text-right">
        사진
     </div> 
     <div class="col-md-6">
        <input type="file" name="userimg" id="userimg" class="form-control" />
     </div>
     <div class="col-md-4"></div>
     <div class="col-md-2"></div>
     <div class="col-md-10">
     <c:choose>
	    <c:when test="${not empty cuser.userimg }">
	        <img src="res/upload/user/${cuser.userimg }" alt="${cuser.username }님" class="userimg">
	    </c:when>
	    <c:otherwise>
	        <img src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" class="userimg">
	    </c:otherwise>
	 </c:choose>
     </div>
  </li>
  <li class="list-group-item d-flex">
     <div class="col-md-2 text-right">
        자기소개
     </div> 
     <div class="col-md-10">
        <textarea name="userprofile" id="userprofile" class="form-control" rows="3">${cuser.userprofile }
        </textarea>
     </div>
  </li>
</ul>
<div class="text-center my-3">
  <button class="btn btn-danger mx-2 px-4" type="reset">취소</button>
  <button class="btn btn-primary mx-2 px-4" type="submit">전송</button> 
</div>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
    
    document.getElementById('userimg').addEventListener('change', function(event){
    	const file = event.target.files[0];
    	if(file){
    		const reader = new FileReader();
    		reader.onload = function(e){
    			//이미지 미리보기
    			let imgTag = document.querySelector(".userimg");
  				imgTag.src = e.target.result;
    		}
    		reader.readAsDataURL(file);
    	}
    });
</script>
