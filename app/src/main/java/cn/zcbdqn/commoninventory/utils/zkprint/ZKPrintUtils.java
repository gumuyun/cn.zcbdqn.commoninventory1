package cn.zcbdqn.commoninventory.utils.zkprint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import zpSDK.zpSDK.zpBluetoothPrinter;

public class ZKPrintUtils {

	/**
	 *  打印标签
	 * @param bluetoothDeviceAddress 蓝牙打印机地址
	 * @param barCodeText 一维码
	 * @param printBarCodeText 是否打印一维码文字
	 * @param qrCode 二维码
	 * @param printQrCodeText 是否打印二维码文字
	 * @param drawableId 图片资源ID
	 * @param context 上下文
	 */
	public static void print(String bluetoothDeviceAddress,
			String barCodeText,
			boolean printBarCodeText,
			String qrCode,
			boolean printQrCodeText,
			int drawableId,Context context) {
		//创建对象
		zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(context);
		//判断是否连接打印机
		if (!zpSDK.connect(bluetoothDeviceAddress)) {
			Toast.makeText(context, "连接打印机失败", Toast.LENGTH_LONG).show();
			return;
		}

		int text_x=0;//距左边界的位置,用于计算居中
		int height=20;//开始高度
		int barCodeHeight=50;//一维码高度
		int qrCodeVer=6;//二维码级别,,,,预计高度=级别*40
		int pageHeight=height+barCodeHeight;//设置面页高度,
		int pageWidth=785;//设置面页宽度,
		//如果打印一维码文字页面高度增加36
		if (printBarCodeText){
			pageHeight+=36;
		}
		//如果打印二维码页面高度增加    级别*40
		if (qrCode!=null && !"".equals(qrCode)){
			pageHeight+=qrCodeVer*40;
		}
		//如果打印二维码文字页面高度增加36
		if (printQrCodeText){
			pageHeight+=36;
		}
		pageHeight+=50;
		// 设置页面宽高
		//zpSDK.pageSetup(668, pageHeight);
		zpSDK.pageSetup(pageWidth, pageHeight);

		// 打印一维码--参数-水平起始位置,垂直位置,内容,类型,是否旋转,线条粗细,高度
		zpSDK.drawBarCode(100, 20, barCodeText, 100, false, 3, barCodeHeight);
		if (printBarCodeText) {
			// 打印文字--参数-水平起始位置,垂直位置,内容,字体大小,旋转,加粗,反转,下划线
			height+=barCodeHeight;
			text_x= (int) ((pageWidth-barCodeText.length()*20)/2*0.71);
			Log.e("gumy","print barCodeText left:"+text_x);
			zpSDK.drawText(text_x, height, barCodeText, 3, 0, 0, false,false);
		}
		if(qrCode!=null && !"".equals(qrCode)){
			height+=36;
			// 打印二维码---参数-水平起始位置,垂直位置,二维码文字,旋转,宽度,?
			text_x=(pageWidth-qrCodeVer*40)/2;
			zpSDK.drawQrCode(190, height, qrCode, 0, 6, 6);
		}
		if (printQrCodeText) {
			// 打印二维码文字--参数-水平起始位置,垂直位置,内容,字体大小,旋转,加粗,反转,下划线
			height+=qrCodeVer*40;
			text_x= (int) ((pageWidth-qrCode.length()*20)/2*0.71);
			zpSDK.drawText(text_x, height, qrCode, 3, 0, 0, false,false);
		}
		if(drawableId!=0){
			//需要调整位置
			// 打印图片-- 参数-水平起始位置,垂直位置,bmp_size_x,bmp_size_y,图片
			//资源对象,可以获得图片等
			Resources res = context.getResources();
			Bitmap bmp = BitmapFactory.decodeResource(res, drawableId);
			zpSDK.drawGraphic(90, 48, 0, 0, bmp);
		}


		zpSDK.print(0, 0);
		
		zpSDK.printerStatus();
		int a = zpSDK.GetStatus();

		if (a == -1) { // "获取状态异常------";
			Toast.makeText(context, "获取状态异常", Toast.LENGTH_LONG).show();

		}
		if (a == 1) {// "缺纸----------";
			Toast.makeText(context, "缺纸", Toast.LENGTH_LONG).show();
		}
		if (a == 2) {
			// "开盖----------";
			Toast.makeText(context, "纸仓未合上", Toast.LENGTH_LONG).show();
		}
		if (a == 0) {

			// "打印机正常-------";
			Toast.makeText(context, "打印完成", Toast.LENGTH_LONG).show();
		}

		zpSDK.disconnect();
	}
	
	public static int getStatus(Context context,String bluetoothDeviceAddress){
		zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(context);
		if (!zpSDK.connect(bluetoothDeviceAddress)) {
			//Toast.makeText(context, "连接打印机失败", Toast.LENGTH_LONG).show();
			return -1;
		}
		zpSDK.pageSetup(1, 1);
		zpSDK.drawText(1, 1, "", 1, 0, 0, false,false);
		zpSDK.print(0, 0);
		//zpSDK.pageSetup(1,1);
		//zpSDK.drawText(1, 1, "", 3, 0, 0, false,false);
		//zpSDK.print(0, 0);
		zpSDK.printerStatus();
		int status= zpSDK.GetStatus();
		/*switch (status) {
		case 0:
			Toast.makeText(context, "打印机正常", Toast.LENGTH_LONG).show();
			break;
		case 1:
			Toast.makeText(context, "缺纸", Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(context, "纸仓未合上", Toast.LENGTH_LONG).show();
			break;
		default:
			Toast.makeText(context, "获取状态异常", Toast.LENGTH_LONG).show();
			break;
		}*/
		zpSDK.disconnect();
		return status;
	}
}
