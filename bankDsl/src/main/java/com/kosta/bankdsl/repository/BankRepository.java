package com.kosta.bankdsl.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bankdsl.entity.Account;
import com.kosta.bankdsl.entity.Member;
import com.kosta.bankdsl.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;

//save나 findBy 는 jpa 레포지토리를 이용하고 그 외는 동적쿼리 dsl을 이용하려고 함

@Repository
public class BankRepository {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;
	
	//계좌개설
	public void insertAccount(Account acc) {
		//insert의 경우는 querydsl 사용 x
		accountRepo.save(acc);
		
	}
	//계좌조회
	public Account findAccountById(String id) {
		QAccount account = QAccount.account;
		return jpaQueryFactory.select(account) //전체 (*) 일 경우 테이블명 그대로 씀
					   .from(account)
					   .where(account.id.eq(id))
					   .fetchOne();	 //.fetchOne: 하나의 데이터 select
		
	}
	
	//전체계좌조회
	public List<Account> findAllAccount() {
		QAccount account = QAccount.account;
		return jpaQueryFactory.selectFrom(account).fetch(); //.fetch : 여러개의 데이터 select
	}
	//입금,출금
	//@Transactional : 데이터가 바뀌는 쿼리문에 붙여준다. (update,delete)
	@Transactional
	public void updateBalance(String id,Integer balance) {
		QAccount account = QAccount.account;
		jpaQueryFactory.update(account)
		.set(account.balance, balance)
		.where(account.id.eq(id))
		.execute();
	}
	
	
	//계좌이체
	@Transactional	
	public void transfer(String sid,String rid,Integer sbalance, Integer rbalance) {
		// 하나의 쿼리문에 하나만 실행해야하기 때문에  new 로 각각 다른 객체를 생성해야한다.
		QAccount sendAccount = new QAccount("account1");
		QAccount recvAccount = new QAccount("account2");
		
		jpaQueryFactory.update(sendAccount)
			.set(sendAccount.balance, sbalance)
			.where(sendAccount.id.eq(sid))
			.execute();
		
		jpaQueryFactory.update(recvAccount)
		.set(recvAccount.balance, rbalance)
		.where(recvAccount.id.eq(rid))
		.execute();

		
		
	}
	// 회원가입
	public void insertMember(Member member) throws Exception {
		Optional<Member> oMember = memberRepo.findById(member.getId());
		if(oMember.isPresent()) throw new Exception("중복 아이디 오류");
		memberRepo.save(member);
	}	
	
	// 회원조회(로그인)
	public Member findMemberById(String id) throws Exception {
		Member member = memberRepo.findById(id).orElseThrow(() -> new Exception("아이디 오류"));
		return member;
	}
	
	// 중복아이디조회
	public boolean existMemberId(String id) throws Exception {
		return memberRepo.findById(id).isPresent();		
	}
}
