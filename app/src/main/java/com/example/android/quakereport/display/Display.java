package com.example.android.quakereport.display;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.quakereport.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Display extends AppCompatActivity {
    private static final String LOCATION_SEPARATOR = " of ";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diplay_whether);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        Double magnitude=intent.getDoubleExtra("magnitude",0.0);
        String originalLocation=intent.getStringExtra("location");
        long date=intent.getLongExtra("time",0);
        final String urll=intent.getStringExtra("URL");

        TextView mag=(TextView)findViewById(R.id.textView3);
        TextView tim=(TextView)findViewById(R.id.time2);
        TextView dat=(TextView)findViewById(R.id.date2);
        TextView dir=(TextView)findViewById(R.id.direction2);
        TextView loc=(TextView)findViewById(R.id.location2);
        Button moreInfo=(Button)findViewById(R.id.button);

        String formattedMagnitude = formatMagnitude(magnitude);
        mag.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        int magnitudeColor = getMagnitudeColor(magnitude);
        magnitudeCircle.setColor(magnitudeColor);

        String primaryLocation;
        String locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = "Near the";
            primaryLocation = originalLocation;
        }
        dir.setText(locationOffset);
        loc.setText(primaryLocation);

        //Date
        Date dateObject=new Date(date);
        String formattedDate = formatDate(dateObject);
        dat.setText(formattedDate);

        //Time
        String formattedTime = formatTime(dateObject);
        tim.setText(formattedTime);

        //More Info..
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Display.this,Browser.class);
                intent1.putExtra("given_url",urll);
                startActivity(intent1);
            }
        });

    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return getResources().getColor(magnitudeColorResourceId);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
