package com.example.kopring4.kopring

import com.example.kopring4.kopring.role.AccountRole
import com.example.kopring4.kopring.role.AccountRoleRepository
import jakarta.persistence.Id
import lombok.extern.java.Log
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import kotlin.math.log

@Controller
@RequestMapping("/account")
@Slf4j
class AccountController
    (
    private val accountService: AccountService,
    private val accountRepository: AccountRepository,
    private val accountRoleRepository: AccountRoleRepository


) {


    @GetMapping("/insert")
    fun insert(model: Model): String {

        model.addAttribute("account", Account())


        return "account/insert"
    }

    @PostMapping("/insert")
    @ResponseBody
    fun insertPost(account: Account): ResponseEntity<String> {

        accountService.insertAccount(account)

        return ResponseEntity.ok().body("회원 가입 성공")

    }

    @GetMapping("/detail")
    fun detail(model: Model, @RequestParam id: Long): String {

        var account: Account? = accountRepository.findById(id).orElse(null)

        model.addAttribute("account", account)

        return "account/detail"
    }

    @PostMapping("/detail")
    @ResponseBody
    fun detailPost(account: Account): ResponseEntity<String> {

        // 서비스에 영속성 위임
        accountService.setAccount(account)


        return ResponseEntity.ok().body("회원 수정 성공")

    }

    @GetMapping("/bulk-insert")
    @ResponseBody
    fun bulkInsert(): ResponseEntity<String> {

        var accountList: MutableList<Account> = mutableListOf()

        for (i in 1..100) {
            var target = Account(
                nickname = (1..10).map { ('a'..'z').random() }.joinToString(""),
                password = (1..10).map { ('a'..'z').random() }.joinToString("")
            )
            accountList.add(target)
        }

        accountService.bulkInsert(accountList)

        return ResponseEntity.ok().body("벌크 삽입 완료")
    }

    @GetMapping("/role/role-insert")
    @ResponseBody
    fun roleInsert(): ResponseEntity<String> {

        var roleSet: MutableSet<AccountRole> = mutableSetOf()

        val role1 = AccountRole(
            roleName = "1급보안",
            role = "ROLE_ONE"
        )

        val role2 = AccountRole(
            roleName = "2급보안",
            role = "ROLE_TWO"
        )
        val role3 = AccountRole(
            roleName = "3급보안",
            role = "ROLE_THREE"
        )
        val role4 = AccountRole(
            roleName = "4급보안",
            role = "ROLE_FOUR"
        )

        roleSet.add(role1)
        roleSet.add(role2)
        roleSet.add(role3)
        roleSet.add(role4)

        accountRoleRepository.saveAll(roleSet)

        return ResponseEntity.ok().body("권한 삽입 완료")

    }

    @GetMapping("/account-detail/{id}")
    fun accountDetail(@PathVariable id: Long, model: Model): String {

        val account: Account? = accountRepository.findById(id).orElseGet { null }
        val accountRoles = accountRoleRepository.findAll()

        model.addAttribute("account", account)
        model.addAttribute("accountRoles", accountRoles)

        return "account/account-detail"
    }

    @PostMapping("/account-detail")
    fun accountSave(accountDto: AccountDto): String {

        val accountRoles = accountRoleRepository.findByIdIn(accountDto.accountRole)

        var account = Account(
            id = accountDto.id,
            nickname = accountDto.nickname,
            password = accountDto.password,
            accountRole = accountRoles
        )

        accountService.setAccount(account)

        return "redirect:/account/account-detail/" + accountDto.id

    }


}