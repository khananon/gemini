package com.example.gemini;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    MessageAdapter mA;
    RecyclerView Rv;
    ImageButton send;
    EditText EdtTxt;
    ImageView logo;
    LottieAnimationView Av;
    List<Message> messageList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        send = findViewById(R.id.Sendbtn);
       // Av= findViewById(R.id.animationView);
        EdtTxt = findViewById(R.id.editText);
        Rv = findViewById(R.id.Rview);
        logo=findViewById(R.id.lg);
        messageList = new ArrayList<>(); // Initialize messageList
        mA = new MessageAdapter(messageList);
        Rv.setAdapter(mA);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        Rv.setLayoutManager(llm);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logo.setVisibility(View.GONE);
                String question = EdtTxt.getText().toString().trim();
                addToChat(question, Message.SENT_BY_MY);
                EdtTxt.setText("");
               // Av.setVisibility(View.VISIBLE);

                GeminiPro model = new GeminiPro();
                model.getResponse(question, new ResponseCallback() {
                    @Override
                    public void onResponse(String response) {
                        addToChat(response, Message.SENT_BY_BOT);
                       // Av.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void addToChat(String message, String sentBy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sentBy));
                mA.notifyDataSetChanged();
                Rv.smoothScrollToPosition(mA.getItemCount());
            }
        });
    }
}
