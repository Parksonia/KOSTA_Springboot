package com.kosta.bankback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankback.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
