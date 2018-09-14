package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import cn.zcbdqn.commoninventory.R.layout;
import cn.zcbdqn.commoninventory.context.MyApplication;

/**
 * 盘点界面
 * @author gumuyun
 *
 */
public class TakeStockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(layout.activity_take_stock);
	}


	@Override
	protected void onDestroy() {
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}
	
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_stock, menu);
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
