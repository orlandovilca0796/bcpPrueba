package com.reto.backend.security.service;

import com.reto.backend.security.entity.Account;
import com.reto.backend.security.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Optional<Account> findyByUsername(String username){
        return this.accountRepository.findByUsername(username);
    }

    public boolean existsUsername(String username){
        return this.accountRepository.existsByUsername(username);
    }

    public Account saveAccount(Account account){
        if(existsUsername(account.getUsername())){
            return null;
        }
        return this.accountRepository.save(account);
    }

}
