package com.bemo.graduationproject.Classes



class User {
    var userId:String? = null
    var name :String? = null
    var code :Int? = null
    var nationalId :Int? = null
    var grade :String? = null

    constructor()
    constructor(userId:String?,name :String?,code :Int?,nationalId :Int?,grade:String?){
        this.userId=userId
        this.name=name
        this.code=code
        this.nationalId=nationalId
        this.grade=grade


    }
}