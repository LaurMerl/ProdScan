package org.prodscan.database.tools


interface ITable {
    fun Insert():DbStatus;
    fun Update():DbStatus;
    fun Delete():DbStatus;
}