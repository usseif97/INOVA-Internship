package com.inovaeg.androidtrainingpharos2020.finaldemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.inovaeg.androidtrainingpharos2020.R;

public class NetflixMovieOverviewActivity extends AppCompatActivity {

    private Boolean isAddedToMyList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix_movie_overview);

        final Button myListButton = findViewById(R.id.btn_movie_overview_my_list);

        myListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAddedToMyList = !isAddedToMyList;
                int drawableId;
                if (isAddedToMyList)
                    drawableId = R.drawable.ic_check;
                else
                    drawableId = R.drawable.ic_add;

                myListButton.setCompoundDrawablesWithIntrinsicBounds(null,
                        ContextCompat.getDrawable(NetflixMovieOverviewActivity.this,
                                drawableId), null, null);

            }
        });
    }
}