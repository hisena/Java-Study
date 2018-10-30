/**
 * 학생객체 추상화
 */


var kosta = {};
kosta.school = {};
kosta.school.Student = function (name, korean, math, english, science) {
	this.name = name;
	this.korean = korean;
	this.math = math;
	this.english = english;
	this.science = science;
}

/** 사용자 정의 객체(생성자) */

Student.schoolName = "KOSTA 대학교";

// 프로토타입에 메소드 저장
Student.prototype.getSum = function(){
	return this.korean + this.math + this.english + this.science;
}

Student.prototype.getAverage = function(){
	return this.getSum() / 4;
}

Student.prototype.toString = function(){ //이때 function은 메서드라고 함
	return this.name+'\t'+this.korean+'\t'+this.math+'\t'+this.english+'\t'+this.science;
}

 //static 변수처럼 적용됨