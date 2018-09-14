package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.adapter.OutStockRfidAdapter;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.entity.Rfid;

public class OutStockActivity extends Activity {

	private ListView scanListView;
	private OutStockRfidAdapter adapter;
	private List<Rfid> scanRfids;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_out_stock);
		
		scanListView=(ListView) findViewById(R.id.scan_lv);
		initData();
		adapter=new OutStockRfidAdapter(this);
		adapter.setList(scanRfids);
		scanListView.setAdapter(adapter);
	}

	
	/**
	 * 测试数据
	 */
	private void initData(){
		scanRfids=new ArrayList<Rfid>();
		scanRfids.add(new Rfid("12345678998800000000", "", "", "广州", "JavaWeb"));
		scanRfids.add(new Rfid("12345678994200000000", "", "", "广州", "SSM"));
		scanRfids.add(new Rfid("12345678998200000000", "", "", "广州", "SSH"));
		scanRfids.add(new Rfid("12345678995400000000", "", "", "广州", "JavaOOP"));
		scanRfids.add(new Rfid("12345678998400000000", "", "", "广州", "Java基础"));
		scanRfids.add(new Rfid("12345678998500000000", "", "", "广州", "Java高级"));
		scanRfids.add(new Rfid("12345678998700000000", "", "", "广州", "JavaScript"));
		scanRfids.add(new Rfid("12345678998900000000", "", "", "广州", "HTML"));
		scanRfids.add(new Rfid("12345678994400000000", "", "", "广州", "CSS"));
		scanRfids.add(new Rfid("12345678993300000000", "", "", "广州", "Mysql"));
		scanRfids.add(new Rfid("12345678992200000000", "", "", "广州", "Oracle"));
		scanRfids.add(new Rfid("12345678992300000000", "", "", "广州", "SpringBoot"));
		scanRfids.add(new Rfid("12345678994300000000", "", "", "广州", "Struts"));
		scanRfids.add(new Rfid("12345678992400000000", "", "", "广州", "Hibernate"));
		scanRfids.add(new Rfid("12345678995500000000", "", "", "广州", "JSON"));
		scanRfids.add(new Rfid("12345678994200000000", "", "", "广州", "JQuery"));
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}
	
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.out_stock, menu);
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
