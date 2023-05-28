package com.example.nationalityapi

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.nationalityapi.models.Countries

class MyViewModel : ViewModel() {
    var countries: Countries? = null
    var rvState: Parcelable? = null
}