package com.example.android20_lzhxw;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * ��Fragment��ӵ�Activity��
 * 	getFragmentMenager()  begin...():FragmentTransaction
 * 	add()
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ��Fragment�Ĺ������
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		//��Fragment��ӽ�Activity
		ft.add(R.id.container, new TTFragment());
		//�ύ
		ft.commit();
	}

}
