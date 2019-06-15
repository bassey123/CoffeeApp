package com.example.coffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    EditText name;
    TextView price;
    TextView quantityText;
    CheckBox box1;
    CheckBox box2;
    CheckBox box3;
    Button sub;
    Button plus;
    Button minus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        sub = findViewById(R.id.submit);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        box1  = findViewById(R.id.whipped_cream_checkbox);
        box2 = findViewById(R.id.chocolate_checkbox);
        box3 = findViewById(R.id.sugar_checkbox);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                submitOrder();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity == 100) {
                    Toast.makeText(MainActivity.this, "You cannot have more than 100 Coffees", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity = quantity + 1;

                displayPrice();
                display(quantity);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1) {
                    Toast.makeText(MainActivity.this, "You cannot have less than 1 Coffee", Toast.LENGTH_SHORT).show();
                    return;
                } 
                quantity = quantity - 1;
                displayPrice();
                display(quantity);

            }
        });

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrice();
            }
        });

        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrice();
            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrice();
            }
        });

    }

    public void display(int number) {
        quantityText = findViewById(R.id.quantity_text_view);
        quantityText.setText(number + "");
    }

    public void displayPrice() {

        price = findViewById(R.id.amt);
        int pr = 0;
        String n = " Naira";

        if (box1.isChecked()) {
            pr = quantity * 50 + 30;
            price.setText(String.valueOf(pr) + n);
        } else if (box2.isChecked()) {
            pr = quantity * 50 + 20;
            price.setText(String.valueOf(pr) + n);
        } else if (box3.isChecked()) {
            pr = quantity * 50 + 10;
            price.setText(String.valueOf(pr) + n);
        } else {
            pr = quantity * 50;
            price.setText(String.valueOf(pr) + n);
        }
    }

    public void submitOrder() {

        StringBuffer result = new StringBuffer();
        result.append("Whipped_Cream: ").append(box1.isChecked());
        result.append("\nChocolate: ").append(box2.isChecked());
        result.append("\nSugar: ").append(box3.isChecked());


        price = findViewById(R.id.amt);
        String p = price.getText().toString();

        name = findViewById(R.id.name_field);
        String message = "Thank you!";
        String nem = name.getText().toString();


//        Intent email = new Intent(Intent.ACTION_SEND);
//        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"bassey@gmail.com"});
//        email.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order for " + nem);
//        email.putExtra(Intent.EXTRA_TEXT, "Name: " + nem + "\n" + result.toString() + "\n" + "Quantity: " + quantity + "\n" + "Price: " + p + "\n" + message);
//        email.setType("message/rfc822");
//        startActivity(Intent.createChooser(email, "Choose your Email: "));

        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); //only email apps should handle this
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"bassey@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order for " + nem);
        email.putExtra(Intent.EXTRA_TEXT, "Name: " + nem + "\n" + result.toString() + "\n" + "Quantity: " + quantity + "\n" + "Price: " + p + "\n" + message);
//        Check to see if there is an email app to handle the intent
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }
    }
}
