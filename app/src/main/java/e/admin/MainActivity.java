package e.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner yearS;
    private Spinner monthS;
    private Spinner dayS;
    private static final int startY=1950;
    private EditText nameet;
    public static final String INPUT_NAME = "InputName";
    public static final String RESULT_NUMBER = "ResultNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearS = (Spinner)findViewById(R.id.yearSp);
        monthS = (Spinner)findViewById(R.id.monthSp);
        dayS = (Spinner)findViewById(R.id.daySp);
        nameet = (EditText)findViewById(R.id.nameET);

        yearS.setAdapter(createYearAdapter());
        monthS.setAdapter(createMonthAdapter());
        dayS.setAdapter(createDayAdapter());

        yearS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayS.setAdapter(createDayAdapter());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayS.setAdapter(createDayAdapter());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById (R.id.okbt).setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameet.getText().toString();
                int year = (int)yearS.getSelectedItem();
                int month = (int)monthS.getSelectedItem();
                int day = (int)dayS.getSelectedItem();

                String str = String.valueOf(year+month+day);
                int resultNumber = Integer.parseInt(str.substring(str.length()-1));
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(INPUT_NAME,name);
                intent.putExtra(RESULT_NUMBER,resultNumber);

                startActivity(intent);
            }
        });
    }

    private ArrayAdapter<Integer> createYearAdapter () {
        ArrayAdapter <Integer> yearAdapter = new ArrayAdapter <Integer> (this, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        Calendar calendar = Calendar.getInstance ();
        int year = calendar.get (Calendar.YEAR);

        for(int i = startY; i <= year; i ++) {
            yearAdapter.add (i);
        }
        return yearAdapter;
    }

    private ArrayAdapter<Integer> createMonthAdapter(){
        ArrayAdapter <Integer> monthAdapter = new ArrayAdapter <Integer> (this, android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        for(int i = 1; i <= 12; i++) {
            monthAdapter.add(i);
        }
        return monthAdapter;
    }

    private ArrayAdapter<Integer> createDayAdapter(){
        ArrayAdapter <Integer> dayAdapter = new ArrayAdapter <Integer> (this, android.R.layout.simple_spinner_dropdown_item);
        dayAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        int month = (int)monthS.getSelectedItem();
        int day = 31;
        if(month==4 || month==6 || month==9 || month==11){
            day=30;
        }else if(month==2){
            int year = (int)yearS.getSelectedItem();
            if((year%100!=0&&year%4==0 || year%400==0)){
                day=29;
            }else{
                day=28;
            }
        }

        for(int i = 1; i <= day; i++) {
            dayAdapter.add(i);
        }
        return dayAdapter;
    }
}
