package com.example.myapplication.util;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.example.myapplication.app.App;

public class ToastUtil {



	private static int GRAVITY = Gravity.CENTER;
    private static Context context= App.getInstance();
    private ToastUtil(){
    	
    }
    /**
     * long��ʾ
     * @param message String
     */
	public static void showLong(String message) {
		
		show(message, Toast.LENGTH_LONG);

	}


     /**
      * short��ʾ
      * @param message
      */
	public static void showShort(String message) {

		show(message, Toast.LENGTH_SHORT);

	}


    /**
     * textId��long��ʾ
     * @param textId int(String id��
     */
	public static void showLong(int textId) {

		show(textId, Toast.LENGTH_LONG);

	}


      /**
       * textId��short��ʾ
       * @param textId
       */
	public static void showShort(int textId) {

		show(textId, Toast.LENGTH_SHORT);

	}


     /**
      * ����ʱ�����ʾ
      * @param text String 
      * @param duration int
      */
	public static void show(String text, int duration) {

		Toast toast = Toast.makeText(context, text, duration);

		toast.setGravity(GRAVITY, 80, 80);

		toast.show();

	}


     /**
      * ����ʱ���textId��ʾ
      * @param textId
      * @param duration
      */
	public static void show(int textId, int duration) {

		Toast toast = Toast.makeText(context, textId, duration);

		toast.setGravity(GRAVITY, 80, 80);

		toast.show();

	}

     /**
      * textid�ĳɹ���ʾ
      * @param textId
      */
	public static void showSuccess(int textId) {

//		showIconToast(textId, R.drawable.success, R.color.blue);

	}


    /**
     * textid��ʧ����ʾ
     * @param textId
     */
	public static void showFailure(int textId) {

//		showIconToast(textId, R.drawable.failure, R.color.black);

	}


   /**
    * textid iconid colorid����ʾ
    * @param textId
    * @param iconId
    * @param colorId
    */
//	public static void showIconToast(int textId, int iconId,
//
//			int colorId) {
//
//		LayoutInflater inflater = (LayoutInflater) context
//
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//		View layout = inflater.inflate(R.layout.toast, null);
//
//		((TextView)layout).setText(textId);
//
//		((TextView) layout).setTextColor(context.getResources().getColor(
//
//				colorId));
//
//		((TextView) layout).setCompoundDrawablesWithIntrinsicBounds(iconId, 0,
//
//				0, 0);
//
//		Toast toast = new Toast(context);
//
//		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//
//		toast.setDuration(Toast.LENGTH_SHORT);
//
//		toast.setView(layout);
//
//		toast.show();
//
//	}



}

