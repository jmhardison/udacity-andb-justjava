/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int priceOfCoffee = 5;
    int priceOfChocolate = 2;
    int priceOfWhippedCream = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     * @param view
     */
    public void submitOrder(View view) {



        CheckBox whippedTextView = (CheckBox) findViewById(R.id.whiptopping_check_box);
        boolean whippedChecked = whippedTextView.isChecked();

        CheckBox chocolateTextView = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean chocolateChecked = chocolateTextView.isChecked();

        EditText usersNameEditText = (EditText) findViewById(R.id.nameofuser);

        String orderMessage = createOrderSummary(quantity, whippedChecked, chocolateChecked, usersNameEditText.getText().toString());

        composeEmail(getString(R.string.composeemailsubject, usersNameEditText.getText().toString()), orderMessage);


    }

    private int calculatePrice(boolean chocolate, boolean whippedcream){
        int coffeePrice = priceOfCoffee;

        if(chocolate){
            coffeePrice += priceOfChocolate;
        }
        if(whippedcream){
            coffeePrice += priceOfWhippedCream;
        }

        coffeePrice = coffeePrice * quantity;

        return coffeePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param inputNumber
     */
    private void displayQuantity(int inputNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + inputNumber);
    }


    public void displayToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    /**
     * Increment quantity
     * @param view
     */
    public void increment(View view){
        if(quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else{
            //add toast here.
            displayToast(getString(R.string.morethan100toast));
            return;
        }
    }

    /**
     * Decrement quantity
     * @param view
     */
    public void decrement(View view){
        if(quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else{
            //toast for oops
            displayToast(getString(R.string.lessthan1toast));
            return;
        }
    }

    /**
     * Creates order summary string and returns it.
     * @param orderPrice
     * @return
     */
    public String createOrderSummary(int orderQty, boolean whippedTopping, boolean chocolateTopping, String nameOfUser){


        int price = calculatePrice(chocolateTopping, whippedTopping);

        String priceMessage = getString(R.string.composebodyline1, nameOfUser) + "\n";
        priceMessage = priceMessage + getString(R.string.composebodyline2, whippedTopping) + "\n";
        priceMessage = priceMessage + getString(R.string.composebodyline3, chocolateTopping) + "\n";
        priceMessage = priceMessage + getString(R.string.composebodyline4, orderQty) + "\n";
        priceMessage = priceMessage + getString(R.string.composebodyline5, price) + "\n";
        priceMessage = priceMessage + getString(R.string.composebodyline6);

        return priceMessage;
    }

    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}