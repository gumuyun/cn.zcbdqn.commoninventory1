package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.context.MyApplication;

public class MainSettingActivity extends Activity implements OnItemClickListener, OnClickListener {

	/**
	 * 设置打印机请求码
	 */
	public static final int SETTING_PRINT_CODE=1;
	private RelativeLayout footMainMenuRl;
	private RelativeLayout footBusinessRl;
	
	private ListView settingListView;
	private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.activity_setting_main);
		//获取ListView
		settingListView=(ListView) findViewById(R.id.stting_lv);
		footMainMenuRl=(RelativeLayout) findViewById(R.id.setting_foot_menu_rl);
		footBusinessRl=(RelativeLayout) findViewById(R.id.setting_foot_business_rl);
		//获取资源
		String[] settingNames=getResources().getStringArray(R.array.settings_strs);
		String[] settingIcons=getResources().getStringArray(R.array.settings_icon);
		//准备数据
		for (int i = 0; i < settingNames.length; i++) {
			Map<String ,Object> map=new HashMap<String, Object>();
			//获得资源ID
			int resId=getResources().getIdentifier(settingIcons[i], "drawable", this.getBaseContext().getPackageName());
			//Log.i("gumy---resId:", resId+",icon name:"+settingIcons[i]);
			map.put("icon", resId);
			map.put("name", settingNames[i]);
			
			list.add(map);
		}
		//创建Adapter
		SimpleAdapter adapter=new SimpleAdapter(this, list, 
				R.layout.item_setting, 
				new String[]{"icon","name"}, 
				new int[]{R.id.item_setting_icon,R.id.item_setting_name});
	
		settingListView.setAdapter(adapter);
		
		settingListView.setOnItemClickListener(this);
		
		footMainMenuRl.setOnClickListener(this);
		footBusinessRl.setOnClickListener(this);
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent,
			View view, int position,
			long id) {
		String[] settingIcons=getResources().getStringArray(R.array.settings_icon);
		Intent intent=null;
		switch (settingIcons[position]) {
		case "setting_print":
			//设置蓝牙打印机界面
			intent=new Intent(this, SettingPrintActivity.class);
			startActivityForResult(intent, SETTING_PRINT_CODE);
			break;
		case "setting_network":
			//设置网络界面
			intent=new Intent(this, SettingNetworkActivity.class);
			startActivityForResult(intent, SETTING_PRINT_CODE);
			break;

		default:
			break;
		}
	}
	
	//别的界面返回结果后调用
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case SETTING_PRINT_CODE:
			if(resultCode==1){
				//Toast.makeText(this, "设置打印机成功", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_foot_menu_rl:
			//进入主扫描界面
			Intent intent=new Intent(this,MainMenuActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.setting_foot_business_rl:
			//进入仓库设置
			intent=new Intent(this, WarehouseActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
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

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_main, menu);
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
