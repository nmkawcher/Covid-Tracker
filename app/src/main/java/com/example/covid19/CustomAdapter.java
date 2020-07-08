package com.example.covid19;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private List<CountryModel> countryModelList;

    public CustomAdapter(Context context, List<CountryModel> countryModelList) {
        this.context = context;
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Glide.with(context).load(countryModelList.get(position).getFlag()).into(holder.flagImage);
        holder.countyName.setText(countryModelList.get(position).getCountry());

        holder.countyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,countryModelList.get(holder.getAdapterPosition()).getCountry(),Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(context,DetailsActivity.class);
                Log.e("tag", "onClick:holder positon:  "+holder.getAdapterPosition() );
                intent.putExtra("value",holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView countyName;
        private ImageView flagImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countyName=itemView.findViewById(R.id.tvCountryName);
            flagImage=itemView.findViewById(R.id.imageFlag);
        }
    }

    public void filterList(List<CountryModel> filterdNames) {
        this.countryModelList = filterdNames;
        notifyDataSetChanged();
    }
}
