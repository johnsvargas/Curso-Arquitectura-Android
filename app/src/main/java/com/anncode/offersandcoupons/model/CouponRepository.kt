package com.anncode.offersandcoupons.model

import androidx.lifecycle.MutableLiveData

interface CouponRepository {
    fun getCounpons(): MutableLiveData<List<Coupon>>
    fun callCounponsAPI()
}