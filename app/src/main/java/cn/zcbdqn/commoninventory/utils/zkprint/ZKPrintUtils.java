package cn.zcbdqn.commoninventory.utils.zkprint;

import zpSDK.zpSDK.zpBluetoothPrinter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

public class ZKPrintUtils {

	public static void print(String bluetoothDeviceAddress,
			String barCodeText,
			boolean printBarCodeText,
			String qrCode,
			boolean printQrCodeText,
			int drawableId,Context context) {

		zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(context);
		if (!zpSDK.connect(bluetoothDeviceAddress)) {
			Toast.makeText(context, "连接打印机失败", Toast.LENGTH_LONG).show();
			return;
		}
		int height=180;
		Resources res = context.getResources();

		Bitmap bmp = BitmapFactory.decodeResource(res, drawableId);
		// 设置页面宽高
		zpSDK.pageSetup(668, 668);
		// 打印一维码--参数-水平起始位置,垂直位置,内容,类型,是否旋转,线条粗细,高度
		zpSDK.drawBarCode(100, 20, barCodeText, 100, false, 3, height);
		if (printBarCodeText) {
			// 打印文字--参数-水平起始位置,垂直位置,内容,字体大小,旋转,加粗,反转,下划线
			height+=36;
			zpSDK.drawText(100, height, "1022376229760159744", 3, 0, 0, false,
					false);
		}
		if(qrCode!=null && !"".equals(qrCode)){
			height+=36;
			// 打印二维码---参数-水平起始位置,垂直位置,二维码文字,旋转,宽度,?
			zpSDK.drawQrCode(190, height, qrCode, 0, 6, 6);
		}
		if(drawableId!=0){
			//需要调整位置
			// 打印图片-- 参数-水平起始位置,垂直位置,bmp_size_x,bmp_size_y,图片
			zpSDK.drawGraphic(90, 48, 0, 0, bmp);
		}
		//zpSDK.drawBarCode(124, 48 + 100 + 56 + 56 + 80 + 80 + 80,
		// "12345678901234567", 128, false, 3, 60);
		/*
		 * zpSDK.pageSetup(668, 668); zpSDK.drawBarCode(8, 540,
		 * "12345678901234567", 128, true, 3, 60); zpSDK.drawGraphic(90, 48, 0,
		 * 0, bmp); zpSDK.drawQrCode(350, 48, "111111111", 0, 3, 0);
		 * zpSDK.drawText(90, 48 + 100, "400-8800-", 3, 0, 0, false, false);
		 * zpSDK.drawText(100, 48 + 100 + 56, "株洲      张贺", 3, 0, 0, false,
		 * false); zpSDK.drawText(250, 48 + 100 + 56 + 56, "经由  株洲", 3, 0, 0,
		 * false, false);
		 * 
		 * zpSDK.drawText(100, 48 + 100 + 56 + 56 + 80,
		 * "2015110101079-01-01   广州", 3, 0, 0, false, false);
		 * zpSDK.drawText(100, 48 + 100 + 56 + 56 + 80 + 80,
		 * "2015-11-01  23:00    卡班", 3, 0, 0, false, false);
		 * 
		 * zpSDK.drawBarCode(124, 48 + 100 + 56 + 56 + 80 + 80 + 80,
		 * "12345678901234567", 128, false, 3, 60);
		 */

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
