package com.visa.android.integration.checkoutsampleapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.visa.checkout.Environment;
import com.visa.checkout.VisaCheckoutSdk;
import com.visa.checkout.VisaCheckoutSdkInitListener;
import com.visa.checkout.VisaPaymentSummary;
import com.visa.checkout.widget.VisaCheckoutButton;

/**
 * Copyright © 2016 Visa. All rights reserved.
 */
public class PaymentStartActivity extends FragmentActivity {

    private static final String TAG = PaymentStartActivity.class.getSimpleName();
    public final static int VISA_CHECKOUT_REQUEST_CODE = 10102;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vxo);

        VisaCheckoutSdk.init(getApplicationContext(), Environment.SANDBOX,
            "9WQGNI3OZ7TES3BODLBL2197Te_k9ZuVx6uckjt1M-VnS9uq4", "",
            new VisaCheckoutSdkInitListener() {
                @Override public void status(int code, String message) {
                    Log.v(TAG, "Code:" + code + "  Message:" + message);
                }
            });


        ((VisaCheckoutButton)findViewById(R.id.visaCheckoutButton)).setCheckoutListener(new VisaCheckoutButton.CheckoutWithVisaListener() {
            @Override public void onClick() {
                Intent intent = VisaCheckoutSdk.getCheckoutIntent(PaymentStartActivity.this,
                    ConfigureVisaPaymentInfo.getPurchaseInfo());
                startActivityForResult(intent, VISA_CHECKOUT_REQUEST_CODE);
            }
        });
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VISA_CHECKOUT_REQUEST_CODE) {
            Log.d(TAG, "Result got back from Visa Checkout SDK");
            String msg = null;
            Bundle bundle = new Bundle();

            if (resultCode == RESULT_OK && data != null) {
                VisaPaymentSummary paymentSummary =
                    data.getParcelableExtra(VisaCheckoutSdk.INTENT_PAYMENT_SUMMARY);
                if (paymentSummary != null) {
                    msg = "Purchase Success!";
                    bundle.putParcelable(VisaCheckoutSdk.INTENT_PAYMENT_SUMMARY, paymentSummary);

                    finish();

                    // start the next activity
                    Intent intent = new Intent(this, PaymentSummaryActivity.class);
                    bundle.putString("msg", msg);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            } else if (resultCode == RESULT_CANCELED) {
                msg = "User Canceled, Result Code : " + resultCode;
            } else if (resultCode == VisaCheckoutSdk.ResultCode.RESULT_SDK_NOT_INITIALIZED) {
                msg = "Sdk not initialized  failed, Result Code : " + resultCode;
            } else if (resultCode == VisaCheckoutSdk.ResultCode.RESULT_INITIALIZED_FAILED) {
                msg = "VisaPaymentInfo validation failed, Result Code : " + resultCode;
            } else {
                msg = "Purchase failed!";
            }

            Log.d(TAG, msg);

            // show the failure reason
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
