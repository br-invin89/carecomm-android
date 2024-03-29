package com.merculia.carecomm.Adapters;

import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.merculia.carecomm.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarAdapter extends ArrayAdapter<Date> {
    // for view inflation
    private LayoutInflater inflater;
    private  HashSet<Date> eventDays;
    public CalendarAdapter(Context context, ArrayList<Date> days)
    {
        super(context, R.layout.custom_calendar_day, days);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        // day in question
        Calendar calendar = Calendar.getInstance();
        Date date = getItem(position);
        calendar.setTime(date);
        int sunday = calendar.get(Calendar.DAY_OF_WEEK);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // today
        Date today = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(today);

        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.custom_calendar_day, parent, false);
        // clear styling
        TextView txtDate = view.findViewById(R.id.text_date);
        txtDate.setTypeface(null, Typeface.NORMAL);
        txtDate.setTextColor(Color.BLACK);

        if (sunday == 1){
            txtDate.setTextColor(Color.RED);

        }
        if (month != calendarToday.get(Calendar.MONTH) || year != calendarToday.get(Calendar.YEAR)) {
            // if this day is outside current month, grey it out
            txtDate.setTextColor(Color.parseColor("#E0E0E0"));
        } else if (day == calendarToday.get(Calendar.DATE)) {
            // if it is today, set it to blue/bold

            txtDate.setTextColor(Color.WHITE);
            view.setBackgroundResource(R.drawable.ractangle_row_tan);
        }

        // set text
        txtDate.setText(String.valueOf(calendar.get(Calendar.DATE)));

        return view;
    }
}

