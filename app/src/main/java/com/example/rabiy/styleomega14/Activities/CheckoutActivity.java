package com.example.rabiy.styleomega14.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Models.Cart;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private EditText etFName;
    private EditText etLName;
    private EditText etAddress;
    private EditText etPhoneNo;
    private EditText etCardNo;
    private EditText etCardExp;
    private EditText etCardCode;
    private TextView tvItemCount;
    private TextView tvTotal;
    private Button btncheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Checkout");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        etFName=findViewById(R.id.checkoutFname_et);
        etLName=findViewById(R.id.checkoutLname_et);
        etAddress=findViewById(R.id.checkoutAddress_et);
        etPhoneNo=findViewById(R.id.checkoutPhone_et);
        etCardNo=findViewById(R.id.checkoutcard_et);
        etCardExp=findViewById(R.id.checkoutExpdate_et);
        etCardCode=findViewById(R.id.checkoutSecCode_et);

        tvItemCount=findViewById(R.id.checkoutItems_tv);
        tvTotal=findViewById(R.id.TotalAmount_tv);

        btncheckout=findViewById(R.id.checkout_btn);

        Intent intent=getIntent();
        final int total=intent.getExtras().getInt("orderTotal");
        int itemcount=intent.getExtras().getInt("itemCount");

        tvTotal.setText("Rs."+Integer.toString(total)+".00");
        tvItemCount.setText("Items : "+Integer.toString(itemcount));

        String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
        List<User> users=User.listAll(User.class);
        for (User user:users) {
            if (user.getEmail().equals(userEmail)) {
                etFName.setText(user.getfName());
                etLName.setText(user.getlName());
                etPhoneNo.setText(user.getPhoneNo());

                if(user.getPayment_cardNo()!=null) {
                    etAddress.setText(user.getAddress());
                    etCardNo.setText(user.getPayment_cardNo());
                    etCardExp.setText(user.getPayment_cardExp());
                    etCardCode.setText(Integer.toString(user.getPayment_cardCode()));
                }
            }
        }

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address=etAddress.getText().toString();
                String card=etCardNo.getText().toString();
                String cardExp=etCardExp.getText().toString();

                if(address.equals("")||card.equals("")||cardExp.equals("")|| etCardCode.getText().toString().equals(""))
                {
                    Toast.makeText(CheckoutActivity.this, "Please Fill in All the Required Fields",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                    List<User> users=User.listAll(User.class);
                    for (User user:users) {
                        if (user.getEmail().equals(userEmail)) {
                            Cart cart = user.getCart();

                            if (user.getCart() == null) {
                                Toast.makeText(CheckoutActivity.this, "Your Cart Is Empty",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                if (user.getCart().getStatus().equals("pending")) {
                                    long cartid = cart.getId();
                                    Cart updateCart = Cart.findById(Cart.class, cartid);
                                    updateCart.setStatus("purchased");

                                    //get current date & time
                                    Date c = Calendar.getInstance().getTime();
                                    System.out.println("Current time => " + c);

                                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                                    String formattedDate = df.format(c);

                                    updateCart.setDate(formattedDate);
                                    updateCart.setTotalAmount(total);
                                    updateCart.save();

                                    int code=Integer.parseInt(etCardCode.getText().toString());

                                    long userid=user.getId();
                                    User updateUser=User.findById(User.class,userid);
                                    updateUser.setAddress(address);
                                    updateUser.setPayment_cardNo(card);
                                    updateUser.setPayment_cardExp(cardExp);
                                    updateUser.setPayment_cardCode(code);
                                    updateUser.save();

                                    Toast.makeText(CheckoutActivity.this, "Purchase Successful!", Toast.LENGTH_SHORT).show();

                                    finish();
                                    Intent intent=new Intent(CheckoutActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }



            }
        });





    }
}
