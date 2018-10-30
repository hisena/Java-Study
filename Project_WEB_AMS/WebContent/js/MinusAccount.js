/**
 * 마이너스계좌
 * @author 류세은
 */

/**
 * 마이너스계좌 생성자
 * 
 * @param accountNum: 입력받은 계좌번호
 * @param accountOwner: 입력받은 예금주명
 * @param passwd: 입력받은 계좌 비밀번호
 * @param restMoney: 입력받은 계좌 현재잔액
 * @param borrowMoney: 입력받은 대출금액
 */

function MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney) {
	Account.call(this, accountNum, accountOwner, passwd, restMoney);
	this.borrowMoney = borrowMoney;
}

/**
 * MinusAccount 빈 값에 Account 객체를 할당
 */
MinusAccount.prototype = new Account(null, null, 0, 0);

/**
 * 마이너스계좌 생성자를 MinusAccount로 지정
 */
MinusAccount.prototype.constructor = MinusAccount;

/**
 * getRestMoney 메소드 
 * 현재잔액에 대출금액 차감하는 메소드
 */
MinusAccount.prototype.getRestMoney = function() {
	return this.restMoney - this.borrowMoney;
}

/**
 * MinusAccount toString 메소드
 * MinusAccount에 대한 정보를 문자열로 반환 
 * return: 문자열로 반환할 MinusAccount 관련 정보
 */
MinusAccount.prototype.toString = function() {
	this.restMoney = this.getRestMoney();

	return this.accountNum + '\t' + this.accountOwner + '\t' + this.restMoney
			+ '\t' + this.borrowMoney;
}
