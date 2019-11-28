package com.freshappbooks.openrawresource;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        InputStream stream = getInputStream(R.raw.lic);
        String result = getStringFromRawResource(stream);
        Log.d(TAG, "onCreate: = " + result );

        textView.setText(result);

    }
    private String getStringFromRawResource(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Writer writer = new StringWriter();
        try {
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                writer.write(line);
                writer.append("\n");
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }


    private InputStream getInputStream (int rawId) {
        return getResources().openRawResource(rawId);
    }
}
