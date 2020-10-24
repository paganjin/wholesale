package com.anz.wholesale.controllers;

import com.anz.wholesale.dtos.account.AccountGetDto;
import com.anz.wholesale.dtos.account.AccountPostDto;
import com.anz.wholesale.dtos.account.AccountPutDto;
import com.anz.wholesale.dtos.transaction.TransactionGetDto;
import com.anz.wholesale.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/management/accounts")
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountManagementController {

    private final AccountService accountService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GROUPADMIN')")
    public List<AccountGetDto> getAllAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping(path="{accountId}")
    @PreAuthorize("hasAuthority('account:write')")
    public List<TransactionGetDto> getTransactions(@PathVariable("accountId") Long accountId) {
        return accountService.getTransactionsByUser(accountId);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('account:write')")
    public void addAccount(AccountPostDto accountPostDto) {
        System.out.println(accountPostDto);
    }

    @DeleteMapping(path = "{accountId}")
    @PreAuthorize("hasAuthority('account:write')")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        System.out.println(accountId);
    }

    @PutMapping(path = "{accountId}")
    @PreAuthorize("hasAuthority('account:write')")
    public void updateAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountPutDto accountPutDto){
        System.out.println(String.format("%s %s", accountId, accountPutDto));
    }
}
