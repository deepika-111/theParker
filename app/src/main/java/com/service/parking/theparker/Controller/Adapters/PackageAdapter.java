package com.service.parking.theparker.Controller.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.service.parking.theparker.Model.PackageModel;
import com.service.parking.theparker.R;

import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {

    private List<PackageModel>  packagelist;
    private Context con;
    private Object packageStatus;

    public PackageAdapter(List<PackageModel> packagelist){
        this.packagelist= packagelist;
    }
    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        con=parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_view_layout,parent,false);
        return new PackageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PackageViewHolder myViewHolder, final int i) {
        final PackageModel model = packagelist.get(i);

        //Log.d("Firebase : ",model.package_name+" "+model.bikes_selected+" "+model.cars_selected +" "+model.price);

        myViewHolder.mPackage_name.setText(model.getPackage_name());
        myViewHolder.mNo_of_space.setText(model.getCars_selected());
        myViewHolder.mPrice.setText("₹"+model.getPrice());

        myViewHolder.buy_Btn.setOnClickListener(v -> {

        });

    }

    @Override
    public int getItemCount() {
        return packagelist.size();
    }

    class PackageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.package_name)
        TextView mPackage_name;

        @BindView(R.id.package_price)
        TextView mPrice;

        @BindView(R.id.no_of_spaces)
        TextView mNo_of_space;

        @BindView(R.id.buy_circular_btn)
        CircleButton buy_Btn;

        PackageViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }
    }
}