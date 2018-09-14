package cn.zcbdqn.commoninventory.adapter;

import java.util.List;

import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.entity.Rfid;
import cn.zcbdqn.commoninventory.utils.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class InStockRfidAdapter extends BaseAdapter {
	private List<Rfid> list;
	private LayoutInflater inflater;
	private Activity context;
	public void setList(List<Rfid> list) {
		this.list = list;
	}
	
	public InStockRfidAdapter(Activity context){
		inflater=LayoutInflater.from(context);
		this.context=context;
	}

	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Rfid getItem(int position) {
		return list==null?null:list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder=null;
		if(convertView==null){
			holder=new Holder();
			convertView=inflater.inflate(R.layout.item_in_stock_scan_rfid, null);
			holder.serialNoTv=(TextView) convertView.findViewById(R.id.item_num);
			holder.rfidCodeTv=(TextView) convertView.findViewById(R.id.item_rfid_code);
			holder.itemCb=(CheckBox) convertView.findViewById(R.id.item_cb);
			holder.goodsCountEt=(EditText) convertView.findViewById(R.id.item_goods_count_in);
			holder.ll=(LinearLayout) convertView.findViewById(R.id.item_in_stock_ll);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		Rfid rfid=list.get(position);
		
		holder.serialNoTv.setText(position+1+"");
		holder.rfidCodeTv.setText(rfid.getRfidCode());
		holder.itemCb.setChecked(rfid.isChecked());
		holder.goodsCountEt.setText(rfid.getGoodsCounts()+"");
		//获得焦点选中文本
		holder.goodsCountEt.setSelectAllOnFocus(true);
		//数量-失去焦点或获得焦点-取出数据
		holder.goodsCountEt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				EditText currentEditText=(EditText) view;
				if (hasFocus) {
					Log.i("gumy", "第"+position+"个goodsCountEt获得焦点!");
				}else{
					Log.i("gumy", "第"+position+"个goodsCountEt失去焦点!");
					Log.i("gumy", "获得第"+position+"个goodsCountEt的值:"+currentEditText.getText().toString());
					String count=currentEditText.getText().toString();
					try {
						Integer goodsCount = StringUtils.str2Integer(count);
						list.get(position).setGoodsCounts(goodsCount);
					} catch (Exception e) {
						currentEditText.setText(1+"");
					}
				}
			}
		});
		//点击小键盘完成时操作
		/*holder.goodsCountEt.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (EditorInfo.IME_ACTION_DONE==actionId) {
					//点击小键盘完成时操作,关闭软键盘
					InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
				}
				
				return false;
			}
		});*/
		//选择该Rfid
		holder.itemCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				list.get(position).setChecked(isChecked);
			}
		});
		return convertView;
	}

	
	private static class Holder{
		TextView serialNoTv;
		TextView rfidCodeTv;
		EditText goodsCountEt;
		CheckBox itemCb;
		//行布局的外层
		LinearLayout ll;
	}
}
