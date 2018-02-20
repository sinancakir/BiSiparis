package io.android.bisiparis.enums

/**
 * Created by sinan on 10.02.2018.
 */
enum class GeneralInfo {
    DatabaseName {
        override fun toString(): String {
            return "Orders"
        }
    },
    DatabaseVersion {
        override fun toString(): String {
            return "3"
        }
    },
    CustomerName {
        override fun toString(): String {
            return "CustomerName"
        }
    },
    NoOrderValues {
        override fun toString(): String {
            return "Herhangi bir müşteri kaydı bulunmamaktadır."
        }
    },
    Welcome {
        override fun toString(): String {
            return " Hoşgeldiniz"
        }
    },
    UserName {
        override fun toString(): String {
            return "UserName"
        }
    },
    RememberMe {
        override fun toString(): String {
            return "RememberMe"
        }
    },
    SharedPrefName {
        override fun toString(): String {
            return "Name"
        }
    },
    CheckBoxIsChecked {
        override fun toString(): String {
            return "isChecked"
        }
    }
}