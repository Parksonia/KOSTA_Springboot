package com.kosta.bankdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankdsl.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
