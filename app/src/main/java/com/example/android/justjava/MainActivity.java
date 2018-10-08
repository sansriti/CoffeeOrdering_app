package com.example.android.justjava;


/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String Name = editText.getText().toString();
        boolean checked_whipped_cream = ((CheckBox) findViewById(R.id.whipped_cream)).isChecked();
        boolean checked_chocolate = ((CheckBox) findViewById(R.id.chocolate)).isChecked();
        int Price = calculatePrice(checked_whipped_cream, checked_chocolate);
        String priceMessage = createOrderSummary(Price, checked_chocolate, checked_whipped_cream, Name);
        displayMessage(priceMessage);
    }

    public String createOrderSummary(int n, boolean choco, boolean cream, String Name) {

        String s = "Name:" + Name + "\nAdd whipped cream ? " + cream + "\nAdd Chocolate ? " + choco + "\nOrder:" + quantity + "\nTotal:" + n + "\nThank You!";
        return (s);
    }

    public int calculatePrice(boolean checked_whipped_cream, boolean checked_chocolate) {
        int Price = 5;
        if (checked_whipped_cream == true) {
            Price = Price + 1;
        }
        if (checked_chocolate) {
            Price = Price + 2;
        }
        return (quantity * Price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Context context = getApplicationContext();
            CharSequence text = "you can't go beyond 100";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
}

   public void decrement(View view){
       if(quantity==1){Context context = getApplicationContext();
           CharSequence text = "you can't go beyond one";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       return;}
       quantity=  quantity-1;
       display(quantity);          }
   public void name(View view){
    EditText setName = (EditText)findViewById(R.id.edit_text);
    setName.setText("", TextView.BufferType.EDITABLE);
   }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        //TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
       // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT," great");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
       // priceTextView.setText(message);
    }


}
