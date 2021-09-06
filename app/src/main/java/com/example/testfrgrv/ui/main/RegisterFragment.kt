package com.example.testfrgrv.ui.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.testfrgrv.R
import kotlinx.android.synthetic.main.fragment_register.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var et_name:EditText
    lateinit var et_email:EditText
    lateinit var btn_ok:Button
    lateinit var radioGroup: RadioGroup
    lateinit var male:RadioButton
    lateinit var female:RadioButton
    lateinit var radioButton:RadioButton
    lateinit var genSelected:String
    lateinit var checkBox: CheckBox
    lateinit var btnpick: Button
     lateinit var time:TextView
    lateinit var date: TextView
    private lateinit var mPickTimeBtn : Button
    private lateinit var textView: TextView
    private lateinit var button_date_1: Button

    var cal = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_register ,container, false)
        et_name= rootView.findViewById<View>(R.id.et_name) as EditText
        et_email= rootView.findViewById<View>(R.id.et_email) as EditText
        btn_ok= rootView.findViewById<View>(R.id.btv_ok) as Button
        radioGroup= rootView.findViewById<View>(R.id.genderGroup) as RadioGroup
        male= rootView.findViewById<View>(R.id.radioM) as RadioButton
        female= rootView.findViewById<View>(R.id.radioF) as RadioButton
        checkBox= rootView.findViewById<View>(R.id.condCheck) as CheckBox
        date= rootView.findViewById<View>(R.id. date) as TextView
        mPickTimeBtn= rootView.findViewById<View>(R.id.pickTimeBtn)as Button
        textView= rootView.findViewById<View>(R.id.timeTv)as TextView
        button_date_1= rootView.findViewById<View>(R.id.button_date_1)as Button


        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

            private fun updateDateInView() {
                val myFormat = "MM/dd/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                date!!.text = sdf.format(cal.getTime())
            }
        }

        button_date_1!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

      btn_ok.setOnClickListener(this)
        return rootView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onClick(view: View) {
        var fragment: Fragment? = null
        when (view.id) {
            R.id.btv_ok -> {
                fragment = HomeFragment()
                replaceFragment(fragment)
            }
        }
    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        someFragment?.let { transaction.replace(R.id.container, it) }
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun validateName(name:String) : Boolean {
        var validName:Boolean
        if (name.length>=5 && name.length<30){
            validName= true
        }else{
            et_name.setError("Please Enter Valid Name!!")
            validName=false
        }
        return validName
    }

    fun validEmail(email:String) :Boolean{
        var validEmail:Boolean
        if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            validEmail=true
        }else{
            et_email.setError("Palease Enter Valid Email")
            validEmail=false
        }

        return validEmail
    }

//    fun valGender() {
//        if (radioGroup.checkedRadioButtonId == -1) {
//            Toast.makeText(this, "selected Gender", Toast.LENGTH_LONG).show()
//        } else {
//            val selectedOpt: Int = radioGroup!!.checkedRadioButtonId
//            radioButton = findViewById(selectedOpt)
//            println("Selected val = " + radioButton.text)
//            genSelected = radioButton.text.toString()
//            println("Selectd gen=" + genSelected)
//        }
//    }

    fun checkLicence():Boolean{
        var licenceVal:Boolean
        if(checkBox.isChecked){
            licenceVal=true
        }
        else{
            checkBox.setError("Please Accept Licence")
            licenceVal=false
        }
        return licenceVal
    }
}

class Timebtn {

}
