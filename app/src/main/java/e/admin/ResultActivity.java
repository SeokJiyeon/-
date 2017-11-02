package e.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity{

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView nameTX = (TextView)findViewById(R.id.nameTX);
        TextView resultTX = (TextView)findViewById(R.id.resultTX);

        String name = getIntent().getStringExtra(MainActivity.INPUT_NAME);
        nameTX.setText(name);

        int resultNumber = getIntent (). getIntExtra (MainActivity.RESULT_NUMBER,0);
        String resultStr = getResources (). getStringArray (R.array.results) [resultNumber];
        resultTX.setText (resultStr);
    }
}
