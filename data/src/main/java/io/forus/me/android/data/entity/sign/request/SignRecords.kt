package io.forus.me.android.data.entity.sign.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SignRecords: Serializable {

    constructor(email: String, firstname: String? = null, lastname: String? = null, bsn: String? = null, phoneNumber: String?) {
        this.email = email
        this.firstname = firstname
        this.lastname = lastname
        this.bsn = bsn
        this.phoneNumber = phoneNumber
    }

    @SerializedName("primary_email")
    @Expose
    var email: String

    @SerializedName("given_name")
    @Expose
    var firstname: String?

    @SerializedName("family_name")
    @Expose
    var lastname: String?

    @SerializedName("bsn")
    @Expose
    var bsn: String?

    @SerializedName("telephone")
    @Expose
    var phoneNumber: String?

}
