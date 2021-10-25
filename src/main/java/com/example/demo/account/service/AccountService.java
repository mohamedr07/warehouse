package com.example.demo.account.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.dto.UpdateAccountDto;
import com.example.demo.account.model.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.role.model.Permission;
import com.example.demo.role.model.Role;
import com.example.demo.role.repository.RoleRepository;
import com.example.demo.utility.dto.PageableDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.security.config.SecurityConfig.accessExp;
import static com.example.demo.security.config.SecurityConfig.secretKey;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class AccountService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public AccountDto getAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()) {
            logger.error("Account does not exist");
            throw new IllegalStateException("Account Does Not Exist");
        }
        logger.info("get account with id: " + accountId);
        return new AccountDto(accountOptional.get());
    }

    public List<AccountDto> getAccounts(PageableDto pageableDto) {
        Page<Account> accounts = accountRepository.findAll(PageRequest.of(pageableDto.getPageNumber(), pageableDto.getNumberOfInstances()));
        List<AccountDto> accountDtos = new ArrayList<>();
        for(Account account : accounts) {
            accountDtos.add(new AccountDto(account));
        }
        logger.info("get all accounts");
        return accountDtos;
    }

    @Transactional
    public AccountDto setRole(Long accountId, UpdateAccountDto updateAccountDto) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<Role> role = roleRepository.findById(updateAccountDto.getRoleId());
        if(accountOptional.isEmpty() || role.isEmpty()) {
            logger.error("Account or Role Does Not Exist");
            throw new IllegalStateException("Account or Role Does Not Exist");
        }
        Account account = accountOptional.get();
        account.setRole(role.get());
        logger.info("set role: "+ role.get().getName() + " for account with id: " + accountId);
        return new AccountDto(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if(account == null) {
            logger.error("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(account.getRole() != null && account.getRole().getPermissions() != null) {
            account.getRole().getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        }
        logger.info("user loaded");
        return new User(account.getEmail(), account.getHashedPassword(), authorities);
    }



    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader == null || !(authorizationHeader.startsWith("Bearer "))) {
                throw new IllegalStateException("Token is missing");
            }
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            String token = authorizationHeader.substring("Bearer ".length());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();
            Account account = accountRepository.findByEmail(email);
            String accessToken = JWT.create()
                    .withSubject(account.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + accessExp))
                    .withIssuer(request.getRequestURI().toString())
                    .withClaim("permissions", account.getRole().getPermissions().stream().map(Permission::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            Map<String , String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", authorizationHeader.substring("Bearer ".length()));
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            logger.info("get a new access token from refresh token for user: " + account.getFullName());
        } catch (Exception e) {
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
            logger.error(e.getMessage());
        }
    }
}
