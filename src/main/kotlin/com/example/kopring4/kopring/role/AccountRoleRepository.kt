package com.example.kopring4.kopring.role

import com.example.kopring4.kopring.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRoleRepository: JpaRepository<AccountRole, Long> {

    fun findByIdIn(ids : MutableSet<Long>) : MutableSet<AccountRole>
}