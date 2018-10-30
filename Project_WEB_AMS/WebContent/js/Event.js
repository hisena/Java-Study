/**
 * 계좌 조회, 생성, 삭제
 * 
 * @author 류세은
 */

var account = Account();
var minusAccount = MinusAccount();
var accountManager = AccountManager();

/**
 * 전체계좌조회
 */
function list() {

	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	for ( var i in accountList) {
		if (accountList[i] instanceof MinusAccount) {
			accountTable += '<tr>';
			accountTable += '<td>' + '마이너스계좌' + '</td>';
			accountTable += '<td>' + accountList[i].accountNum + '</td>';
			accountTable += '<td>' + accountList[i].accountOwner + '</td>';
			accountTable += '<td>' + accountList[i].getRestMoney() + '</td>';
			accountTable += '<td>' + accountList[i].borrowMoney + '</td>';
			accountTable += '</tr>';
		} else {
			accountTable += '<tr>';
			accountTable += '<td>' + '입출금계좌' + '</td>';
			accountTable += '<td>' + accountList[i].accountNum + '</td>';
			accountTable += '<td>' + accountList[i].accountOwner + '</td>';
			accountTable += '<td>' + accountList[i].restMoney + '</td>';
			accountTable += '<td>' + '-' + '</td>';
			accountTable += '</tr>';
		}
	}
	document.getElementsByTagName("table").item(0).innerHTML = accountTable;

}

/**
 * 계좌종류로 조회
 */
function typelist() {

	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	var getType = document.getElementById("accountType").value;

	if (getType == "마이너스계좌") {
		for ( var i in accountList) {
			if (accountList[i] instanceof MinusAccount) {
				accountTable += '<tr>';
				accountTable += '<td>' + '마이너스계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].getRestMoney()
						+ '</td>';
				accountTable += '<td>' + accountList[i].borrowMoney + '</td>';
				accountTable += '</tr>';
			} else {
				continue;
			}
			document.getElementsByTagName("table").item(0).innerHTML = accountTable;
		}
	} else if (getType == "입출금계좌") {
		for ( var i in accountList) {
			if (accountList[i] instanceof MinusAccount) {
				continue;
			} else {
				accountTable += '<tr>';
				accountTable += '<td>' + '입출금계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].restMoney + '</td>';
				accountTable += '<td></td>';
				accountTable += '</tr>';
			}
		}
		document.getElementsByTagName("table").item(0).innerHTML = accountTable;
	}
}

/**
 * 계좌번호로 계좌조회
 */
function get() {

	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	var getNum = document.getElementById("accountNum").value;

	for ( var i in accountList) {
		if (getNum == accountList[i].accountNum) {
			if (accountList[i] instanceof MinusAccount) {
				accountTable += '<tr>';
				accountTable += '<td>' + '마이너스계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].getRestMoney()
						+ '</td>';
				accountTable += '<td>' + accountList[i].borrowMoney + '</td>';
				accountTable += '</tr>';
			} else {
				accountTable += '<tr>';
				accountTable += '<td>' + '입출금계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].restMoney + '</td>';
				accountTable += '<td>' + '-' + '</td>';
				accountTable += '</tr>';
			}
			break;

		} else {
			document.getElementById("message").value = '해당 번호의 계좌가 존재하지 않습니다.';
			break;
		}
	}
	document.getElementsByTagName("table").item(0).innerHTML = accountTable;

}

/**
 * 예금주명으로 계좌조희
 */
function search() {
	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	var getName = document.getElementById("accountOwner").value;
	for ( var i in accountList) {
		if (getName == accountList[i].accountOwner) {
			if (accountList[i] instanceof MinusAccount) {
				accountTable += '<tr>';
				accountTable += '<td>' + '마이너스계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].getRestMoney()
						+ '</td>';
				accountTable += '<td>' + accountList[i].borrowMoney + '</td>';
				accountTable += '</tr>';
			} else {
				accountTable += '<tr>';
				accountTable += '<td>' + '입출금계좌' + '</td>';
				accountTable += '<td>' + accountList[i].accountNum + '</td>';
				accountTable += '<td>' + accountList[i].accountOwner + '</td>';
				accountTable += '<td>' + accountList[i].restMoney + '</td>';
				accountTable += '<td>' + '-' + '</td>';
				accountTable += '</tr>';
			}
		}
	}
	if (accountTable == "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>") {
		document.getElementById("message").value = '해당 번호의 계좌가 존재하지 않습니다.';
	}

	document.getElementsByTagName("table").item(0).innerHTML = accountTable;
}

/**
 * 계좌등록
 */
function add() {

	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	var getType = document.getElementById("accountType").value;
	var getNum = document.getElementById("accountNum").value;
	var getName = document.getElementById("accountOwner").value;
	var getPasswd = document.getElementById("passwd").value;
	var getRestMoney = document.getElementById("restMoney").value;
	var getBorrowMoney = document.getElementById("borrowMoney").value;

	if (getType == "입출금계좌") {
		if (chooseType() == true && correctNum() == true
				&& correctName() == true && correctPw() == true
				&& correctRestMoney() == true) {
			accountManager.add(new Account(getNum, getName, getPasswd,
					getRestMoney));
			document.getElementById("message").value = "계좌가 등록되었습니다.";
			document.getElementsByTagName("table").item(0).innerHTML = accountTable;

			document.getElementById("accountType").value = "전체";
			document.getElementById("accountNum").value = "";
			document.getElementById("accountOwner").value = "";
			document.getElementById("passwd").value = "";
			document.getElementById("restMoney").value = "";
			document.getElementById("borrowMoney").value = "";
		}
	} else {
		if (chooseType() == true && correctNum() == true
				&& correctName() == true && correctPw() == true
				&& correctRestMoney() == true && correctBorrowMoney() == true) {

			accountManager.add(new MinusAccount(getNum, getName, getPasswd,
					getRestMoney, getBorrowMoney));


			document.getElementsByTagName("table").item(0).innerHTML = accountTable;
			document.getElementById("accountType").value = "전체";
			document.getElementById("accountNum").value = "";
			document.getElementById("accountOwner").value = "";
			document.getElementById("passwd").value = "";
			document.getElementById("restMoney").value = "";
			document.getElementById("borrowMoney").value = "";
		}
	}
}

/**
 * 계좌번호로 계좌삭제
 */
function remove() {
	var accountTable = "<tr><th>계좌종류</th><th>계좌번호</th><th>예금주명</th><th>현재잔액</th><th>대출금액</th></tr>";
	var accountList = accountManager.list();

	var getNum = document.getElementById("accountNum").value;
	for ( var i in accountList) {
		if (getNum == accountList[i].accountNum) {
			accountList.splice(i, 1);
		}
	}

	document.getElementsByTagName("table").item(0).innerHTML = accountTable;
}
