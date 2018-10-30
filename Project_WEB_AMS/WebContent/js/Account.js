/**
 * 은행계좌
 * @author 류세은
 */

Account.bankName = 'KOSTA 은행';

/**
 * 입출금계좌 생성자
 * 
 * @param accountNum: 입력받은 계좌번호
 * @param accountOwner: 입력받은 예금주명
 * @param passwd: 입력받은 계좌 비밀번호
 * @param restMoney: 입력받은 계좌 현재잔액
 */
function Account(accountNum, accountOwner, passwd, restMoney) {
	this.accountNum = accountNum;
	this.accountOwner = accountOwner;
	this.passwd = passwd;
	this.restMoney = restMoney;
}

/**
 * 입금 메소드 
 * money값을 받아 현재잔액에 저장 
 * return: money값을 포함한 현재잔액
 */
Account.prototype.deposit = function(money) {
	return this.restMoney += money;
}

/**
 * 출금 메소드 
 * money값을 받아 현재잔액에서 차감 
 * return: money값을 차감한 후의 현재잔액
 */
Account.prototype.withdraw = function(money) {
	return this.restMoney -= money;
}

/**
 * 비밀번호 확인 메소드 
 * 입력받은 비밀번호의 일치여부 확인
 * return: 비밀번호의 일치여부
 */
Account.prototype.checkPasswd = function(passwd) {
	return this.passwd == passwd;
}

/**
 * toString 메소드 
 * account에 대한 정보를 문자열로 반환 
 * return: 문자열로 반환할 account 관련 정보
 */
Account.prototype.toString = function() {
	return this.accountNum + '\t' + this.accountOwner + '\t' + this.restMoney;
}
