package com.horizon.core.model

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.Id


@Entity
@Document(collection = "user")
class User() {

    @Id
    var id = ""

    var fullName = ""

    var organization = ""

    var username = ""

    var mobile = ""

    var password = ""

    var isAdmin = false


}