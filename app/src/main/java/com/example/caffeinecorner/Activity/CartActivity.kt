    package com.example.caffeinecorner.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caffeinecorner.Adapter.CartAdapter
import com.example.caffeinecorner.Helper.ChangeNumberItemsListener
import com.example.caffeinecorner.Helper.ManagmentCart
import com.example.caffeinecorner.R
import com.example.caffeinecorner.databinding.ActivityCartBinding

    class CartActivity : BaseActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var managment:ManagmentCart
    private var tax:Double=0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managment= ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

        private fun initCartList() {
            with(binding){
                cartView.layoutManager = LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
                cartView.adapter = CartAdapter(managment.getListCart(),this@CartActivity, object :
                    ChangeNumberItemsListener{


                    override fun onChanged() {
                        calculateCart()
                    }

                })
            }
        }

        private fun setVariable() {
            binding.backBtn.setOnClickListener {
                finish()
            }
        }

        private fun calculateCart() {
            val PercentTax = 0.02
            val delivery = 15.0
            tax   =  Math.round((managment.getTotalFee()*PercentTax)*100)/100.0
            val total = Math.round((managment.getTotalFee()+tax+delivery)*100)/100
            val itemTotal = Math.round(managment.getTotalFee()*100)/100

            with(binding){
                totalFeeTxt.text = "$$itemTotal"
                taxTxt.text = "$$tax"
                deliveryTxt.text = "$$delivery"
                totalTxt.text = "$$total"
            }
        }
    }