package com.service.parking.theparker.Controller.Activity.OfferLocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.service.parking.theparker.Controller.Activity.StartActivity;
import com.service.parking.theparker.Model.LocationPin;
import com.service.parking.theparker.R;
import com.service.parking.theparker.Services.NetworkServices;
import com.service.parking.theparker.Theparker;
import com.service.parking.theparker.View.ActivityAnimator;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class PriceAndAdditionalDetailsActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_name)
    TextView mActionBarName;

    @BindView(R.id.back_btn)
    CircleImageView mBackBtn;

    @BindView(R.id.finish_btn)
    CircleButton mFinishBtn;

    @BindView(R.id.parking_description)
    EditText mDescription;

    @BindView(R.id.price_edit_text)
    ExtendedEditText mPrice;

    @BindView(R.id.add_imageBtn)
    Button mAddImage;

    String description;
    String price;
    LocationPin locationPin = Theparker.currentLocationpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_and_additional_details);
        ButterKnife.bind(this);
        Theparker.animate(this);
        mActionBarName.setText("Price and Additional Details");

        mBackBtn.setOnClickListener(v -> {
            onBackPressed();
            Theparker.animate(this);
        });

        mFinishBtn.setOnClickListener(v -> {
            if (checkData()) {
                NetworkServices.ParkingPin.setLocationPin(locationPin);
                int position = 3;
                Intent toMain = new Intent(this, StartActivity.class);
                toMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                toMain.putExtra("position",position);
                startActivity(toMain);
            }
        });
    }

    boolean checkData() {
        description = mDescription.getText().toString();
        price = mPrice.getEditableText().toString();
        if(price.isEmpty()) {
            Toasty.error(this,"Please fill the information correctly").show();
            return false;
        } else {

            if (description.isEmpty()) {
                description = "No Description Provided by the Host";
                locationPin.setDescription(description);
                locationPin.setPrice(price);
                return true;
            } else {
                locationPin.setDescription(description);
                locationPin.setPrice(price);
                return true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Theparker.animate(this);
    }
}