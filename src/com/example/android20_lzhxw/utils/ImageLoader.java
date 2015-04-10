package com.example.android20_lzhxw.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.android20_lzhxw.utils.HttpUtils.OnBitmapNetWorkResponse;

/**
 * ����ͼƬ�� 1�����ж�ͼƬ��HashMap�����Ƿ���ڣ����ڵĻ�����HashMap���ã�����
 * 				�����ھ�ȥ�����ң���������Ҳû�У�ȥ��������
 * 				ע��HashMap�м���ͼƬ��url��ֵ��url��Ӧ��bitmap
 * 				  ���ش���ļ���������url����ͼƬ���ֲ���
 * 			2����������ȡ������ͼƬ����HashMapһ�ݣ��ٴ汾��һ��
 * @author cj
 * 
 */
public class ImageLoader {
	private static HashMap<String, Bitmap> cache = new HashMap<String, Bitmap>();

	public static void loadImg(final String path, final ImageView imgv,final Context context) {
		Bitmap bitmap = cache.get(path);
		if(bitmap!=null){//HashMap���ж�Ӧ��ͼƬ
			imgv.setImageBitmap(bitmap);
			return;
		}
		//��Ȿ�ش洢���Ƿ�����Ҫ�����ͼƬ
		File imgFile = extracted(path, context);
		if(imgFile.exists()){
			Bitmap bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			imgv.setImageBitmap(bitmap2);
			return;
		}
		//ͼƬ�ǵ�һ�μ���
		HttpUtils.RequestBitmapNetWork(path, new OnBitmapNetWorkResponse() {
			
			@Override
			public void ok(Bitmap bitmap) {
				imgv.setImageBitmap(bitmap);
				//��HashMap���汾��
				cache.put(path, bitmap);
				//��bitMapѹ��ΪJPEG��PNGͼƬ
				//����1ѹ��������ͼƬ�����ͣ�����2ѹ��Ʒ��  ����3 pngͼƬ���ɺ��file���������
				FileOutputStream fos=null;
				try {
					fos = new FileOutputStream(extracted(path,context));
					bitmap.compress(CompressFormat.PNG, 100,fos );
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}finally{
					if(fos!=null){
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			@Override
			public void error(String error) {
				
			}
		});
		
		
	}
	/**��ȡpath·����Ӧ��ͼƬ�ļ�*/
	private static File extracted(final String path, Context context) {
		File cacheFile= context.getCacheDir();
		String strImgName=path.substring(path.lastIndexOf("/")+1);
		File imgFile=new File(cacheFile,strImgName);
		return imgFile;
	}
}
