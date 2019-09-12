package com.example.rickandmortykotlin.threads

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

object Executors{

    val networkIO:()-> ScheduledExecutorService = {Executors.newScheduledThreadPool(3)}

}