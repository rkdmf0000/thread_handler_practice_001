package com.taxitabby.thread_handler_practice_001

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getSystemService
import android.widget.LinearLayout

import android.R
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import java.time.Duration


interface AlertSectionInterface001 {
    public fun setContext(context: Context)
    public fun setInflater(inflater: LayoutInflater)
    public fun install(viewGroup: ViewGroup)

    public fun makeText() : AlertSection.volatilities?
}

interface AlertSectionInterface002 {
    public abstract fun show() : Unit
    public abstract fun show(ms : Long) : Unit
}

object AlertSection {

    //prefix purposes
    public val TAG by lazy{ "alert_section_object" }

    private var theSinglePrimaryThing : mother? = null

    class mother : AlertSectionInterface001{

        public var m_inflater : LayoutInflater? = null
        public var m_context : Context? = null

        public var m_mother_body : LinearLayout? = null

        public var is_now_installed_on_this_activity : Boolean? =  null
        public var is_enabled : Boolean? = null
        public var is_visibled : Boolean? = null

        public var n_maximum_temporarie : Int? = null //(another it minimum is 0)
        public var n_section_width : Int? = null
        public var n_section_height : Int? = null
        public open var section_size_unit : String? = null

        public var n_temporaries_cnt : Int? = null

        public fun init() {
            this.m_inflater                         = null
            this.m_context                          = null
            this.is_now_installed_on_this_activity  = false
            this.is_enabled                         = false
            this.is_visibled                        = false
            this.n_maximum_temporarie               = 5
            this.n_section_width                    = 0
            this.n_section_height                   = 0
            this.section_size_unit                  = "dp"
            this.n_temporaries_cnt                  = 0
        }

        private final fun generateMotherBody() : LinearLayout? {
            try {
                if (this.m_mother_body != null)
                    throw Exception("already_mother_body")

                if (this.m_inflater == null)
                    throw Exception("null_inflater")

                if (this.m_context == null)
                    throw Exception("null_context")

                return LinearLayout(this.m_context)

            } catch (e : Exception) {
                Log.e(TAG, e.toString())
                return null
            }
        }

        private final fun generateTemporary() : volatilities? {
            try {

                if (this.m_inflater == null)
                    throw Exception("null_inflater")

                if (this.m_context == null)
                    throw Exception("null_context")

                if (this.m_context != null)
                    return volatilities(this.m_context!!)

                throw Exception("default_error")
            } catch (e : Exception) {
                Log.e(TAG, e.toString())
                return null
            }
        }

        private fun _viewGroupInsertion(viewGroup: ViewGroup) : Unit {
            val el : LinearLayout? = generateMotherBody()

            if (el == null) {
                Log.e(TAG, "_viewGroupInsertion : mother body is null")
                return
            }

            this.m_mother_body = null
            this.m_mother_body = el
            viewGroup.addView(this.m_mother_body)
            this.is_now_installed_on_this_activity = true
            Log.i(TAG, "_viewGroupInsertion : ok")
        }


        public override fun setContext(context: Context) {
            this.m_context = null
            this.m_context = context
        }

        public override fun setInflater(inflater: LayoutInflater) {
            this.m_inflater = null
            this.m_inflater = inflater
        }

        public override fun install(viewGroup: ViewGroup) {
            _viewGroupInsertion(viewGroup)
        }

        override fun makeText(): volatilities? {
            return generateTemporary()
        }


        init {
            init()
        }
    }

    class volatilities(context: Context) : AlertSectionInterface002 {
        public var is_enable : Boolean? = null
        public var is_visible : Boolean? = null
        public var time_ms : Long? = null
        public var time_at : Long? = null

        public var m_group : RelativeLayout = RelativeLayout(context)

        private fun _getCurrentTimeStamp() : Long {
            return System.currentTimeMillis()
        }


        init {
            is_enable   = false
            is_visible  = false
            time_ms     = 0
            time_at     = 0
        }

        //네네치
        override fun show() {
            time_ms = 1000
            time_at = _getCurrentTimeStamp()
        }

        //배먹어배
        override fun show(ms : Long) {
            time_ms = ms
            time_at = _getCurrentTimeStamp()
        }
    }

    fun getInstance(): mother? {
        if (theSinglePrimaryThing == null) {
            theSinglePrimaryThing = mother()
        }
        return theSinglePrimaryThing
    }



}