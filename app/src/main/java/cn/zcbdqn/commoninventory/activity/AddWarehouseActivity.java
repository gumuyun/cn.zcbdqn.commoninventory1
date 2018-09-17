package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.db.TableNamesConstant;
import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;
import cn.zcbdqn.commoninventory.entity.User;
import cn.zcbdqn.commoninventory.utils.DateUtil;
import cn.zcbdqn.commoninventory.utils.GsonUtil;
import cn.zcbdqn.commoninventory.utils.NetworkConfigConstant;
import cn.zcbdqn.commoninventory.utils.Object2Values;
import cn.zcbdqn.commoninventory.utils.OkHttpUtil;
import cn.zcbdqn.commoninventory.utils.UUIDUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddWarehouseActivity extends Activity implements OnClickListener {
	
	public static final int ADD_WAREHOUSE=1;
	public NetworkConfig networkConfig;

	private Button smtBtn;
	private Button calBtn;
	private EditText warehouseNameEt;
	private EditText warehouseAddressEt;
	private EditText warehouseRemarksEt;

	private RfidDataOpenHelper rfidDataOpenHelper;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_add_warehouse);
		sp = getSharedPreferences("InvenConfig", MODE_PRIVATE);
		Map<String,Object> applicationMap=MyApplication.applicationMap;
		networkConfig=(NetworkConfig) applicationMap.get("networkConfig");
		smtBtn = (Button) findViewById(R.id.add_warehouse_btn);
		calBtn = (Button) findViewById(R.id.cal_warehouse_btn);
		warehouseNameEt = (EditText) findViewById(R.id.warehouse_name_et);
		warehouseAddressEt = (EditText) findViewById(R.id.warehouse_address_et);
		warehouseRemarksEt = (EditText) findViewById(R.id.warehouse_remarks_et);

		smtBtn.setOnClickListener(this);
		calBtn.setOnClickListener(this);

		rfidDataOpenHelper = new RfidDataOpenHelper(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent;

		switch (v.getId()) {
		case R.id.add_warehouse_btn:
			// 增加
			String warehouseName = warehouseNameEt.getText().toString().trim();
			String warehouseAddress = warehouseAddressEt.getText().toString()
					.trim();
			String warehouseRemarks = warehouseRemarksEt.getText().toString()
					.trim();
			if (warehouseName == null || "".equals(warehouseName)) {
				Toast.makeText(this, "商档名称不能为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			if (warehouseAddress == null || "".equals(warehouseAddress)) {
				Toast.makeText(this, "商档地址不能为空!", Toast.LENGTH_SHORT).show();
				return;
			}

			User currUser=(User) MyApplication.applicationMap.get("user");
			RfidWarehouse warehouse=new RfidWarehouse(UUIDUtil.uuid(), null, warehouseName, warehouseAddress, currUser.getId(), "0", currUser.getId(), DateUtil.format(null, new Date()), null, null, warehouseRemarks);
			
			//发送请求到服务器
			
			String url=networkConfig.toUrl()+NetworkConfigConstant.ADD_WAREHOUSE;

			String json= GsonUtil.object2Json(warehouse);
			Log.e("gumy", json);
			Log.e("gumy", url);

			RequestBody requestBody = RequestBody.create(NetworkConfigConstant.JSON, json);
			OkHttpUtil.sendOkHttpRequest(url, requestBody, new Callback() {
				
				@Override
				public void onResponse(Call call, Response response) throws IOException {
					
					Log.e("gumy", response.body().string());
				}
				
				@Override
				public void onFailure(Call call, IOException e) {
					
					Log.e("gumy", "add warehouse fail with---->"+e.toString());
				}
			});

			//保存本地数据库
			ContentValues values = Object2Values.object2Values(warehouse);
			rfidDataOpenHelper.insert(TableNamesConstant.RFID_WAREHOUSE, null,values);

			//保存到SharedPreferences
			/*sp.edit().putString(DrugSystemConst.warehouseId,warehouse.getId());
			sp.edit().putString(DrugSystemConst.warehouseName,warehouse.getWarehouseName());
			sp.edit().apply();*/

			intent = new Intent();
			intent.putExtra("resultCode", 1);
			// Toast.makeText(this, "click back", Toast.LENGTH_LONG).show();
			setResult(WarehouseActivity.ADD_WAREHOUSE_CODE, intent);
			finish();
			break;
		case R.id.cal_warehouse_btn:
			// 返回
			intent = new Intent();
			intent.putExtra("resultCode", 0);
			// Toast.makeText(this, "click back", Toast.LENGTH_LONG).show();
			setResult(WarehouseActivity.ADD_WAREHOUSE_CODE, intent);
			finish();
			break;

		default:
			break;
		}

	}
	

	@Override
	protected void onDestroy() {
		// 结束Activity&从栈中移除该Activity
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.add_warehouse, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */
}
