package com.fstech.yzedutc.application;

import java.io.Serializable;

import android.app.Application;

public class YZEduApplication extends Application implements Serializable{
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
