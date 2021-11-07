package com.taxitabby.thread_handler_practice_001

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *  It is just meant to define.
 *  Sometimes we have to bull's to do and that is important in our job
 */
open class OCMD : AppCompatActivity() {

    public final enum class EV() {
        NULL,   //Ping!
        FRESH,  //Printer re-draw
        UPDATE, //Data update
        INSERT, //?
        PAUSE,  //?
        CLEAR,  //?
        DELETE; //?
        fun value() = this.ordinal
    }

    public final enum class ET() {
        BACK, MAIN;
        fun value() = this.ordinal
    }


    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("UPLINK-LEVEL", "1")
    }
}


public class subActionThread : Thread() {
    private final val TAG : String = "HelloMother"
}