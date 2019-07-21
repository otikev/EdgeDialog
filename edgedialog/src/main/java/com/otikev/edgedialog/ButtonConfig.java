package com.otikev.edgedialog;

import android.view.View;

public class ButtonConfig {
    String text;
    View.OnClickListener clickListener;

    public ButtonConfig(String text, View.OnClickListener clickListener) {
        this.text = text;
        this.clickListener = clickListener;
    }

    public ButtonConfig(String text) {
        this.text = text;
        this.clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing
            }
        };
    }
}