package com.example.ticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class YourPurchase extends AppCompatActivity {
    private String bus,classs,city,time,distancest,date,taka,doller,number,quantity;
    private Button btncall, btnsms;


    private TextView busTv,classTv,cityTv,timetv,distancetv,dateTv,billTakatv,billDollerTv,quantityTv,phoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_purchase);

        setTitle("Your Purchase");

        init();
        showData();
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms();
            }
        });
    }



    private void showData() {
        bus=getIntent().getStringExtra("bus");
        busTv.setText(bus);
        classs=getIntent().getStringExtra("classs");
        classTv.setText(classs);
        city=getIntent().getStringExtra("city");
        cityTv.setText(city);
        time=getIntent().getStringExtra("time");
        timetv.setText(time);
        distancest=getIntent().getStringExtra("distance");
        distancetv.setText(distancest);
        date=getIntent().getStringExtra("date");
        dateTv.setText(date);
        taka=getIntent().getStringExtra("billTaka");
        billTakatv.setText(taka);
        doller=getIntent().getStringExtra("billUsd");
        billDollerTv.setText(doller);
        number=getIntent().getStringExtra("phone");
        phoneTv.setText(number);
        quantity=getIntent().getStringExtra("quantity");
        quantityTv.setText(quantity);


    }

    private void init() {

        busTv=findViewById(R.id.busTv);
        classTv=findViewById(R.id.classTv);
        cityTv=findViewById(R.id.cityTv);
        distancetv=findViewById(R.id.distIv);
        timetv=findViewById(R.id.timeIv);
        dateTv=findViewById(R.id.dateTv);
        billTakatv=findViewById(R.id.takaTv);
        billDollerTv=findViewById(R.id.dollerTv);
        quantityTv=findViewById(R.id.quantityTv);
        phoneTv=findViewById(R.id.numberTv);
        btncall=findViewById(R.id.callid);
        btnsms=findViewById(R.id.smsid);

    }

    public void Confirm(View view) {
        Toast.makeText(this, "Ticket Order is confirm", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(YourPurchase.this,Tickets.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void call(){

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+number));
        startActivity(callIntent);
    }
    private void sms(){

        Toast.makeText(getApplicationContext(),"For Safety issue SMS is Trun off!",Toast.LENGTH_LONG).show();
    }




}
