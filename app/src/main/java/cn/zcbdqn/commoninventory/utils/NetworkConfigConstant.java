package cn.zcbdqn.commoninventory.utils;

import okhttp3.MediaType;

public interface NetworkConfigConstant {

	String ADD_WAREHOUSE="/rfidAndroid/addWarehouse";
	String DELETE_WAREHOUSE="/rfidAndroid/deleteWarehouse";
	String UPDATE_WAREHOUSE="/rfidAndroid/updateWarehouse";
	String ADD_RFID="/rfidAndroid/addRfid";
	String DELETE_RFID="/rfidAndroid/deleteRfid";
	String UPDATE_RFID="/rfidAndroid/updateRfid";
	String ADD_GOODS_ALLOCATION="/rfidAndroid/addGoodsAllocation";
	String DELETE_GOODS_ALLOCATION="/rfidAndroid/deleteGoodsAllocation";
	String UPDATE_GOODS_ALLOCATION="/rfidAndroid/updateGoodsAllocation";
	String ADD_GOODS="/rfidAndroid/addRfidGoods";
	String DELETE_GOODS="/rfidAndroid/deleteRfidGoods";
	String UPDATE_GOODS="/rfidAndroid/updateRfidGoods";


	MediaType JSON = MediaType.parse("application/json; charset=utf-8");
}
