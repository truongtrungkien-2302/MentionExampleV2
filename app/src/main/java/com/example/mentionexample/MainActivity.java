package com.example.mentionexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linkedin.android.spyglass.suggestions.SuggestionsResult;
import com.linkedin.android.spyglass.tokenization.QueryToken;
import com.linkedin.android.spyglass.tokenization.interfaces.QueryTokenReceiver;
import com.linkedin.android.spyglass.ui.RichEditorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    AppCompatImageView ivSend, ivNoSend;
    EditText etChat;
    RecyclerView rvData;
    MessageAdapter adapter;
    List<Message> listMessage;
    String tvMention = "";
    String mention = "";
    private static final String BUCKET = "cities-memory";
    private City.CityLoader cities;
    int start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initID();

//        initEvent();

        initRvData();

    }

    private void initRvData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(linearLayoutManager);
        listMessage = new ArrayList<>();
        adapter = new MessageAdapter();
        adapter.setData(listMessage);
        rvData.setAdapter(adapter);
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String strMessage = etChat.getText().toString().trim();
        etChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(editable)){
                    ivNoSend.setVisibility(View.VISIBLE);
                    ivSend.setVisibility(View.GONE);
                } else {
                    ivNoSend.setVisibility(View.GONE);
                    ivSend.setVisibility(View.VISIBLE);
                }

                String text = editable.toString();
                Pattern p = Pattern.compile("[@][a-zA-Z0-9-.]+");
                Matcher m = p.matcher(text);
                int cursorPosition = etChat.getSelectionStart();
                while(m.find())
                {
                    if (cursorPosition >= m.start() && cursorPosition <= m.end())
                    {
                        start = m.start();
                        end = m.end();
                        break;
                    }
                }
            }
        });
        Spannable wordtoSpan = new SpannableString(strMessage);
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (TextUtils.isEmpty(strMessage)){
            return;
        }
        listMessage.add(new Message(wordtoSpan));
        adapter.notifyDataSetChanged();
        rvData.scrollToPosition(listMessage.size() - 1);
        etChat.setText("");
    }


    private void initEvent() {



        ivSend.setOnClickListener(view -> {

        });

    }

    private void initID() {
        ivSend = findViewById(R.id.ivSend);
        ivNoSend = findViewById(R.id.ivNoSend);
        etChat = findViewById(R.id.etChat);
        rvData = findViewById(R.id.rvData);
    }

}