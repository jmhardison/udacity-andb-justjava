/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int priceOfCoffee = 5;

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


        String orderMessage = createOrderSummary(quantity);

        displayMessage(orderMessage);


    }

    private int calculatePrice(){
        return quantity * priceOfCoffee;
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param inputNumber
     */
    private void displayQuantity(int inputNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + inputNumber);
    }

    /**
     * This method displays the given quantity value on the screen
     * @param number
     */
    private void displayPrice(int number){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Increment quantity
     * @param view
     */
    public void increment(View view){
        quantity = quantity +1;
        displayQuantity(quantity);
    }

    /**
     * Decrement quantity
     * @param view
     */
    public void decrement(View view){
        quantity = quantity -1;
        displayQuantity(quantity);
    }

    /**
     * Creates order summary string and returns it.
     * @param orderPrice
     * @return
     */
    public String createOrderSummary(int orderPrice){

        String nameOfUser = "Kaptain Kunal";
        int price = calculatePrice();

        String priceMessage = "Name: " + nameOfUser + "\n";
        priceMessage = priceMessage + "Quantity: " + orderPrice + "\n";
        priceMessage = priceMessage + "Total: $" + price + "\n";
        priceMessage = priceMessage + "Thank You!";

        return priceMessage;
    }


}