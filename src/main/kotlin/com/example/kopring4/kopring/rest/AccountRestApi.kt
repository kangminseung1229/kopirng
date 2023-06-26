package com.example.kopring4.kopring.rest

import com.example.kopring4.kopring.Account
import com.example.kopring4.kopring.AccountRepository
import com.example.kopring4.kopring.role.AccountRole
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountRestApi
    (
    private val accountRepository: AccountRepository
)

{

        @GetMapping("/rest/v1/grant-insert")
        fun grantInsert(account: Account, accountRole: AccountRole): ResponseEntity<String> {

            return ResponseEntity.ok().body("삽입완료")

        }

}