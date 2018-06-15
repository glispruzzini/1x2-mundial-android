package it.crispybacon.mundial1x2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import java.util.Date;

import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

public class HomeActivity extends AppCompatActivity {

    private FlagImageView mFlagImageLeft;
    private FlagImageView mFlagImageRight;
    private DateTextView mDateTextView;
    private AppCompatTextView mHourTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFlagImageLeft = findViewById(R.id.img_left_team);
        mFlagImageRight = findViewById(R.id.img_right_team);
        mDateTextView = findViewById(R.id.text_date);
        mHourTextView = findViewById(R.id.text_hour);

        init();
    }



    private void init(){

        mFlagImageLeft.withFlag(R.drawable.flag_russia)
                .andText("Russia");

        mFlagImageRight.withFlag(R.drawable.flag_russia)
                .andText("Russia");

        mDateTextView.setDate(new Date());
        mHourTextView.setText("20:00");
    }
}
