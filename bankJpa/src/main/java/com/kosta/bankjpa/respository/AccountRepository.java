package com.kosta.bankjpa.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankjpa.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> { //ID:primary type

}
