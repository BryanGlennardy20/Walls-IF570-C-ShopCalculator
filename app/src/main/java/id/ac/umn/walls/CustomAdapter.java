package id.ac.umn.walls;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id, item_name, item_price, item_image;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList item_name, ArrayList item_price, ArrayList item_image){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_image = item_image;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemId, itemName, itemPrice;
        ImageView itemImage;
        Button editHomeBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemId = itemView.findViewById(R.id.itemId);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImg);
            editHomeBtn = itemView.findViewById(R.id.editHomeBtn);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_itemhome, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemId.setText(String.valueOf(id.get(position)));
        holder.itemName.setText(String.valueOf(item_name.get(position)));
        holder.itemPrice.setText(String.valueOf(item_price.get(position)));
        holder.itemImage.setImageURI(Uri.parse(String.valueOf(item_image.get(position))));

        holder.editHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("itemId", String.valueOf(id.get(position)));
                intent.putExtra("itemName", String.valueOf(item_name.get(position)));
                intent.putExtra("itemPrice", String.valueOf(item_price.get(position)));
                intent.putExtra("itemImage", String.valueOf(item_image.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

}