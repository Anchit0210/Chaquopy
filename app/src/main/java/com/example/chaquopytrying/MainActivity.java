package com.example.chaquopytrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.samsao.messageui.views.MessagesWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        MessagesWindow messagesWindow = (MessagesWindow)findViewById(R.id.customized_messages_window);
        EditText message = messagesWindow.getWritingMessageView().findViewById(com.samsao.messageui.R.id.message_box_text_field);
        message.setHint("Type here ...");
        messagesWindow.setBackgroundResource(com.google.android.material.R.color.design_default_color_primary_dark);

        messagesWindow.getWritingMessageView().findViewById(com.samsao.messageui.R.id.message_box_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                messagesWindow.sendMessage(message.getText().toString());

                Python py =Python.getInstance();
                PyObject pyobj= py.getModule("myscript");
                PyObject obj = pyobj.callAttr("main",message.getText().toString());

                messagesWindow.receiveMessage(obj.toString());

                message.setText("");

            }
        });



    }
}