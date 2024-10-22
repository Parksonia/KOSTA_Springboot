package com.kosta.boarddsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boarddsl.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
