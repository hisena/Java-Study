/**
 * 은행계좌관리
 * 
 * @author 류세은
 * 
 */

function AccountManager() {

	this.accounts = [];

}

/**
 * 계좌저장 메소드 
 * 입력받은 account을 accounts에 저장
 */
AccountManager.prototype.add = function(account) {

	if (account instanceof Account) {
		for ( var i in this.accounts) {
			if (account.accountNum == this.accounts[i].accountNum) {
				return false;
			}
		}

		this.accounts.push(account);
	} else {
		return false;
	}
}

/**
 * 계좌전체조회 메소드 
 * accounts에 들어있는 계좌를 목록으로 반환
 * return: 등록된 계좌목록 반환
 */
AccountManager.prototype.list = function() {
	return this.accounts;
}

/**
 * 계좌번호를 이용한 계좌조회 메소드 
 * 입력받은 계좌번호와 일치하는 계좌가 존재할 시 계좌 반환 
 * return: 해당 계좌번호를 가진 계좌 반환
 */
AccountManager.prototype.get = function(accountNum) {
	for ( var i in this.accounts) {
		if (accountNum == this.accounts[i].accountNum) {
			return this.accounts[i];
		}
	}
}

/**
 * 예금주명을 이용한 계좌조회 메소드 
 * 입력받은 예금주명과 일치하는 계좌가 존재할 시 계좌 반환 
 * return: 해당 예금주명으로 등록된 계좌 반환
 */
AccountManager.prototype.search = function(accountOwner) {
	for ( var i in this.accounts) {
		if (accountOwner == this.accounts[i].accountOwner) {
			return this.accounts[i];
		}
	}
	return false;
}

/**
 * 계좌번호를 이용한 계좌삭제 메소드
 * 입력받은 계좌번호와 일치하는 계좌가 존재할 시 계좌 반환
 * return 계좌삭제여부 반환 
 */
AccountManager.prototype.remove = function(accountNum) {
	for ( var i in this.accounts) {
		if (accountNum == this.accounts[i].accountNum) {
			this.accounts.splice(i, 1);
			return true;
		}
	}
	return false;
}
