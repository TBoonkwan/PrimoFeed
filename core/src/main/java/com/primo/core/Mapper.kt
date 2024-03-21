package com.primo.core

interface Mapper<in T, out R> {
    fun map(response : T): R
}