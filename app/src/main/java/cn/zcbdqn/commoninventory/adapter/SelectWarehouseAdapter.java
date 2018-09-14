package cn.zcbdqn.commoninventory.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.zcbdqn.commoninventory.R;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;

public class SelectWarehouseAdapter extends BaseAdapter{

	private List<RfidWarehouse> list;
	private LayoutInflater inflater;
	
	
	
	public List<RfidWarehouse> getList() {
		return list;
	}

	public void setList(List<RfidWarehouse> list) {
		this.list = list;
	}

	public SelectWarehouseAdapter(Context context) {
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
			holder.warehouseNameTv=(TextView) convertView.findViewById(R.id.item_warehouse_name);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		RfidWarehouse warehouse = list.get(position);
		holder.warehouseNameTv.setText(warehouse.getWarehouseName());
		return convertView;
	}

	private static class Holder{
		TextView warehouseNameTv;
	}
}
