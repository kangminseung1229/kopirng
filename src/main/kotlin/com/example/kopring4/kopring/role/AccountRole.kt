package com.example.kopring4.kopring.role

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class AccountRole (

    @Id
    @GeneratedValue
    var id : Long?= null,
    var role : String? = null,
    var roleName : String? = null
)