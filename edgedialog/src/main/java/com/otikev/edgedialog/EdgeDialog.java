package com.otikev.edgedialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import static com.otikev.edgedialog.EdgeDialog.Orientation.BOTTOM;
import static com.otikev.edgedialog.EdgeDialog.Orientation.TOP;

public class EdgeDialog extends DialogFragment {
    Builder builder;
    private String TAG = getClass().getCanonicalName();

    TextView titleText;
    TextView messageText;
    Button[] buttons = new Button[3];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (builder.orientation == TOP) {
            return inflater.inflate(R.layout.dialog_edge_top, container);
        } else if (builder.orientation == BOTTOM) {
            return inflater.inflate(R.layout.dialog_edge_bottom, container);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(0.6f);

            WindowManager.LayoutParams params = window.getAttributes();
            if (builder.orientation == TOP) {
                params.gravity = Gravity.TOP;
            } else if (builder.orientation == BOTTOM) {
                params.gravity = Gravity.BOTTOM;
            }
            window.setAttributes(params);
        }

        setCancelable(builder.cancellable);
    }

    public void setupViews(View view) {
        titleText = view.findViewById(R.id.titleText);
        messageText = view.findViewById(R.id.messageText);
        buttons[0] = view.findViewById(R.id.button1);
        buttons[1] = view.findViewById(R.id.button2);
        buttons[2] = view.findViewById(R.id.button3);

        titleText.setText(builder.title);
        messageText.setText(builder.message);

        buttons[0].setVisibility(View.GONE);
        buttons[1].setVisibility(View.GONE);
        buttons[2].setVisibility(View.GONE);

        for (int i = 0; i < builder.buttons.size(); i++) {
            final ButtonConfig config = builder.buttons.get(i);
            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].setText(config.text);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDialog().dismiss();
                    config.clickListener.onClick(view);
                }
            });
        }
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

    public static class Builder {
        boolean cancellable = true;
        String title = "";
        String message = "";
        Orientation orientation = TOP;
        List<ButtonConfig> buttons = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder cancellable(boolean cancellable) {
            this.cancellable = cancellable;
            return this;
        }

        public Builder button(ButtonConfig buttonConfig) {
            if (buttons.size() <= 3) {
                buttons.add(buttonConfig);
            }
            return this;
        }

        public Builder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public EdgeDialog build() {
            EdgeDialog edgeDialog = new EdgeDialog();
            edgeDialog.builder = this;
            return edgeDialog;
        }
    }

    public enum Orientation {
        TOP, BOTTOM
    }
}

