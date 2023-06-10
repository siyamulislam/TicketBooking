package com.example.ticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tickets extends AppCompatActivity {

    String bus, city, quantity, date, classs, phone,billTakaAc,billTakaNonAc;

    double totalTicket;
    double bograAc=550;
    double bograNonAc=350;
    double rajAc=650;
    double rajNonAc=450;
    double rangpurAc=800;
    double rangpurNonAc=600;
    double dinajpurAc=1000;
    double dinajpurNonAc=700;
    double billTaka,billDoller;
    String taka,doller;

    TextView dateTv,priceD,dst,tim;
    Button orderBtn;

    private EditText numberEt;

    Spinner bussp, citysp, quantitysp, classsp;


    private String[] selectbus;
    ArrayAdapter<String> busAdapter;

    private String[] selectcity;
    ArrayAdapter<String> cityAdapter;

    private String[] selectquantity;
    ArrayAdapter<String> quantityAdapter;

    private String[] selectclass;
    ArrayAdapter<String> classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);

        init();
        priceD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Price();
            }
        });
        dst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TD();
            }
        });
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepiker();
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bus = bussp.getSelectedItem().toString().trim();
                city = citysp.getSelectedItem().toString().trim();
                quantity = quantitysp.getSelectedItem().toString().trim();




                classs = classsp.getSelectedItem().toString();
                date = dateTv.getText().toString().trim();
                phone = numberEt.getText().toString().trim();
                if(!bus.equals("Select Bus")&&!city.equals("Select Destination")&&!classs.equals("Select Class")&&!quantity.equals("Select Quantity")&&!date.equals("")&&!phone.equals("")){
                    if(phone.length()>10 && phone.length()<12){
                        order();
                    }
                    else {
                        Toast.makeText(Tickets.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Tickets.this, "Something is not define", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void datepiker() {

        DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;
                        String currentDate =  day+ "/" + month + "/" + year;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Date date = null;

                        try {
                            date = dateFormat.parse(currentDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateTv.setText(dateFormat.format(date));
                        long dateInmilis = date.getTime();

                    }
                };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Tickets.this, dateSetListener, year, month, day);
        datePickerDialog.show();

    }

    private void init() {
        orderBtn=findViewById(R.id.orderBtn);

        numberEt = findViewById(R.id.numberEt);
        dateTv = findViewById(R.id.dateTv);
        priceD = findViewById(R.id.priceDamu);
        dst = findViewById(R.id.dstD);
        tim = findViewById(R.id.timD);


        citysp = findViewById(R.id.districtsp);
        selectcity = getResources().getStringArray(R.array.Select_district);
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectcity);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        citysp.setAdapter(cityAdapter);

        classsp = findViewById(R.id.classsp);
        selectclass = getResources().getStringArray(R.array.Select_class);
        classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectclass);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        classsp.setAdapter(classAdapter);

        quantitysp = findViewById(R.id.quantitysp);
        selectquantity = getResources().getStringArray(R.array.Select_quantity);
        quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectquantity);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        quantitysp.setAdapter(quantityAdapter);

        bussp = findViewById(R.id.bussp);
        selectbus = getResources().getStringArray(R.array.Select_bus);
        busAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectbus);
        busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bussp.setAdapter(busAdapter);


    }





    public void Price(){
        city = citysp.getSelectedItem().toString().trim();
        classs = classsp.getSelectedItem().toString();
       if (city.equals("Select Destination")|| classs.equals("Select Class")) {
            Toast.makeText(getApplicationContext(),"Select Destination & Class",Toast.LENGTH_LONG).show();

           }

       else{
           if(city.equals("Bogra")){
               if (classs.equals("AC")) {
                   String Price = Double.toString(bograAc);
                   priceD.setText("Price(1p): "+Price);
               }
               else if (classs.equals("Non-AC")) {
                   String Price = Double.toString(bograNonAc);
                   priceD.setText("Price(1p): "+Price);
               }

           }
           if(city.equals("Rajshahi")){
               if (classs.equals("AC")) {
                   String Price = Double.toString(rajAc);
                   priceD.setText("Price(1p): "+Price);
               }
               else if (classs.equals("Non-AC")) {
                   String Price = Double.toString(rajNonAc);
                   priceD.setText("Price/: "+Price);
               }

           }

           if(city.equals("Rangpur")){
               if (classs.equals("AC")) {
                   String Price = Double.toString(rangpurAc);
                   priceD.setText("Price(1p): "+Price);
               }
               else if (classs.equals("Non-AC")) {
                   String Price = Double.toString(rangpurNonAc);
                   priceD.setText("Price(1p): "+Price);
               }

           }

           if(city.equals("Dinajpur")){
               if (classs.equals("AC")) {
                   String Price = Double.toString(dinajpurAc);
                   priceD.setText("Price(1p): "+Price);
               }
               else if (classs.equals("Non-AC")) {
                   String Price = Double.toString(dinajpurNonAc);
                   priceD.setText("Price(1p): "+Price);
               }

           }





       }


    }


    public void TD(){
        city = citysp.getSelectedItem().toString().trim();
        classs = classsp.getSelectedItem().toString();
        if (city.equals("Select District")) {
            Toast.makeText(getApplicationContext(),"Select District & Class",Toast.LENGTH_LONG).show();

        }

        else {
            if (city.equals("Bogra")) {

                    String Distance = "194km";
                    String Time = "19h:24m";
                    dst.setText("Distance: "+Distance);
                    tim.setText("Time: "+Time);

            }
            if (city.equals("Rajshahi")) {
                String Distance = "246km";
                String Time = "1d:0h:36m";
                dst.setText("Distance: "+Distance);
                tim.setText("Time: "+Time);

            }
            if (city.equals("Rangpur")) {
                String Distance = "301km";
                String Time = "1d:6h:6m";
                dst.setText("Distance: "+Distance);
                tim.setText("Time: "+Time);

            }
            if (city.equals("Dinajpur")) {
                String Distance = "334km";
                String Time = "1d:9h:24m";
                dst.setText("Distance: "+Distance);
                tim.setText("Time: "+Time);

            }


        }


        }






    public void order() {






        if (city.equals("Bogra")) {
            //Integer.parseInt(myString);
            totalTicket=Integer.parseInt(quantity);


            if(classs.equals("AC")){

                billTaka=bograAc*totalTicket;

                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }
            else {
                billTaka=bograNonAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }



            String Distance = "194km";
            String time = "19h:24m";


            Intent intent=new Intent(Tickets.this,YourPurchase.class);
            intent.putExtra("bus",bus);
            intent.putExtra("classs",classs);
            intent.putExtra("city",city);
            intent.putExtra("time",time);
            intent.putExtra("distance",Distance);
            intent.putExtra("date",date);
            intent.putExtra("phone",phone);
            intent.putExtra("quantity",quantity);
            intent.putExtra("billTaka",taka);
            intent.putExtra("billUsd",doller);

            startActivity(intent);
        }

        else if (city.equals("Rajshahi")) {
            totalTicket=Integer.parseInt(quantity);


            if(classs.equals("AC")){

                billTaka=rajAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }
            else {
                billTaka=rajNonAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }



            String Distance = "246km";
            String time = "1d:0h:36m";


            Intent intent=new Intent(Tickets.this,YourPurchase.class);
            intent.putExtra("bus",bus);
            intent.putExtra("classs",classs);
            intent.putExtra("city",city);
            intent.putExtra("time",time);
            intent.putExtra("distance",Distance);
            intent.putExtra("date",date);
            intent.putExtra("phone",phone);
            intent.putExtra("quantity",quantity);
            intent.putExtra("billTaka",taka);
            intent.putExtra("billUsd",doller);

            startActivity(intent);

        } else if (city.equals("Rangpur")) {
            totalTicket=Integer.parseInt(quantity);


            if(classs.equals("AC")){

                billTaka=rangpurAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }
            else {
                billTaka=rangpurNonAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }



            String Distance = "301km";
            String time = "1d:6h:6m";


            Intent intent=new Intent(Tickets.this,YourPurchase.class);
            intent.putExtra("bus",bus);
            intent.putExtra("classs",classs);
            intent.putExtra("city",city);
            intent.putExtra("time",time);
            intent.putExtra("distance",Distance);
            intent.putExtra("date",date);
            intent.putExtra("phone",phone);
            intent.putExtra("quantity",quantity);
            intent.putExtra("billTaka",taka);
            intent.putExtra("billUsd",doller);

            startActivity(intent);
        }

        else if (city.equals("Dinajpur")) {
            totalTicket=Integer.parseInt(quantity);

            if(classs.equals("AC")){
                billTaka=dinajpurAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }
            else {
                billTaka=dinajpurNonAc*totalTicket;
                billDoller=billTaka/85;
                billDoller = billDoller*100;
                billDoller = Math.round(billDoller);
                billDoller = billDoller /100;
                taka=String.valueOf(billTaka);
                doller=String.valueOf(billDoller);

            }


            String Distance = "334km";
            String time = "1d:9h:24m";


            Intent intent=new Intent(Tickets.this,YourPurchase.class);
            intent.putExtra("bus",bus);
            intent.putExtra("classs",classs);
            intent.putExtra("city",city);
            intent.putExtra("time",time);
            intent.putExtra("distance",Distance);
            intent.putExtra("date",date);
            intent.putExtra("phone",phone);
            intent.putExtra("quantity",quantity);
            intent.putExtra("billTaka",taka);
            intent.putExtra("billUsd",doller);
            startActivity(intent);



        }


    }








}






