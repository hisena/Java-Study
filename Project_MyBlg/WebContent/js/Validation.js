/**
 * 유효성 검사
 * @author 류세은
 */

/**
 * 아이디 정규표현식 유효성검사
 */
function correctId() {
	var reg2 = /^[a-zA-Z0-9]{2,8}$/;
	var getId = document.getElementsByName("id")[0].value;
	var id = getId.trim();
	var check1 = ("" == id);
	// 빈칸 문제
	var check2 = reg2.test(id);
	// 정규표현식 적합성
	if (check1 == false && check2 == true) {
		document.getElementById("id").style.display = 'none';
		document.getElementsByName("regist")[0].removeAttribute('disabled');
	} else {
		document.getElementById("id").innerHTML = "영문대소문자 및 숫자 2-8자리로 입력해주십시오";
		document.getElementById("id").style.color = 'red';
		document.getElementById("id").style.display = 'block';
		document.getElementsByName("regist")[0].disabled = 'true';
	}
}

/**
 * 이름 정규표현식 유효성검사
 */
function correctName() {
	var getName = document.getElementsByName("name")[0].value;
	var name = getName.trim();
	var check1 = ("" == name);
	var reg2 = /^[가-힣]{1,5}$/;
	var check2 = reg2.test(name);
	if (check1 == false && check2 == true) {
		document.getElementById("name").style.display = 'none';
		document.getElementsByName("regist")[0].removeAttribute('disabled');
	} else {
		document.getElementById("name").innerHTML = "한글 5자리 이하로 입력해주십시오";
		document.getElementById("name").style.color = 'red';
		document.getElementById("name").style.display = 'block';
		document.getElementsByName("regist")[0].disabled = 'true';
	}

}

/**
 * 비밀번호 정규표현식 유효성검사
 */

function correctPw() {
	var getPasswd = document.getElementsByName("passwd")[0].value;
	var passwd = getPasswd.trim();
	var check1 = ("" == passwd);
	var reg2 = /^[a-zA-Z0-9]{2,8}$/;
	var check2 = reg2.test(passwd);
	if (check1 == false && check2 == true) {
		document.getElementById("passwd").style.display = 'none';
		document.getElementsByName("regist")[0].removeAttribute('disabled');
	} else {
		document.getElementById("passwd").innerHTML = "영문대소문자 및 숫자 2-8자리로 입력해주십시오";
		document.getElementById("passwd").style.color = 'red';
		document.getElementById("passwd").style.display = 'block';
		document.getElementsByName("regist")[0].disabled = 'true';
	}
}

/**
 * 이메일 정규표현식 유효성검사
 */
function correctEmail() {
	var getEmail = document.getElementsByName("email")[0].value;
	var email = getEmail.trim();
	var check1 = ("" == email);
	var reg2 = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	var check2 = reg2.test(email);
	if (check1 == false && check2 == true) {
		document.getElementById("email").style.display = 'none';
		document.getElementsByName("regist")[0].removeAttribute('disabled');
	} else {
		document.getElementById("email").innerHTML = "이메일을 정확하게 입력해주십시오";
		document.getElementById("email").style.color = 'red';
		document.getElementById("email").style.display = 'block';
		document.getElementsByName("regist")[0].disabled = 'true';
	}
}
