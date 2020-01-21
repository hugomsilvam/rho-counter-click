package com.rho.counterclick.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "Counter")
data class Counter(
    @Id
    @Column(value = "counterID")
    val id: Long,

    @Column(value = "counterValue")
    val value: Long
)
