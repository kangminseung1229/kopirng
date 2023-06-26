package com.example.kopring4.kopring

data class AccountDto(

    var id: Long,
    var nickname: String,
    var password: String,
    var accountRole: MutableSet<Long>

) {
}