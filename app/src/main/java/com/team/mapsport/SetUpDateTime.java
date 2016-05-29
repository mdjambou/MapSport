package com.team.mapsport;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.team.mapsport.common.DateTimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetUpDateTime {

    final  static  void setDateTime (Context context, Activity activ, final Button mDateDisplay){
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(context);
        // Inflate the root layout
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) activ.getLayoutInflater().inflate(R.layout.date_time_dialog, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
        // Check is system is set to use 24h time (this doesn't seem to work as expected though)
        //final String timeS = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.System.TIME_12_24);
        final boolean is24h = true;

        // Update demo TextViews when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mDateTimePicker.clearFocus();
                // TODO Auto-generated method stub
                String monthFinal = "", dayFinal ="";
                String timePick="";
                String myDate = mDateTimePicker.get(Calendar.YEAR) + "/" + (mDateTimePicker.get(Calendar.MONTH) + 1) + "/"
                        + mDateTimePicker.get(Calendar.DAY_OF_MONTH);

                SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd");
                try{
                    Date  dt1=format1.parse(myDate);
                    monthFinal = (String) DateFormat.format("MMM", dt1);
                    dayFinal = (String) DateFormat.format("dd", dt1);
                }catch (ParseException ex){

                }

                //String datePick =  mDateTimePicker.get(Calendar.DAY_OF_MONTH)+ " "+monthFinal;
                String datePick = dayFinal +" "+ monthFinal;
                if (mDateTimePicker.is24HourView()) {
                    timePick = mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE);
                } else {
                    timePick= mDateTimePicker.get(Calendar.HOUR) + ":" + mDateTimePicker.get(Calendar.MINUTE) + " "
                            + (mDateTimePicker.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
                }
                mDateDisplay.setText(datePick+" à "+timePick);
                mDateTimeDialog.dismiss();
            }
        });

        // Cancel the dialog when the "Cancel" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimeDialog.cancel();
            }
        });

        // Reset Date and Time pickers when the "Reset" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimePicker.reset();
            }
        });

        // Setup TimePicker
        mDateTimePicker.setIs24HourView(is24h);
        // No title on the dialog window
        mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set the dialog content view
        mDateTimeDialog.setContentView(mDateTimeDialogView);
        // Display the dialog
        mDateTimeDialog.show();
    }

}
