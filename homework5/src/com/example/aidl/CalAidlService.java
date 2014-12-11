package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidl.ICal;

public class CalAidlService extends Service {
	private CalBinder binder=new CalBinder();
	
	class CalBinder extends ICal.Stub{
		
		@Override
		public int compare(int num1, int num2) throws RemoteException {
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

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
}
