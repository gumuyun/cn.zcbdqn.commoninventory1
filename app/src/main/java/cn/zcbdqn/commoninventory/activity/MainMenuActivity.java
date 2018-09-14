package cn.zcbdqn.commoninventory.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.uhf.uhf.Common.Comm;
import com.uhf.uhf.UHF1.UHF1Function.SPconfig;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.utils.TextUtils;

public class MainMenuActivity extends BaseActivity {

	/**
	 * 盘点按扭
	 */
	private ImageButton takeStockView;
	/**
	 * 入库按扭
	 */
	private ImageButton inStockView;
	/**
	 * 出库按扭
	 */
	private ImageButton outStockView;
	/**
	 * 商品管理按扭
	 */
	private ImageButton goodsManageView;
	
	
	private RelativeLayout footSettingRl;
	private RelativeLayout footBusinessRl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.activity_main_menu);
		//获得View
		takeStockView=(ImageButton) findViewById(R.id.take_stock);
		inStockView=(ImageButton) findViewById(R.id.in_stock);
		outStockView=(ImageButton) findViewById(R.id.out_stock);
		goodsManageView=(ImageButton) findViewById(R.id.goods_manage);
	
		//设置按扭
		footSettingRl=(RelativeLayout) findViewById(R.id.main_menu_foot_setting_rl);
		footBusinessRl=(RelativeLayout) findViewById(R.id.main_menu_foot_business_rl);
		//注册点击事件监听器
		takeStockView.setOnClickListener(this);
		inStockView.setOnClickListener(this);
		outStockView.setOnClickListener(this);
		goodsManageView.setOnClickListener(this);
		
		footSettingRl.setOnClickListener(this);
		footBusinessRl.setOnClickListener(this);
	}





	@Override
	public void onClick(View v) {
		int id=v.getId();
		Intent intent=null;
		switch (id) {
		case R.id.take_stock:
			//点击盘点
			intent=new Intent(this, TakeStockActivity.class);
			startActivity(intent);
			
			break;
		case R.id.in_stock:
			//点击入库
			intent=new Intent(this, InStockActivity.class);
			startActivity(intent);
			break;
		case R.id.out_stock:
			//点击出库
			intent=new Intent(this, OutStockActivity.class);
			startActivity(intent);
			break;
		case R.id.goods_manage:
			//点击商品管理
			intent=new Intent(this, AddGoodsActivity.class);
			startActivity(intent);
			break;

		case R.id.main_menu_foot_setting_rl:
			//进入设置
			intent=new Intent(this, MainSettingActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.main_menu_foot_business_rl:
			//进入仓库设置
			intent=new Intent(this, WarehouseActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
		
	}
	
	long currentTime=0;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis()-currentTime<2000) {
			finish();
		}else {
			currentTime=System.currentTimeMillis();
			Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		// 结束Activity&从栈中移除该Activity
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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
