package cn.zcbdqn.commoninventory.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.context.ScanOperate;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.db.TableNamesConstant;
import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.entity.RfidGoods;
import cn.zcbdqn.commoninventory.entity.RfidGoodsAllocation;
import cn.zcbdqn.commoninventory.entity.User;
import cn.zcbdqn.commoninventory.utils.DateUtil;
import cn.zcbdqn.commoninventory.utils.GsonUtil;
import cn.zcbdqn.commoninventory.utils.NetworkConfigConstant;
import cn.zcbdqn.commoninventory.utils.Object2Values;
import cn.zcbdqn.commoninventory.utils.OkHttpUtil;
import cn.zcbdqn.commoninventory.utils.UUIDUtil;
import cn.zcbdqn.commoninventory.utils.zkprint.ZKPrintUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddGoodsActivity extends Activity implements OnClickListener {
	private PopupWindow popWindow;
	/*private RfidGoodsAllocation rfidGoodsAllocation;
	private List<RfidGoodsAllocation> list=new ArrayList<RfidGoodsAllocation>();
	private SelectGoodsAllocationAdapter adapter;*/
	private RfidDataOpenHelper rfidDataOpenHelper;
	/**
	 * 商品编码
	 */
	private EditText goodsCodeEt;
	/**
	 * 商品名称
	 */
	private EditText goodsNameEt;
	/**
	 * 价格
	 */
	private EditText goodsPriceEt;
	/**
	 * 单位
	 */
	private EditText goodsUnitEt;
	/**
	 * 规格
	 */
	private EditText goodsStandardEt;
	/**
	 * 供应商
	 */
	private EditText goodsSupplierEt;
	/**
	 * 货位编号
	 */
	//private EditText goodsAllocationId;
	/**
	 * 选择货位
	 */
	//private Spinner selectAllocationSpi;
	/**
	 * 增加
	 */
	private Button addBtn;
	/**
	 * 返回
	 */
	private Button calBtn;

	private String defaultBluePrintAddress;
	private ScanOperate scanOperate;
	private boolean is_canScan=true;
	private boolean is_intercept = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_goods);
		rfidDataOpenHelper=new RfidDataOpenHelper(this);
		goodsCodeEt = (EditText) findViewById(R.id.goods_id_et);
		goodsNameEt = (EditText) findViewById(R.id.goods_name_et);
		goodsSupplierEt = (EditText) findViewById(R.id.goods_supplier_et);
		//goodsAllocationId = (EditText) findViewById(R.id.goods_allocation_et);
		goodsUnitEt = (EditText) findViewById(R.id.goods_unit_et);
		goodsPriceEt = (EditText) findViewById(R.id.goods_price_et);
		goodsStandardEt = (EditText) findViewById(R.id.goods_standard_et);

		defaultBluePrintAddress= (String) MyApplication.applicationMap.get("defaultBluePrintAddress");
		//selectAllocationSpi = (Spinner) findViewById(R.id.sel_allocation_spi);
		addBtn = (Button) findViewById(R.id.add_goods_btn);
		calBtn = (Button) findViewById(R.id.cal_goods_btn);

		addBtn.setOnClickListener(this);
		calBtn.setOnClickListener(this);

		scanOperate = new ScanOperate();
		scanOperate.onCreate(this, R.raw.scanok);
		scanOperate.openScannerPower(is_canScan);

		//查询已有的货位  下拉
		
		//货位下拉列表显示
		//adapter = new SelectGoodsAllocationAdapter(AddGoodsActivity.this);
		//selectAllocationSpi.setAdapter(adapter);
		//initGoodsAll();

		//选择一项时
		/*selectAllocationSpi.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				
				rfidGoodsAllocation=(RfidGoodsAllocation) parent.getItemAtPosition(position);
				if (rfidGoodsAllocation.getId()!=null){
					goodsAllocationId.setText(rfidGoodsAllocation.getGoodsAllocationName());
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
		});*/
		
		//当goodsAllocationId货位失去焦点的时候,判断是否存在,如果不存在,则提示增加
		/*goodsAllocationId.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				*//*
				if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
					InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
					goodsNameEt.setText("货位失去焦点");
					Log.e("gumy", "货位失去焦点");
				}
				*//*
				//Toast.makeText(AddGoodsActivity.this, ""+actionId+"=="+EditorInfo.IME_ACTION_DONE, 0).show();
				String goodsAllStr=goodsAllocationId.getText().toString().trim();
				if(!TextUtils.isEmpty(goodsAllStr)){
					int count=rfidDataOpenHelper.queryGoodsAllocationCount("select count(id) from "+TableNamesConstant.RFID_GOODS_ALLOCATION+" where goodsAllocationName=?" , new String[]{goodsAllStr});
					//如果数据据库没有,则增加
					if(count<=0){
						rfidGoodsAllocation=new RfidGoodsAllocation(goodsAllocationId.getText().toString(), "1", "1", "增加商品时增加", "1", DateUtil.format(null, new Date()), null);
						rfidGoodsAllocation.setId(UUIDUtil.uuid());
						//2f2be65f-0b5e-425c-acde-80065349945b
						ContentValues values=Object2Values.object2Values(rfidGoodsAllocation);
						long count1=rfidDataOpenHelper.insert(TableNamesConstant.RFID_GOODS_ALLOCATION, null, values);
						//更新货位集合
						updateGoodsAll();
						Toast.makeText(AddGoodsActivity.this,"增加"+goodsAllStr+"货位",Toast.LENGTH_SHORT).show();
					}else {
						rfidGoodsAllocation= rfidDataOpenHelper.queryGoodsAllocationt("select * from "+TableNamesConstant.RFID_GOODS_ALLOCATION+" where goodsAllocationName=? ",new String[]{goodsAllStr}).get(0);
					}
					
				}
				goodsSupplierEt.requestFocus();
				return true;
			}
		});*/

	}
	RfidGoodsAllocation bankGoodsAll=new RfidGoodsAllocation(null,"请选择货位");


	/**
	 * 初始化下拉列表
	 */
	/*public void initGoodsAll(){
		list.add(0,bankGoodsAll);
		list.addAll(rfidDataOpenHelper.queryGoodsAllocationt("select * from "+TableNamesConstant.RFID_GOODS_ALLOCATION, null));
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}*/
	/**
	 * 更新货位下拉列表
	 */
	/*public void updateGoodsAll(){
		list.add(rfidGoodsAllocation);
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}*/

	@SuppressLint({"NewApi", "WrongConstant"})
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_goods_btn:
			String goodsName=goodsNameEt.getText().toString().trim();
			String goodsCode=goodsCodeEt.getText().toString().trim();
			String goodsPrice=goodsPriceEt.getText().toString().trim();
			String unit=goodsUnitEt.getText().toString().trim();
			String supplier=goodsSupplierEt.getText().toString().trim();
			String standard=goodsStandardEt.getText().toString().trim();
			//String goodsAllId=rfidGoodsAllocation.getId();
			User user= (User) MyApplication.applicationMap.get("user");
			String createBy=user.getId();
			String createDate=DateUtil.format(null,new Date());
			//点击确定,保存商品
			RfidGoods rfidGoods=new RfidGoods(UUIDUtil.uuid(),  goodsCode, goodsName, supplier, standard, unit, Double.parseDouble(goodsPrice), "1",  null, createBy, createDate);

			//发送网络请求到服务器
			NetworkConfig networkConfig= (NetworkConfig) MyApplication.applicationMap.get("networkConfig");
			//转换json
			String json=GsonUtil.object2Json(rfidGoods);
			Log.e("gumy",json);
			String url=networkConfig.toUrl()+NetworkConfigConstant.ADD_GOODS;
			Log.e("gumy",url);
			//使用json创建requestBody
			RequestBody requestBody = RequestBody.create(NetworkConfigConstant.JSON, json);
			OkHttpUtil.sendOkHttpRequest(url, requestBody, new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					Log.e("gumy","onFailure"+e.getClass().getName());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					Log.e("gumy","onResponse:"+response.body().string());
				}
			});

			long count=rfidDataOpenHelper.insert(TableNamesConstant.RFID_GOODS,null,Object2Values.object2Values(rfidGoods));
			if(count>0){
				if (defaultBluePrintAddress!=null){
					ZKPrintUtils.print(defaultBluePrintAddress,goodsCode,true,null,false,0,this);
				}
				Toast.makeText(this,"增加"+goodsName+"成功!",Toast.LENGTH_SHORT).show();

			}
			break;
		case R.id.cal_goods_btn:
			//点击返回
			finish();
			break;

		default:
			break;
		}

	}


	/*
	*//**
	 * 按下扫描按扭
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				finish();
				break;

			case 0:
				// 松开扫描键
				// Toast.makeText(this, keyCode+"---"+is_canScan,
				// Toast.LENGTH_SHORT).show();
				break;
			case 249:// KeyEvent.KEYCODE_MUTE:
				if (is_canScan) {
					if (event.getRepeatCount() == 0) {
						scanOperate.setScannerContinuousMode();
						scanOperate.Scanning();
						Intent scannerIntent = new Intent(ScanOperate.SCN_CUST_ACTION_START);
						sendBroadcast(scannerIntent);
					}
				}
				break;
		}

		return false;
	}



	long currentTime=0;
	/**
	 * 松开扫描按扭
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.e("gumy", is_canScan + ""+keyCode);
		switch (keyCode) {
			case 0:
				// 松开扫描键
				break;
			case 249:
				// KeyEvent.KEYCODE_MUTE:
				// if (is_canScan) {
				Intent scannerIntent = new Intent(ScanOperate.SCN_CUST_ACTION_CANCEL);
				sendBroadcast(scannerIntent);
				// }
				break;
		}

		// return super.onKeyUp(keyCode, event);
		return false;
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ScanOperate.MESSAGE_TEXT:
					String code = (String) msg.obj;
					scanOperate.setVibratortime(50);
					scanOperate.mediaPlayer();
					// 扫描后获取数据处理
					if (is_intercept) {

						// onGetBarcode(code);
						if (goodsCodeEt.isFocused()) {
							goodsCodeEt.setText(code);
							// 光标定位
							goodsCodeEt.setSelection(goodsCodeEt.getText().length());
							goodsCodeEt.setFocusable(true);
							// locationEditText.requestFocus();
						}
					}
					// 连续扫描
					// Intent scannerIntent = new Intent(
					// ScanOperate.SCN_CUST_ACTION_START);
					// sendBroadcast(scannerIntent);
					break;
				case 2:

					break;
			}

		}
	};
	@Override
	protected void onResume() {
		scanOperate.mHandler = mHandler;
		// start the scanner.
		scanOperate.onResume(this);
		is_intercept = true;
		super.onResume();
	}

	public void onStop() {
		super.onStop();
		scanOperate.onStop(this);
	}

	@Override
	protected void onDestroy() {
		// 结束Activity&从栈中移除该Activity
		//MyApplication.getInstance().finishActivity(this);
		scanOperate.mHandler = null;
		scanOperate.onDestroy(this);
		//release();
		MyApplication.getInstance().finishActivity(this);
		super.onDestroy();
	}


	/**
	 * 扫描后获取的数据处理
	 * 调用api读取扫到的一维码或二维码
	 *
	 * @param code
	 *
	 */
	private void onGetBarcode(String code) {

	}

}
