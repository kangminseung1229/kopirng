package com.example.kopring4.kopring

import com.example.kopring4.kopring.role.AccountRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Account(

    @Id
    @GeneratedValue
    var id: Long? = null,
    var nickname: String? = null,
    var password: String? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "account_auth",
        joinColumns = [ JoinColumn(name = "account_id")],
        inverseJoinColumns = [ JoinColumn(name = "account_role_id")]
    )
    var accountRole: MutableSet<AccountRole>? = mutableSetOf(),

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)

