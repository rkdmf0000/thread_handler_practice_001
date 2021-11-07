package com.taxitabby.thread_handler_practice_001

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getSystemService
import android.widget.LinearLayout

import android.R
import android.util.Log
import android.view.View
import android.view.ViewGroup


interface AlertSectionInterface001 {
    public fun setContext(context: Context)
    public fun setInflater(inflater: LayoutInflater)
    public fun install(viewGroup: ViewGroup)
}

object AlertSection {

    //prefix purposes
    public val TAG by lazy{ "alert_section_object" }

    private var theSinglePrimaryThing : mother? = null

    internal class mother : AlertSectionInterface001{

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

        private final fun generateTemporary() : Unit {

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

        private fun _viewInjector() {

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



        init {
            init()
        }
    }

    internal class volatilities {
        public var is_visible : Boolean? = null
        public var timer_ms : Int? = null

        init {
            is_visible  = false
            timer_ms    = 0
        }
    }

    internal fun getInstance(): mother? {
        if (theSinglePrimaryThing == null) {
            theSinglePrimaryThing = mother()
        }
        return theSinglePrimaryThing
    }



}