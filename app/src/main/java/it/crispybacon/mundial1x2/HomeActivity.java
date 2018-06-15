package it.crispybacon.mundial1x2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;

public class HomeActivity extends AppCompatActivity {

    private FlagImageView mFlagImageLeft;
    private FlagImageView mFlagImageRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFlagImageLeft = findViewById(R.id.img_left_team);
        mFlagImageRight = findViewById(R.id.img_right_team);

        init();
    }



    private void init(){

        mFlagImageLeft.withFlag(R.drawable.flag_russia)
                .andText("Russia");

        mFlagImageRight.withFlag(R.drawable.flag_russia)
                .andText("Russia");
    }
}
