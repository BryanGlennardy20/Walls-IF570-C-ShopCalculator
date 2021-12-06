package id.ac.umn.walls;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditActivity extends AppCompatActivity {
    EditText addname_input, addprice_input;
    Button edit_confirm, delete_confirm;
    ImageView itemImg;

    String itemId, itemName, itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        addname_input = findViewById(R.id.addname_input2);
        addprice_input = findViewById(R.id.addprice_input2);
        edit_confirm = findViewById(R.id.edit_confirm);
        itemImg = findViewById(R.id.itemImg2);
        delete_confirm = findViewById(R.id.delete_confirm);

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


        edit_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBmain dBmain = new DBmain(EditActivity.this);

                //panggil method
                itemName = addname_input.getText().toString().trim();
                itemPrice = addprice_input.getText().toString().trim();
                dBmain.editData(itemId, itemName, itemPrice, );

                startActivity(new Intent(getApplicationContext()
                        , MainActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        delete_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    // Untuk melakukan set data ke edit text
    void getAndSetIntentData(){
        if(getIntent().hasExtra("itemId") && getIntent().hasExtra("itemName") && getIntent().hasExtra("itemPrice")){

            // get intent
            itemId = getIntent().getStringExtra("itemId");
            itemName = getIntent().getStringExtra("itemName");
            itemPrice = getIntent().getStringExtra("itemPrice");

            //set intent to edit text
            addname_input.setText(itemName);
            addprice_input.setText(itemPrice);

        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    // Dialog Delete
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + itemName + " ?");
        builder.setMessage("Are you sure you want to delete " + itemName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBmain dBmain = new DBmain(EditActivity.this);
                dBmain.deleteData(itemId);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}