package cn.zcbdqn.commoninventory.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uhf.uhf.Common.Comm;
import com.uhf.uhf.UHF1.UHF1Function.AndroidWakeLock;
import com.uhf.uhf.UHF1.UHF1Function.SPconfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.adapter.InStockRfidAdapter;
import cn.zcbdqn.commoninventory.adapter.SelectGoodsAllocationAdapter;
import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.context.ScanOperate;
import cn.zcbdqn.commoninventory.db.RfidDataOpenHelper;
import cn.zcbdqn.commoninventory.db.TableNamesConstant;
import cn.zcbdqn.commoninventory.entity.Rfid;
import cn.zcbdqn.commoninventory.entity.RfidGoods;
import cn.zcbdqn.commoninventory.entity.RfidGoodsAllocation;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;
import cn.zcbdqn.commoninventory.entity.User;
import cn.zcbdqn.commoninventory.utils.DateUtil;
import cn.zcbdqn.commoninventory.utils.Object2Values;
import cn.zcbdqn.commoninventory.utils.TextUtils;
import cn.zcbdqn.commoninventory.utils.UUIDUtil;

public class InStockActivity extends Activity implements OnClickListener {

	//读rfid
    public long exittime;
    private String strMsg ;
    
    private User currUser;
    
	
	private RfidGoodsAllocation rfidGoodsAllocation;
	private PopupWindow popWindow;
	private RfidDataOpenHelper rfidDataOpenHelper;
	/**
	 * 货位对象,选中后
	 */
	private RfidWarehouse warehouse;
	/**
	 * 货位EditView
	 */
	private EditText locationEditText;
	/**
	 * 条码
	 */
	private EditText barcodeEditText;

	private String barcode;
	/**
	 * 条码对应的商品名
	 */
	private TextView goodsNameTextView;
	/**
	 * 规格
	 */
	private TextView specificationTextView;
	/**
	 * 库存
	 */
	private TextView stockNumTextView;
	/**
	 * 记录
	 */
	private TextView recordTextView;
	/**
	 * 记录
	 */
	private TextView priceTextView;
	/**
	 * 记录
	 */
	private TextView unitTextView;
	/**
	 * 品项
	 */
	//private TextView itemTextView;
	/**
	 * 总数
	 */
	private TextView numTextView;
	/**
	 * 全选
	 */
	private CheckBox itemAllCheckBox;
	private boolean is_canScan = true;
	private boolean is_intercept = true;

	private ScanOperate scanOperate;
	/**
	 * 显示扫描到的RFID列表
	 */
	private ListView scanListView;
	/**
	 * 适配器
	 */
	private InStockRfidAdapter adapter;

	/**
	 * 扫描到的RFID集合
	 */
	private List<Rfid> scanRfids=new ArrayList<Rfid>();
	/**
	 * 入库按扭
	 */
	private Button inStockBtn;
	/**
	 * 扫描按扭
	 */
	private Button scanRfidBtn;
	private Button stopScanRfidBtn;
	/**
	 * 选择货位
	 */
	//private Button selectLocationBtn;
	/**
	 * 黙认蓝牙打印机地址
	 */
	private String bluetoothDeviceAddress;

	/**
	 * 货位
	 */
	private List<RfidGoodsAllocation> goodsAllocations=new ArrayList<RfidGoodsAllocation>();
	private SelectGoodsAllocationAdapter goodsAllocationAdapter;
	/**
	 * 选择货位
	 */
	private Spinner selectAllocationSpi;
	RfidGoodsAllocation bankGoodsAll=new RfidGoodsAllocation(null,"请选择货位");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.activity_in_stock);
		//当前登录的用户
		currUser=(User) MyApplication.applicationMap.get("user");
		
		rfidDataOpenHelper=new RfidDataOpenHelper(this);
		scanOperate = new ScanOperate();
		scanOperate.onCreate(this, R.raw.scanok);
		scanOperate.openScannerPower(is_canScan);
		// 初始化控件
		locationEditText = (EditText) findViewById(R.id.goods_allocation_et);
		barcodeEditText = (EditText) findViewById(R.id.barcode_et);
		goodsNameTextView = (TextView) findViewById(R.id.goods_name_tv);
		specificationTextView = (TextView) findViewById(R.id.specification_tv);
		stockNumTextView = (TextView) findViewById(R.id.stock_num_tv);
		//recordTextView = (TextView) findViewById(R.id.record_tv);
		//itemTextView = (TextView) findViewById(R.id.item_tv);
		//numTextView = (TextView) findViewById(R.id.num_tv);
		unitTextView = (TextView) findViewById(R.id.unit_tv);
		priceTextView = (TextView) findViewById(R.id.price_tv);
		itemAllCheckBox = (CheckBox) findViewById(R.id.item_all_cb);
		inStockBtn = (Button) findViewById(R.id.in_stock_btn);
		scanRfidBtn = (Button) findViewById(R.id.scan_rfid_btn);
		stopScanRfidBtn = (Button) findViewById(R.id.stop_scan_rfid_btn);
		//selectLocationBtn = (Button) findViewById(R.id.sel_location_btn);

		// 初始化ListView
		scanListView = (ListView) findViewById(R.id.scan_lv);
		//initData();

		
		// 注册按扭点击事件
		inStockBtn.setOnClickListener(this);
		//selectLocationBtn.setOnClickListener(this);
		scanRfidBtn.setOnClickListener(this);
		stopScanRfidBtn.setOnClickListener(this);
		// 设设是否可以扫描标签,主要在是输入标签的时候使用,其他不用
		barcodeEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				is_canScan = hasFocus;
			}
		});
		

		Comm.Awl = new AndroidWakeLock((PowerManager) getSystemService(Context.POWER_SERVICE));
		// 创建适配器对象
		adapter = new InStockRfidAdapter(this);
		adapter.setList(scanRfids);
		scanListView.setAdapter(adapter);


		selectAllocationSpi = (Spinner) findViewById(R.id.sel_allocation_spi);
		//货位下拉列表显示
		goodsAllocationAdapter = new SelectGoodsAllocationAdapter(this);
		selectAllocationSpi.setAdapter(goodsAllocationAdapter);
		initGoodsAll();
		selectAllocationSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {

				rfidGoodsAllocation=(RfidGoodsAllocation) parent.getItemAtPosition(position);
				if (rfidGoodsAllocation.getId()!=null){
					locationEditText.setText(rfidGoodsAllocation.getGoodsAllocationName());
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {


			}
		});
		//条码失去焦点的时候
		barcodeEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
				} else {
					// 此处为失去焦点时的处理内容
					String barcode=barcodeEditText.getText().toString().trim();
					//查询,并显示信息
					if (!TextUtils.isEmpty(barcode)){
						//显示
						showRfidGoods(barcode);

					}
				}
			}
		});
		//当goodsAllocationId货位失去焦点的时候,判断是否存在,如果不存在,则提示增加
		locationEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
				} else {
					// 此处为失去焦点时的处理内容
					String goodsAllStr=locationEditText.getText().toString().trim();
					addRfidGoodsAllocation(goodsAllStr);
				}
			}
		});
		//按下回车键的时候
		locationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				//Toast.makeText(AddGoodsActivity.this, ""+actionId+"=="+EditorInfo.IME_ACTION_DONE, 0).show();
				String goodsAllStr=locationEditText.getText().toString().trim();

				addRfidGoodsAllocation(goodsAllStr);
				return true;
			}
		});
	}

	/**
	 * 显示商品信息
	 * @param barcode
	 */
	private void showRfidGoods(String barcode) {
		//查询本地数据库显示信息
		RfidGoods rfidGoods = rfidDataOpenHelper.queryUniqueGoods("select * from " + TableNamesConstant.RFID_GOODS + " where goodsCode=?", new String[]{barcode});
		if (rfidGoods!=null){
            goodsNameTextView.setText(getResources().getString(R.string.goods_name_tv)+rfidGoods.getGoodsName());
            specificationTextView.setText(getResources().getString(R.string.goods_standard_display)+rfidGoods.getStandard());
            stockNumTextView.setText(getResources().getString(R.string.stock_tv)+rfidGoods.getQuantity()+"");
            priceTextView.setText(getResources().getString(R.string.price_tv)+rfidGoods.getPrice()+"");
            unitTextView.setText(getResources().getString(R.string.unit_tv)+rfidGoods.getUnit()+"");
        }else{
			Log.e("gumy",barcode+"该条码不存在");
			goodsNameTextView.setText(getResources().getString(R.string.goods_name_tv));
			specificationTextView.setText(getResources().getString(R.string.goods_standard_display));
			stockNumTextView.setText(getResources().getString(R.string.stock_tv));
			priceTextView.setText(getResources().getString(R.string.price_tv));
			unitTextView.setText(getResources().getString(R.string.unit_tv));
			Toast.makeText(this,"该条码不存在!",Toast.LENGTH_SHORT).show();
		}
	}

	public long addRfidGoodsAllocation(String goodsAllStr){
		long count1=-1L;
		if(!TextUtils.isEmpty(goodsAllStr)){
			int count=rfidDataOpenHelper.queryGoodsAllocationCount("select count(id) from "+TableNamesConstant.RFID_GOODS_ALLOCATION+" where goodsAllocationName=?" , new String[]{goodsAllStr});
			//如果数据据库没有,则增加
			if(count<=0){
				rfidGoodsAllocation=new RfidGoodsAllocation(locationEditText.getText().toString(), "1", "1", "增加商品时增加", "1", DateUtil.format(null, new Date()), null);
				rfidGoodsAllocation.setId(UUIDUtil.uuid());
				//2f2be65f-0b5e-425c-acde-80065349945b
				ContentValues values= Object2Values.object2Values(rfidGoodsAllocation);
				count1=rfidDataOpenHelper.insert(TableNamesConstant.RFID_GOODS_ALLOCATION, null, values);
				//更新货位集合
				if (count1>0){
					updateGoodsAll();
					Toast.makeText(InStockActivity.this,"增加"+goodsAllStr+"货位",Toast.LENGTH_SHORT).show();
				}
			}else {
				rfidGoodsAllocation= rfidDataOpenHelper.queryGoodsAllocationt("select * from "+TableNamesConstant.RFID_GOODS_ALLOCATION+" where goodsAllocationName=? ",new String[]{goodsAllStr}).get(0);
			}
		}
		return count1;
	}

/**
 * 初始化下拉列表
 */
	public void initGoodsAll(){
		goodsAllocations.add(0,bankGoodsAll);
		goodsAllocations.addAll(rfidDataOpenHelper.queryGoodsAllocationt("select * from "+TableNamesConstant.RFID_GOODS_ALLOCATION, null));
		goodsAllocationAdapter.setList(goodsAllocations);
		goodsAllocationAdapter.notifyDataSetChanged();
	}
	/**
	 * 更新货位下拉列表
	 */
	public void updateGoodsAll(){
		goodsAllocations.add(rfidGoodsAllocation);
		goodsAllocationAdapter.setList(goodsAllocations);
		goodsAllocationAdapter.notifyDataSetChanged();
	}

	/**
	 * 扫描后获取的数据处理
	 * 调用api读取扫到的一维码或二维码
	 * 
	 * @param code
	 * 
	 */
	private void onGetBarcode(String code) {
		if (barcodeEditText.isFocused()) {
			barcodeEditText.setText(code);
			// 光标定位
			barcodeEditText.setSelection(barcodeEditText.getText().length());
			barcode = code;
			// locationEditText.requestFocus();
		} else if (locationEditText.isFocused()) {
			locationEditText.setText(code);
			// 光标定位
			locationEditText.setSelection(locationEditText.getText().length());
		}
	}

	
	public Handler uhfhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                if (Comm.is6BTag){
                	Comm.tagListSize = Comm.lsTagList6B.size();
                }else{
                	Comm.tagListSize = Comm.lsTagList.size();
                }
                Bundle bd = msg.getData();

                int readCount = bd.getInt("readCount");
                if (readCount > 0){
                	//显示读到的数量
                	//tv_state.setText(String.valueOf(readCount));
                }

                if (Comm.tagListSize > 0) {
                    showlist();
                }

                Log.e("gumy", "readCount : " + String.valueOf(readCount));

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("gumy", e.getMessage());
            }
        }
    };
    
    private void showlist() {
        try {
            int index = 1;
            int readCount = 0;//个数
            //存扫描到rfid
            //List<Rfid> rfids=new ArrayList<Rfid>();
            //扫描到rfidCode
            String epcstr = "";//
            Rfid rfid=null;
            int listIndex = 0;
            if (Comm.tagListSize > 0){
            	//tv_tags.setText(String.valueOf(Comm.tagListSize));
				//Log.e("gumy",String.valueOf(Comm.tagListSize));
            }
            if (Comm.isQuick && !Comm.isrun){
            	//tv_state.setText(String.valueOf("正在处理数据，请稍后。。。"));
            }
            if (!Comm.isQuick || !Comm.isrun) {
            	//Toast.makeText(this, "showlist:"+(Comm.tagListSize), Toast.LENGTH_SHORT).show();
                while (Comm.tagListSize > 0) {
                    if (index < 100) {
                        if (Comm.is6BTag){
                        	//读到的rfidCode
                        	epcstr=Comm.lsTagList6B.get(listIndex).strUID;
                            if (epcstr!=null&&epcstr!="") {
                                
                            	epcstr = Comm.lsTagList6B.get(listIndex).strUID.replace(" ", "");
                                //扫描到的次数
								//readCount = Comm.lsTagList6B.get(listIndex).nTotal;
								if (!rfidCodes.contains(epcstr)){
									rfidCodes.add(epcstr);
									rfid=new Rfid(epcstr, goodsNameTextView.getText().toString(), 1, true, currUser.getId(), DateUtil.format(null, new Date()));
									//int mRSSI=Integer.parseInt(lsTagList.get(ListIndex).strRSSI);
									index++;
									//增加到集合中
									if (!scanRfids.contains(rfid)){
										scanRfids.add(rfid);
									}
								}
                            }
                        }else {
                            epcstr = Comm.lsTagList.get(listIndex).strEPC;
                             if ( Comm.dt == Comm.DeviceType.supoin_JT&&Comm.To433Index<index ) {
                                 Comm.mWirelessMg.writeTo433(epcstr + "\n");
                                 Comm.To433Index++;
                             }
                            if (epcstr!=null&&epcstr!="") {
                                epcstr = Comm.lsTagList.get(listIndex).strEPC.replace(" ", "");
                                if (epcstr.length()>24){
                                    epcstr=epcstr.substring(0,24)+"\r\n"+epcstr.substring(24);
                                }
                                //扫到的次数
                                //readCount = Comm.lsTagList6B.get(listIndex).nTotal;
								if (!rfidCodes.contains(epcstr)){
									rfidCodes.add(epcstr);
									rfid=new Rfid(epcstr, goodsNameTextView.getText().toString(), 1, true, currUser.getId(), DateUtil.format(null, new Date()));
									//int mRSSI=Integer.parseInt(lsTagList.get(ListIndex).strRSSI);
									index++;
									//增加到集合中
									if (!scanRfids.contains(rfid)){
										scanRfids.add(rfid);
									}
								}

                            }
                        }
                    }
                    listIndex++;
                    Comm.tagListSize--;
                    Log.e("gumy","epcstr:"+epcstr);
                }

            }
            if (!Comm.isQuick || !Comm.isrun) {
                //adapter
                // layout为listView的布局文件，包括三个TextView，用来显示三个列名所对应的值
                // ColumnNames为数据库的表的列名
                // 最后一个参数是int[]类型的，为view类型的id，用来显示ColumnNames列名所对应的值。view的类型为TextView
                //listView.setAdapter(adapter);
            	// 创建适配器对象
        		/*adapter = new InStockRfidAdapter(this);
        		adapter.setList(scanRfids);
        		scanListView.setAdapter(adapter);*/
				//如果集合中的数量大于rfidCodesSize,则刷新listview
				if (scanRfids.size()>rfidCodesSize){
					rfidCodesSize++;
					adapter.setList(scanRfids);
					adapter.notifyDataSetChanged();
				}
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (!Comm.isrun){
            //tv_state.setText(String.valueOf("等待操作..."));
        }
    }
	int rfidCodesSize=0;
    private List<String> rfidCodes=new ArrayList<String>();

	/**
	 * 连接rfid扫描器
	 */
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
               Comm.mInventoryHandler= uhfhandler;

                Bundle bd = msg.getData();
                strMsg = bd.get("Msg").toString();
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


	/**
	 * 按扭点击事件
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.in_stock_btn:
			// Toast.makeText(this, "in_stock_btn", Toast.LENGTH_SHORT).show();
			// 点击入库
			// 循环打印一维码标签
			Log.e("gumy", scanRfids.toString());

			break;
		case R.id.scan_rfid_btn:
			// 扫描RFID
			startScanRfid();
			break;
		case R.id.stop_scan_rfid_btn:
			//停止扫描
			stopScanRfid();
			break;

		/*case R.id.sel_location_btn:
			// 选择货位
			// popwindow布局文件
			// 选择货位
			// popwindow布局文件
			View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_select_location, null);

			List<RfidGoodsAllocation> list = rfidDataOpenHelper.queryGoodsAllocationt("select * from "+TableNamesConstant.RFID_GOODS_ALLOCATION, null);

			ListView lv = (ListView) view.findViewById(R.id.location_lv);

			SelectGoodsAllocationAdapter adapter = new SelectGoodsAllocationAdapter(this);
			adapter.setList(list);
			lv.setAdapter(adapter);

			popWindow = new PopupWindow(view, 360, 400);
			popWindow.setOutsideTouchable(true);
			popWindow.showAsDropDown(locationEditText, 0, 20, Gravity.CENTER);
			// 取消
			Button canBtn = (Button) view.findViewById(R.id.pop_can_loc_btn);
			canBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					popWindow.dismiss();
				}
			});
			// 选中
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					rfidGoodsAllocation = (RfidGoodsAllocation) parent
							.getItemAtPosition(position);
					locationEditText.setText(rfidGoodsAllocation
							.getGoodsAllocationName());
					popWindow.dismiss();
				}
			});
			break;*/
		default:
			break;
		}
	}


	public void startScanRfid(){
		//指定扫描的处理器
		Comm.mInventoryHandler= uhfhandler;

		Comm.Awl.WakeLock();
		Comm.startScan();
		//隐藏扫描
		scanRfidBtn.setVisibility(View.GONE);
		//显示停上扫描
		stopScanRfidBtn.setVisibility(View.VISIBLE);
	}
	/**
	 * 停止扫描RFID
	 */
	public void stopScanRfid(){
		// 停止扫描RFID
		//Toast.makeText(this, "停止扫描RFID", Toast.LENGTH_SHORT).show();
		//显示扫描
		scanRfidBtn.setVisibility(View.VISIBLE);
		//隐藏停上扫描
		stopScanRfidBtn.setVisibility(View.GONE);
		Comm.Awl.ReleaseWakeLock();
		Comm.stopScan();
		showlist();
	}

	/*
	*//**
	 * 按下扫描按扭
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(Comm.isrun){
				Log.e("gumy",currentTime+"::::"+(System.currentTimeMillis()-currentTime)+"::::"+Comm.isrun);
				if (System.currentTimeMillis()-currentTime>2000) {
					currentTime=System.currentTimeMillis();
					stopScanRfid();
					break;
				}
			}
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
					Intent scannerIntent = new Intent(
							ScanOperate.SCN_CUST_ACTION_START);
					sendBroadcast(scannerIntent);
				}
			}
			break;
		}

		// TODO Auto-generated method stub
		// return super.onKeyDown(keyCode, event);
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
			Intent scannerIntent = new Intent(
					ScanOperate.SCN_CUST_ACTION_CANCEL);
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
						if (barcodeEditText.isFocused()) {
							barcodeEditText.setText(code);
							// 光标定位
							barcodeEditText.setSelection(barcodeEditText.getText()
									.length());
							barcode = code;
							barcodeEditText.setFocusable(true);
							// locationEditText.requestFocus();
							if (!TextUtils.isEmpty(barcode)){
								//显示
								showRfidGoods(barcode);

							}
						} else if (locationEditText.isFocused()) {
							locationEditText.setText(code);
							// 光标定位
							locationEditText.setSelection(locationEditText
									.getText().length());
							locationEditText.setFocusable(true);
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
		stopScanRfid();
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
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//initDevice();
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

}
