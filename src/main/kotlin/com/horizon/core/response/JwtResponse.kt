package com.horizon.core.response


class JwtResponse {
    var id: String = ""
    var fullName = ""
    var organization = ""
    var username = ""
    var mobile = ""
    var isAdmin: Boolean = false
    var token = ""
    var type = "Bearer"

    constructor() : super() {
    }

    constructor(
        id: String, fullName: String, organization: String, username: String,
        mobile: String, isAdmin: Boolean, token: String, type: String
    ) : super() {
        this.id = id
        this.fullName = fullName
        this.organization = organization
        this.username = username
        this.mobile = mobile
        this.isAdmin = isAdmin
        this.token = token
        this.type = type
    }

    constructor(id: String, fullName: String, username: String, token: String) : super() {
        this.id = id
        this.fullName = fullName
        this.username = username
        this.token = token
    }
}