package com.example.homework5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class CalService extends Service {
	
    private CalBinder binder=new CalBinder(); 
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	class CalBinder extends Binder {
		
		public int compare(int num1, int num2){
			if(num1>num2)
			{
				return 1;
			}else if(num1==num2){
				return 0;
			}else{
				return -1;
			}
		}
	}

}

