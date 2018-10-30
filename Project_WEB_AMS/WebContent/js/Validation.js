/**
 * 유효성 검사
 * @author 류세은
 */

/**
 * 계좌종류 선택 유효성검사
 * 계좌종류 미선택시 계좌등록버튼 비활성
 */
function borrowDisabled() {
	var getType = document.getElementById("accountType").value;
	if (getType == "입출금계좌") {
		document.getElementById("borrowMoney").disabled = true;
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
	} else if (getType == "마이너스계좌") {
		document.getElementById("borrowMoney").disabled = false;
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
	} else if (getType == "전체") {
		document.getElementById("message").value = "계좌종류를 선택해주십시오.";
		document.getElementById("add").disabled = true;
	}

}

function chooseType() {
	var getType = document.getElementById("accountType").value;
	if (getType == "전체") {
		return false;
	} else {
		return true;
	}
}

/**
 * 계좌번호 정규표현식 유효성검사
 * return: 유효성검사 통과여부
 */
function correctNum() {
	var getNum = document.getElementById("accountNum").value;

	var reg2 = /^[0-9]{4}-[0-9]{4}$/;
	var check1 = ("" == getNum);
	var check2 = reg2.test(getNum);
	if (check1 == false && check2 == true) {

		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
		return true;
	} else {
		document.getElementById("message").value = "계좌번호를 정확히 입력해주십시오. ex)1111(4자리)-2222(4자리)";
		document.getElementById("add").disabled = true;
		getNum = "";
		document.getElementById("accountNum").focus();
		return false;
	}
}

/**
 * 예금주명 정규표현식 유효성검사
 * return: 유효성검사 통과여부
 */
function correctName() {
	var getName = document.getElementById("accountOwner").value;
	var check1 = ("" == getName);
	var reg2 = /^[가-힣]{1,5}$/;
	var check2 = reg2.test(getName);
	if (check1 == false && check2 == true) {
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";

		return true;
	} else {
		document.getElementById("message").value = "예금주명을 정확히 입력해주십시오. ex)홍길동";
		document.getElementById("add").disabled = true;
		getName = "";
		document.getElementById("accountOwner").focus();
		return false;
	}

}

/**
 * 비밀번호 정규표현식 유효성검사
 * return: 유효성검사 통과여부
 */
function correctPw() {
	var getPasswd = document.getElementById("passwd").value;
	var check1 = ("" == getPasswd);
	var reg2 = /^[0-9]{4}$/;
	var check2 = reg2.test(getPasswd);
	if (check1 == false && check2 == true) {
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
		return true;
	} else {
		document.getElementById("message").value = "비밀번호를 정확히 입력해주십시오.(숫자 4자리)";
		document.getElementById("add").disabled = true;

		getPasswd = " ";
		document.getElementById("passwd").focus();
		return false;
	}
}

/**
 * 현재잔액 정규표현식 유효성검사
 * return: 유효성검사 통과여부
 */
function correctRestMoney() {
	var getRestMoney = document.getElementById("restMoney").value;
	var check1 = ("" == getRestMoney);
	var reg2 = /^[0]{1}$|^[1-9]{1}[0-9]*$/;
	var check2 = reg2.test(getRestMoney);
	if (check1 == false && check2 == true) {
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
		return true;
	} else {
		document.getElementById("message").value = "올바른 값을 입력해주십시오.";
		document.getElementById("add").disabled = true;
		getRestMoney = "";
		document.getElementById("restMoney").focus();
		return false;
	}

}

/**
 * 대출금액 정규표현식 유효성검사
 * return: 유효성검사 통과여부
 */
function correctBorrowMoney() {
	var getBorrowMoney = document.getElementById("borrowMoney").value;
	var check1 = ("" == getBorrowMoney);
	var reg2 = /^[0]{1}$|^[1-9]{1}[0-9]*$/;
	var check2 = reg2.test(getBorrowMoney);

	if (check1 == false && check2 == true) {
		document.getElementById("add").disabled = false;
		document.getElementById("message").value = "";
		return true;
	} else {
		document.getElementById("message").value = "올바른 값을 입력해주십시오.";
		document.getElementById("add").disabled = true;
		getBorrowMoney = "";
		getBorrowMoney.focus();
		return false;
	}
}
