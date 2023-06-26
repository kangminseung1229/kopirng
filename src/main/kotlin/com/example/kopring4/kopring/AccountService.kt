package com.example.kopring4.kopring

import org.hibernate.property.access.internal.PropertyAccessStrategyCompositeUserTypeImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun insertAccount(account: Account) {

        accountRepository.save(account)
    }

    fun setAccount(account: Account) {

        accountRepository.save(account)
    }

    fun bulkInsert(accountList: MutableList<Account>) {
        accountRepository.saveAll(accountList)
    }
}