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
 * 加载图片： 1、先判断图片在HashMap当中是否存在，存在的话就在HashMap中拿；；；
 * 				不存在就去本地找；；；本地也没有，去网络上拿
 * 				注：HashMap中键是图片的url，值是url对应的bitmap
 * 				  本地存的文件的名字是url最后的图片名字部分
 * 			2、从网络上取下来的图片，存HashMap一份，再存本地一份
 * @author cj
 * 
 */
public class ImageLoader {
	private static HashMap<String, Bitmap> cache = new HashMap<String, Bitmap>();

	public static void loadImg(final String path, final ImageView imgv,final Context context) {
		Bitmap bitmap = cache.get(path);
		if(bitmap!=null){//HashMap中有对应的图片
			imgv.setImageBitmap(bitmap);
			return;
		}
		//检测本地存储中是否有需要的这个图片
		File imgFile = extracted(path, context);
		if(imgFile.exists()){
			Bitmap bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			imgv.setImageBitmap(bitmap2);
			return;
		}
		//图片是第一次加载
		HttpUtils.RequestBitmapNetWork(path, new OnBitmapNetWorkResponse() {
			
			@Override
			public void ok(Bitmap bitmap) {
				imgv.setImageBitmap(bitmap);
				//存HashMap、存本地
				cache.put(path, bitmap);
				//把bitMap压缩为JPEG、PNG图片
				//参数1压缩出来的图片的类型，参数2压缩品质  参数3 png图片生成后的file（输出流）
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
	/**获取path路径对应的图片文件*/
	private static File extracted(final String path, Context context) {
		File cacheFile= context.getCacheDir();
		String strImgName=path.substring(path.lastIndexOf("/")+1);
		File imgFile=new File(cacheFile,strImgName);
		return imgFile;
	}
}
