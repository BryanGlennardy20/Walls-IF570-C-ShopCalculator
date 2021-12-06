package id.ac.umn.walls;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class cartActivity extends AppCompatActivity {
    TextView itemName, itemPrice;
    Button addToCartBtn;
    ImageView itemImg;

    private Uri imageUri;

    String itemId, itemName2, itemPrice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemName = findViewById(R.id.itemNameCart);
        itemPrice = findViewById(R.id.itemPriceCart);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        itemImg = findViewById(R.id.cartImage);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.add:
                        startActivity(new Intent(getApplicationContext()
                                , addActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });

        //panggil data
        getAndSetIntentData();

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext()
                        , MainActivity.class));
                overridePendingTransition(0, 0);
            }
        });
    }

    // Untuk melakukan set data ke edit text
    void getAndSetIntentData(){
        if(getIntent().hasExtra("itemId") && getIntent().hasExtra("itemName") && getIntent().hasExtra("itemPrice") && getIntent().hasExtra("itemImage")){

            // get intent
            itemId = getIntent().getStringExtra("itemId");
            itemName2 = getIntent().getStringExtra("itemName");
            itemPrice2 = getIntent().getStringExtra("itemPrice");
            imageUri = Uri.parse(getIntent().getStringExtra("itemImage"));

            //set intent to edit text
            itemName.setText(itemName2);
            itemPrice.setText(itemPrice2);
            itemImg.setImageURI(imageUri);


        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}