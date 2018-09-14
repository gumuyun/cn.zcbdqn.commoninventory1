package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.db.TableNamesConstant;
import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.utils.DateUtil;
import cn.zcbdqn.commoninventory.utils.StringUtils;
import cn.zcbdqn.commoninventory.utils.UUIDUtil;

public class SettingNetworkActivity extends Activity implements OnClickListener {
	
	private EditText serverIpEt;
	private EditText serverPortEt;
	private EditText contextPathEt;
	private Button smt;
	private ImageView back;
	
	private RfidDataOpenHelper rfidDataOpenHelper;
	
	private NetworkConfig networkConfig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_setting_network);
		rfidDataOpenHelper=new RfidDataOpenHelper(this);
		
		serverIpEt=(EditText) findViewById(R.id.server_ip_et);
		serverPortEt=(EditText) findViewById(R.id.server_port_et);
		contextPathEt=(EditText) findViewById(R.id.context_path_et);
		smt=(Button) findViewById(R.id.setting_network_smt);
		back=(ImageView) findViewById(R.id.setting_network_back);
		
		smt.setOnClickListener(this);
		back.setOnClickListener(this);
		
		//加载以前配置过的数据
		networkConfig = (NetworkConfig) MyApplication.applicationMap.get("networkConfig");
		if(networkConfig!=null){
			serverIpEt.setText(networkConfig.getServerIp());
			serverPortEt.setText(networkConfig.getServerPort()+"");
			contextPathEt.setText(networkConfig.getContextPath());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_network_smt:
			//保存数据
			String serverIp = serverIpEt.getText().toString();
			String serverPort = serverPortEt.getText().toString();
			String contextPath = contextPathEt.getText().toString();
			ContentValues values=new ContentValues();
			values.put("server_ip", serverIp);
			values.put("server_port", serverPort);
			values.put("context_path", contextPath);
			if(networkConfig==null || networkConfig!=null && networkConfig.getId()==null){
				String uuid = UUIDUtil.uuid();
				values.put("id", uuid);
				values.put("create_by", 1);
				String createDate = DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date());
				values.put("create_date", createDate);
				rfidDataOpenHelper.insert(TableNamesConstant.RFID_NETWORK_CONFIG, null, values);
				networkConfig=new NetworkConfig(uuid, serverIp, StringUtils.str2Integer(serverPort), contextPath, "1", "", createDate, "");
			}else if(networkConfig!=null && networkConfig.getId()!=null){

				values.put("update_by", 1);
				String updateDate = DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date());
				values.put("update_date", updateDate);

				rfidDataOpenHelper.update(TableNamesConstant.RFID_NETWORK_CONFIG, values, " id=? ", new String[]{networkConfig.getId()});
			}
			MyApplication.applicationMap.put("networkConfig", networkConfig);
			Toast.makeText(this, "保存网络配置成功!", Toast.LENGTH_SHORT).show();
			break;

		case R.id.setting_network_back:
			
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
	

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_network, menu);
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
