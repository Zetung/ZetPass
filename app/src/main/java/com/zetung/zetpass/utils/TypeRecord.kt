package com.zetung.zetpass.utils

sealed class TypeRecord{
    abstract val number: Int
    abstract val type: String
    class Standard(override val number: Int = 0, override val type: String = "standard") : TypeRecord()
    class Note(override val number: Int = 1,override val type: String = "note") : TypeRecord()
}
