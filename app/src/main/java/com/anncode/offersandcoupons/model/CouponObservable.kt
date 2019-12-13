package com.anncode.offersandcoupons.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class CouponObservable: BaseObservable() {

    private var couponRepository = CouponRepositoryImpl()
    // repositorio
    fun callCoupons(){
      couponRepository.callCounponsAPI()
    }

    //ViewModel
    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponRepository.getCounpons()
    }
}