package com.example.demo.account.controller;

import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.dto.UpdateAccountDto;
import com.example.demo.account.service.AccountService;
import com.example.demo.utility.dto.PageableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAccounts(@RequestBody PageableDto pageableDto) {
        List<AccountDto> accountDtos = accountService.getAccounts(pageableDto);
        if (accountDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountId") Long accountId) {
        AccountDto accountDto = accountService.getAccount(accountId);
        if (accountDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping(path = "{accountId}")
    public ResponseEntity<AccountDto> setRole(@PathVariable("accountId") Long accountId, @RequestBody @Valid UpdateAccountDto updateAccountDto) {
        AccountDto accountDto = accountService.setRole(accountId, updateAccountDto);
        if (accountDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.refreshToken(request, response);
    }
}
