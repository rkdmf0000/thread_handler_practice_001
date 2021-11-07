package com.taxitabby.thread_handler_practice_001

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : OCMD() {

    private lateinit var nowContext : Context

    private var mainNumber : Int = 0
    private var backNumber : Int = 0
    private var waiterAlreadyWait : Boolean = false
    private var waiterForceStopFlag : Boolean = false

    private lateinit var elementOfNumberPrinter : TextView
    private lateinit var elementOfNumberBackNumberPrinter : TextView
    private lateinit var elementOfFirstButton : Button
    private lateinit var elementOfSecondButton : Button
    private lateinit var elementOfTriggerRePatchButton : Button

    private lateinit var elementOfShowAlertButton : Button

    private val handler : Handler = Handler { msg ->
        var completeFlag: Boolean = true

        when (msg.what) {

            //Just return ping
            EV.NULL.value() -> {
                Toast.makeText(nowContext, "PONG!", Toast.LENGTH_SHORT).show()
            }

            EV.FRESH.value() -> {
                flushPrintData()

            }

            EV.UPDATE.value() -> {
                when (msg.arg1) {
                    ET.BACK.value() -> {

                    }
                    ET.MAIN.value() -> {

                    }
                }
            }

            else -> {
                completeFlag = false
            }

        }

        completeFlag
    }


    private fun _init() {
        this.mainNumber             = 0
        this.backNumber             = 0
        this.waiterAlreadyWait      = false
        this.waiterForceStopFlag    = false
    }
    private fun _initUnderStuff() {

    }

    private fun _initAll() {
        _init()
        _initUnderStuff()
    }

    private fun WaiterToDo() {
        ++this.backNumber
    }

    private final fun WaiterWaitsEveryDayEveryTimeEvenYouSleepingInYourLife() {
        if (waiterForceStopFlag)
            return
            waiterAlreadyWait = true

        val handler: Boolean = Handler().postDelayed({
            WaiterToDo()
            flushPrintData()
            if (!waiterForceStopFlag)
                WaiterWaitsEveryDayEveryTimeEvenYouSleepingInYourLife() //loop
            else
                waiterForceStopFlag = false
        }, 100)
    }

    private fun triggerDeployer() {

        setButtonEventListener(elementOfFirstButton) {

        }

        setButtonEventListener(elementOfSecondButton) {
            handler.sendEmptyMessage(EV.NULL.value())
            var x : Message = handler.obtainMessage()
            x.what = EV.NULL.value()
            handler.sendMessage(x)
        }

        setButtonEventListener(elementOfTriggerRePatchButton) {
            Toast.makeText(this, "RE-PATCHED OF BUTTON TRIGGER", Toast.LENGTH_SHORT).show()
            triggerDeployer()
        }

        setButtonEventListener(elementOfShowAlertButton) {
            //layoutInflater
            val inst : AlertSection.mother  = AlertSection.getInstance() as AlertSection.mother
            val el = findViewById<ViewGroup>(R.id.god_over)
            inst.setContext(this)
            inst.setInflater(layoutInflater)
            inst.install(el)

        }

    }

    private fun flushPrintData() {
        elementOfNumberPrinter.text             = this.mainNumber.toString()
        elementOfNumberBackNumberPrinter.text   = this.backNumber.toString()
    }



    private fun setButtonEventListener(buff: Button, callback: () -> Unit) {
        try {
            if (buff != null) {
                buff.setOnClickListener(null)
                buff.setOnClickListener {
                    callback()
                }
            } else {
                throw Exception("ErrorOfButtonIsNull")
            }
        } catch (e : Exception) {

        }

    }

    /**
     * 상위에서도 클레스에서도 작성되있음
     * 뭐부터 실행되는지 보려고 씀ㅋ
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("UPLINK-LEVEL", "2")

        this.nowContext = applicationContext

        this.elementOfNumberPrinter             = findViewById<TextView>(R.id.element001_number_print_object)
        this.elementOfNumberBackNumberPrinter   = findViewById<TextView>(R.id.element004_back_number_print_object)
        this.elementOfFirstButton               = findViewById<Button>(R.id.element002_add_btn_of_main)
        this.elementOfSecondButton              = findViewById<Button>(R.id.element003_add_btn_of_second)
        this.elementOfTriggerRePatchButton      = findViewById<Button>(R.id.element005_refresh_btn_trigger)

        this.elementOfShowAlertButton           = findViewById<Button>(R.id.element006_show_alert_button)

//        val HelloThread : subActionThread
//        HelloThread.start()

        triggerDeployer()
        flushPrintData()
        this.WaiterWaitsEveryDayEveryTimeEvenYouSleepingInYourLife()

    }


    init {
        _initAll()
    }

}



