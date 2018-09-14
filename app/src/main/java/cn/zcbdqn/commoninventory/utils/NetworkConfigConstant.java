package cn.zcbdqn.commoninventory.utils;

import okhttp3.MediaType;

public interface NetworkConfigConstant {

	String ADD_WAREHOUSE="/rfidWarehouse/addWarehouse"; 
	String DELETE_WAREHOUSE="/rfidWarehouse/deleteWarehouse"; 
	String UPDATE_WAREHOUSE="/rfidWarehouse/updateWarehouse"; 
	String ADD_RFID="/rfid/addRfid"; 
	String DELETE_RFID="/rfid/deleteRfid"; 
	String UPDATE_RFID="/rfid/updateRfid"; 
	String ADD_GOODS_ALLOCATION="/rfidGoodsAllocation/addGoodsAllocation"; 
	String DELETE_GOODS_ALLOCATION="/rfidGoodsAllocation/deleteGoodsAllocation"; 
	String UPDATE_GOODS_ALLOCATION="/rfidGoodsAllocation/updateGoodsAllocation"; 
	String ADD_GOODS="/rfidAndroid/addRfidGoods";
	String DELETE_GOODS="/rfidAndroid/deleteRfidGoods";
	String UPDATE_GOODS="/rfidAndroid/updateRfidGoods";


	MediaType JSON = MediaType.parse("application/json; charset=utf-8");
}
