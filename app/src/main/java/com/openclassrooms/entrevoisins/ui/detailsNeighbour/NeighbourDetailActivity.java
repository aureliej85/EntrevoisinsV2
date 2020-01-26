package com.openclassrooms.entrevoisins.ui.detailsNeighbour;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    @BindView(R.id.usernameText) public TextView mUsername;
    @BindView(R.id.avatarPic) public ImageView mAvatar;
    @BindView(R.id.favButton) public ImageView mStar;
    @BindView(R.id.adressText) public TextView mAdresse;
    @BindView(R.id.telText) public TextView mTel;
    @BindView(R.id.urlText) public TextView mLink;
    @BindView(R.id.descText) public TextView mDescr;
    @BindView(R.id.bigUsernameText) public TextView mBigUsername;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mApiService = DI.getNeighbourApiService();

        ButterKnife.bind(this);

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true); // Enable the Up button
        ab.setDisplayShowTitleEnabled(false); // Disable the title

        mStar.setImageResource(R.drawable.ic_star_white_24dp);

        Intent intent = getIntent();
        mNeighbour = intent.getParcelableExtra("neighbour");

        yellowStar();
        infosNeighbour();
    }


    private void yellowStar(){
        Drawable drawable1 = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_star_yellow);
        Drawable drawable2 = mStar.getDrawable();

        if (mNeighbour.getFavorite() == true) {
            mStar.setImageDrawable(drawable1);
        } else {
            mStar.setImageDrawable(drawable2);
        }

        mStar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mApiService.nIsFavorite(mNeighbour);
                        mNeighbour.setFavorite(!mNeighbour.getFavorite());
                        yellowStar();
                    }
                }
        );
    }


    private void infosNeighbour(){
        mBigUsername.setText(mNeighbour.getName());
        mUsername.setText(mNeighbour.getName());
        Glide.with(this)
                .asBitmap()
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);
        mAdresse.setText(mNeighbour.getAdress());
        mTel.setText(mNeighbour.getTel());
        mLink.setText(mNeighbour.getUrl());
        mDescr.setText(mNeighbour.getDescription());

    }

}
