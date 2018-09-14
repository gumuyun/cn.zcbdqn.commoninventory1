package cn.zcbdqn.commoninventory.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.adapter.WarehouseAdapter;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;

public class WarehouseActivity extends Activity implements OnClickListener {
	/**
	 * 请求码
	 */
	public static final int ADD_WAREHOUSE_CODE=1;
	/**
	 * 扫描界面跳转
	 */
	private RelativeLayout footMainMenuRl;
	/**
	 * 设置界面
	 */
	private RelativeLayout footSettingRl;
	/**
	 * 更多按扭
	 */
	private ImageView warehouseMoreIv;
	/**
	 * 仓库列表ListView
	 */
	private ListView warehouseLv;
	/**
	 * 数据库处理助手
	 */
	private RfidDataOpenHelper rfidDataOpenHelper;
	/**
	 * 仓库列表
	 */
	private List<RfidWarehouse> list ;
	/**
	 * 适配器,显示还需要改进
	 */
	private WarehouseAdapter adapter;
	/**
	 * 弹窗
	 */
	private PopupWindow popupWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.activity_warehouse);
		//反射View
		footMainMenuRl=(RelativeLayout) findViewById(R.id.warehouse_foot_scan_rl);
		footSettingRl=(RelativeLayout) findViewById(R.id.warehouse_foot_setting_rl);
		warehouseMoreIv=(ImageView) findViewById(R.id.warehouse_more);
		warehouseLv=(ListView) findViewById(R.id.warehouse_lv);
		
		//注册点击事件
		 
		footMainMenuRl.setOnClickListener(this);
		footSettingRl.setOnClickListener(this);
		warehouseMoreIv.setOnClickListener(this);
		//创建数据库助手对象
		rfidDataOpenHelper=new RfidDataOpenHelper(this);
		//从数据库中加载仓库
		list = rfidDataOpenHelper.queryWarehouse(null,null);
		//创建适配器
		adapter=new WarehouseAdapter(this);
		//设置数据
		adapter.setList(list);
		//关联适配器
		warehouseLv.setAdapter(adapter);
	}
	
	
	@Override
	public void onClick(View v) {
		
		Intent intent=null;
		switch (v.getId()) {
		case R.id.warehouse_foot_scan_rl:
			//点击扫描,跳转扫描界面
			intent=new Intent(this, MainMenuActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.warehouse_foot_setting_rl:
			//设置界面
			intent=new Intent(this, MainSettingActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.warehouse_more:
			//点击更多
			//创建popwindow
			//布局
			View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_more_warehouse, null);
			TextView addWarehouseTv=(TextView) view.findViewById(R.id.add_warehouse);
			TextView updateWarehouseTv=(TextView) view.findViewById(R.id.update_warehouse);
			TextView deleteWarehouseTv=(TextView) view.findViewById(R.id.delete_warehouse);
			
			popupWindow=new PopupWindow(view,200,200,true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.showAsDropDown(v,0,20);
			
			addWarehouseTv.setOnClickListener(new OnClickListener() {
				@SuppressLint("NewApi") @Override
				public void onClick(View v) {
					//增加商档
					//Toast.makeText(WarehouseActivity.this, "增加商档", Toast.LENGTH_LONG).show();
					popupWindow.dismiss();
					Intent intent=new Intent(WarehouseActivity.this, AddWarehouseActivity.class);
					startActivityForResult(intent, ADD_WAREHOUSE_CODE);
				}
			});
			updateWarehouseTv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//更新商档
					Toast.makeText(WarehouseActivity.this, "更新商档", Toast.LENGTH_LONG).show();
					popupWindow.dismiss();
				}
			});
			deleteWarehouseTv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//删除商档
					Toast.makeText(WarehouseActivity.this, "删除商档", Toast.LENGTH_LONG).show();
					popupWindow.dismiss();
				}
			});
			break;
		default:
			break;
		}
		
	}
	

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode==ADD_WAREHOUSE_CODE) {
			int result=data.getIntExtra("resultCode", 0);
			if(result!=0){
				Toast.makeText(this, "增加成功", Toast.LENGTH_SHORT).show();
				list = rfidDataOpenHelper.queryWarehouse(null,null);
				adapter.setList(list);
				adapter.notifyDataSetChanged();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
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
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}
	
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.warehouse, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

	
}
