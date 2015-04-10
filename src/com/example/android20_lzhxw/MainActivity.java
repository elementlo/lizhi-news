package com.example.android20_lzhxw;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 把Fragment添加到Activity：
 * 	getFragmentMenager()  begin...():FragmentTransaction
 * 	add()
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取到Fragment的管理对象
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		//把Fragment添加进Activity
		ft.add(R.id.container, new TTFragment());
		//提交
		ft.commit();
	}

}
