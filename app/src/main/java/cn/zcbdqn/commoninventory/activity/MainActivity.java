package cn.zcbdqn.commoninventory.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.uhf.uhf.Common.Comm;
import com.uhf.uhf.UHF1.UHF1Function.SPconfig;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.constvalue.DrugSystemConst;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;
import cn.zcbdqn.commoninventory.entity.User;
import cn.zcbdqn.commoninventory.utils.AlertUtil;
import cn.zcbdqn.commoninventory.utils.TextUtils;


public class MainActivity extends Activity {
	private RfidDataOpenHelper rfidDataOpenHelper;
	

	/**
	 * 显示版本
	 */
	private TextView tv_version;
	/**
	 * 
	 */
	private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
        setContentView(R.layout.activity_main);
        rfidDataOpenHelper=new RfidDataOpenHelper(this);
        //获得显示版本的TextView
        tv_version = (TextView) findViewById(R.id.tv_version);
        sp = getSharedPreferences("InvenConfig", MODE_PRIVATE);
       
        //获得包管理器,获得版本信息,设置信息
        PackageManager manager = this.getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			tv_version.setText(AlertUtil.getString(R.string.versionV) + version);
			Log.e("gumy",AlertUtil.getString(R.string.versionV) + version);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		//如果非销邦设备,退出程序
		/*if (!Build.MANUFACTURER.equals("SUPOIN")){
			AlertUtil.showAlert(MainActivity.this, 
					"警告", "你的设备是"+Build.MANUFACTURER+"，请选择销邦设备安装！",
					"确定", new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							AlertUtil.dismissDialog();
							finish();
						}
					});
			return;
		}*/
		
		//初始化系统配置......
		//网络配置
		NetworkConfig networkConfig = rfidDataOpenHelper.queryNetworkConfig();
		if(networkConfig==null){
			networkConfig=new NetworkConfig(null, "http://gumuyun.vicp.net", 51336, "/jeesite", "0", null, "", "");
		}
		MyApplication.applicationMap.put("networkConfig", networkConfig);
		Log.e("gumy","networkConfig:"+networkConfig.toUrl());
		//获得设置的黙认打印机
		String defaultBluePrintAddress=sp.getString("defaultBluePrintAddress", null);
		MyApplication.applicationMap.put("defaultBluePrintAddress", defaultBluePrintAddress);
		Log.e("gumy","defaultBluePrintAddress:"+defaultBluePrintAddress);
		//判断是否已登录
		User user=new User("aaaa");
		MyApplication.applicationMap.put("user", user);
		Log.e("gumy","currentUser:"+user.getId());
        //黙认货位
		String warehouseId = sp.getString(DrugSystemConst.warehouseId, "1");
		String warehouseName = sp.getString(DrugSystemConst.warehouseName, "黙认商档");
		Log.e("gumy","warehouseId:"+warehouseId);
		RfidWarehouse warehouse=new RfidWarehouse(warehouseId,warehouseName);
		MyApplication.applicationMap.put("warehouse", warehouse);
		//两秒后进入主菜单界面
		openDateOrNextActivity();


    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	// 结束Activity&从栈中移除该Activity
		MyApplication.getInstance().finishActivity(this);
    }

    //两秒后进入主菜单界面
  	private void openDateOrNextActivity() {
  		new Handler().postDelayed(new Runnable() {
  			public void run() {
  				Intent intent = new Intent();
  				intent.setClass(MainActivity.this, MainMenuActivity.class);
  				startActivity(intent);
  				finish();
  			}
  		}, 5000);

  	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();
		initDevice();
	}

	public void release() {
		try {
			if (Comm.isrun){
				Comm.stopScan();
			}
			//关闭rfid扫描电源
			Comm.powerDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化rfid扫描
	 */
	public void initDevice() {
		Comm.repeatSound=true;
		Comm.app = getApplication();
		Comm.spConfig = new SPconfig(this);

		Comm.context = this;
		Comm.soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		Comm.soundPool.load(this, R.raw.beep51, 1);

		Comm.checkDevice();
		Comm.initWireless(Comm.app);
		Comm.connecthandler = connectH;
		Comm.Connect();
		Log.e("gumy", "connect");
	}




	public Handler connectH = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			try {
				//cb_is6btag.setEnabled(false);
//                UHF001.mhandler = uhfhandler;
//                if (null != rfidOperate) {
//                    rfidOperate.mHandler = uhfhandler;
//                    cb_is6btag.setEnabled(true);
//                }
//                if (null != Comm.uhf6)
//                    Comm.uhf6.UHF6handler = uhfhandler;
				//Comm.mInventoryHandler= uhfhandler;

				Bundle bd = msg.getData();
				String strMsg = bd.get("Msg").toString();
				if (!TextUtils.isEmpty(strMsg)) {
					//tv_state.setText(strMsg);
					Comm.SetInventoryTid(false);
					Log.e("gumy", strMsg);
				} else{
					//模块初始化失败
					//tv_state.setText("模块初始化失败");
					Log.e("gumy", strMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("gumy", e.getMessage());
			}
		}
	};

}
