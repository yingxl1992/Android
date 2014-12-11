package com.example.homework6_client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	OutputStream os;
	EditText show;
	EditText text;
	String name;
	Handler handler;
	Button confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent=getIntent();
		name=intent.getStringExtra("name");
			
		show=(EditText)findViewById(R.id.et_show);
		text=(EditText)findViewById(R.id.et_text);
		confirm=(Button)findViewById(R.id.btn_confirm);
		show.setText(name+"欢迎你~");
		
		Socket socket;
		handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					show.append("\n" + msg.obj.toString());
				}
			}
		};
		try {
			socket=new Socket("192.168.1.108",30000);
			new Thread(new ClientThread(socket, handler)).start();
			os=socket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {							
				try {
					String line=name+":"+text.getText().toString()+"\r\n";
					os.write(line.getBytes());
					text.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
}
