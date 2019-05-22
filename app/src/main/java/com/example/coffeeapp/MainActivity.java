package com.example.coffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    EditText name;
    CheckBox box1;
    CheckBox box2;
    CheckBox box3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


    }

    public void decrement(View V) {
        if (quantity != 0) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void increment(View V) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void submitOrder(View V) {

        box1 = findViewById(R.id.whipped_cream_checkbox);
        box2 = findViewById(R.id.chocolate_checkbox);
        box3 = findViewById(R.id.sugar_checkbox);

        StringBuffer result = new StringBuffer();
        result.append("Whipped_Cream: ").append(box1.isChecked());
        result.append("\nChocolate: ").append(box2.isChecked());
        result.append("\nSugar: ").append(box3.isChecked());

        int price = quantity * 50;
        name = findViewById(R.id.name_field);
        String message = "Thank you!";
        String nem = name.getText().toString().trim();


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"bassey@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order for " + nem);
        email.putExtra(Intent.EXTRA_TEXT, "Name: " + nem + "\n" + result.toString() + "\n" + "Quantity: " + quantity + "\n" + "Total: " + price * 5 + "Naira" + "\n" + message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose your Email: "));
    }

    public void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
