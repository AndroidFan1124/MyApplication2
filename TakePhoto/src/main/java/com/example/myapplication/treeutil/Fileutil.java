package com.example.myapplication.treeutil;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class Fileutil {

	public static boolean haveSd() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}

	public static final String baseFileUrl = "";

	public static boolean newFile(String parentFileName) {
		Log.d("wmy", "parentFileName��" + parentFileName);
		boolean flag = true;
		if (haveSd()) {

			File parentFile = new File(getBaseFilrPath() + parentFileName);
			if(!parentFile.exists())
				 parentFile.mkdir();
			else{
				 Log.d("wmy", getBaseFilrPath() + parentFileName + "�Ѵ���");
				 flag = false;
			}
		}
			// if (fileName == null) {// ˵��ֻ������Ŀ¼�ͺ�
			// if (!parentFile.exists()) {
			// parentFile.mkdir();
			// } else {
			// Log.d("wmy", getBaseFilrPath() + parentFileName + "�Ѵ���");
			// }
			// } else {
			// // �жϸ�ĸ·�Ƿ����
			// // �����ڣ��ж��Ƿ��������������������������أ�����������
			// // �粻���ڣ��Ƚ���Ŀ¼���ڽ���Ŀ¼
			// if (!parentFile.exists()) {
			// parentFile.mkdir();
			// } else {
			// File[] fileList = parentFile.listFiles();
			// // �Ƿ�������
			// if (fileList != null && fileList.length > 0)
			// for (File f : fileList) {
			// if (f.isDirectory() && f.getName().equals(fileName))
			// flag = false;// �ļ��Ѿ�����
			// break;
			// }
			//
			// if (flag) {
			// String path = getBaseFilrPath() + parentFileName + "/"
			// + fileName;
			// Log.d("wmy", "path" + path);
			// File file = new File(path);
			// file.mkdir();
			// }
			// }
			// }
			//========================================================
			// Log.d("wmy", "��Ŀ¼��" + getBaseFilrPath() + parentFileName);
			// // File parentFile = new File(getBaseFilrPath() +
			// parentFileName);
			//
			// Log.d("wmy", "parentFile�Ƿ���ڣ�" + parentFile.exists());
			// if (!parentFile.exists()) {
			// // File childFile = new File(fileName);
			//
			// File[] fileList = parentFile.listFiles();
			// //�Ƿ�������
			// if (fileList!= null && fileList.length > 0)
			// for (File f : fileList) {
			// if (f.isDirectory() && f.getName().equals(fileName))
			// flag = false;// �ļ��Ѿ�����
			// break;
			// }
			// Log.d("wmy", "FLAG��" + flag);
			// if (flag) {// �ļ�������
			//
			// String path = parentFileName + "/" + fileName;
			// Log.d("wmy", getBaseFilrPath() + path);
			// File file = new File(getBaseFilrPath() + path);
			// file.mkdir();
			// }
			// }
			// }

		return flag;
	}

	public static String getBaseFilrPath() {
		// if(haveSd()){
		return Environment.getExternalStorageDirectory().getPath() + "/DCIM/";

		// }
		// return "";
	}
}
