package cn.zcbdqn.commoninventory.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.entity.RfidGoodsAllocation;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;

public class SelectGoodsAllocationAdapter extends BaseAdapter{

	private List<RfidGoodsAllocation> list;
	private LayoutInflater inflater;
	
	
	
	public List<RfidGoodsAllocation> getList() {
		return list;
	}

	public void setList(List<RfidGoodsAllocation> list) {
		this.list = list;
	}

	public SelectGoodsAllocationAdapter(Context context) {
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list==null?null:list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder=null;
		if(convertView==null){
			holder=new Holder();
			convertView=inflater.inflate(R.layout.item_select_warehouse, null);
			holder.goodsAllocationNameTv=(TextView) convertView.findViewById(R.id.item_warehouse_name);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		RfidGoodsAllocation goodsAllocation = list.get(position);
		holder.goodsAllocationNameTv.setText(goodsAllocation.getGoodsAllocationName());
		return convertView;
	}

	private static class Holder{
		TextView goodsAllocationNameTv;
	}
}
