package com.example.edgedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.otikev.edgedialog.ButtonConfig;
import com.otikev.edgedialog.EdgeDialog;

import static com.otikev.edgedialog.EdgeDialog.Orientation.BOTTOM;
import static com.otikev.edgedialog.EdgeDialog.Orientation.TOP;

public class MainActivity extends AppCompatActivity {

    Button buttonTopDialog;
    Button buttonBottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTopDialog = findViewById(R.id.buttonTopDialog);
        buttonBottomDialog = findViewById(R.id.buttonBottomDialog);

        setEventListeners();
    }
    void setEventListeners(){

        buttonTopDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTopDialog();
            }
        });

        buttonBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }
    void showTopDialog(){
        EdgeDialog edgeDialog = new EdgeDialog.Builder()
                .title("Top Dialog")
                .message("This is a dialog attached to the top edge")
                .button(new ButtonConfig("Dismiss"))
                .orientation(TOP)
                .build();
        edgeDialog.show(getSupportFragmentManager());
    }

    void showBottomDialog(){
        EdgeDialog edgeDialog = new EdgeDialog.Builder()
                .title("Bottom Dialog")
                .message("This is a dialog attached to the bottom edge")
                .button(new ButtonConfig("YES"))
                .button(new ButtonConfig("NO"))
                .cancellable(false)
                .orientation(BOTTOM)
                .build();
        edgeDialog.show(getSupportFragmentManager());
    }
}