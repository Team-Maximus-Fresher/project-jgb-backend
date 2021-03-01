package com.axis.jgbbackend.model

class ReqData {
    var loanAmount:Int = 0
    var loanPurpose:String?=null
    var status:String?=null
    var pan:String?=null
    constructor()
    constructor(
            loanAmount: Int,
            loanPurpose: String,
            status:String,
            pan:String
            ) {
        this.loanAmount=loanAmount
        this.loanPurpose = loanPurpose
        this.status=status
        this.pan=pan

    }
}