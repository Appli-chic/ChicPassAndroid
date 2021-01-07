package com.applichic.chicsecret

class RustGreetings {
    external fun greeting(pattern: String): String

    companion object {
        init {
            System.loadLibrary("security")
        }
    }
}
