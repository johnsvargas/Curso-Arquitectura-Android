package com.anncode.offersandcoupons.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null
    private var context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setupBindings(savedInstanceState)

    }
    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.model= couponViewModel
        setupListUpdate()
    }

    fun setupListUpdate(){
        //CallCoupons
        couponViewModel?.callCoupons()
        //getCoupons
        couponViewModel?.getCoupons()?.observe(this, Observer {
            Log.w("Coupon", it[0].title)
            couponViewModel?.setCouponsInRecyclerAdapter(it)
        })
        setupListClick()
    }

    fun setupListClick() {
        couponViewModel?.getCouponSelected()?.observe(this,
            Observer {coupon: Coupon ->
                Log.i("CLICK", coupon.title)
                val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
                showPhotoIntent.putExtra("COUPON", coupon)
                context.startActivity(showPhotoIntent)
            })
    }
}
