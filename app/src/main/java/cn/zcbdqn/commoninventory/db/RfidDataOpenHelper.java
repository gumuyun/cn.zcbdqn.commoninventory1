package cn.zcbdqn.commoninventory.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.entity.RfidGoods;
import cn.zcbdqn.commoninventory.entity.RfidGoodsAllocation;
import cn.zcbdqn.commoninventory.entity.RfidWarehouse;

/**
 * 数据库操作类
 * @author gumuyun
 *
 */
public class RfidDataOpenHelper extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;
	private static final String DB_NAME = "rfid.db";
	

	public RfidDataOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public RfidDataOpenHelper(Context context) {
		this(context,DB_NAME,null,VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//仓库表
		StringBuffer createWarehouseTb=new StringBuffer();
		createWarehouseTb.append("CREATE TABLE rfid_warehouse (");
		createWarehouseTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createWarehouseTb.append("serverId varchar(32),");
		createWarehouseTb.append("warehouseName varchar(255) ,");
		createWarehouseTb.append("warehouseAddress varchar(255) ,");
		createWarehouseTb.append("userCode varchar(255),");
		createWarehouseTb.append("status varchar(11) NOT NULL,");
		createWarehouseTb.append("createBy varchar(64) NOT NULL,");
		createWarehouseTb.append("createDate varchar(64) NOT NULL,");
		createWarehouseTb.append("updateBy varchar(64),");
		createWarehouseTb.append("updateDate varchar(64),");
		createWarehouseTb.append("remarks varchar(255)");
		createWarehouseTb.append(")");
		  
		db.execSQL(createWarehouseTb.toString());
		
		//网络设置表
		StringBuffer createNetworkTb=new StringBuffer();
		createNetworkTb.append("CREATE TABLE "+TableNamesConstant.RFID_NETWORK_CONFIG+" (");
		createNetworkTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createNetworkTb.append("serverIp varchar(32) NOT NULL,");
		createNetworkTb.append("serverPort integer(5) NOT NULL,");
		createNetworkTb.append("contextPath varchar(255),");
		createNetworkTb.append("createBy varchar(32) NOT NULL,");
		createNetworkTb.append("createDate varchar(64) NOT NULL,");
		createNetworkTb.append("updateBy varchar(32),");
		createNetworkTb.append("updateDate varchar(64)");
		createNetworkTb.append(")");
		db.execSQL(createNetworkTb.toString());
		/*
		 * 商品表
		 * CREATE TABLE `rfid_goods` (
		  `id` varchar(255) NOT NULL COMMENT ' 商品编号',
		  `goods_name` varchar(255) NOT NULL COMMENT '商品名字',
		  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
		  `standard` varchar(255) DEFAULT NULL COMMENT '规格',
		  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
		  `price` double(255,2) DEFAULT NULL COMMENT '商品价格',
		  `quantity` int(11) DEFAULT NULL COMMENT '库存量',
		  `status` char(11) NOT NULL COMMENT '状态（0正常 1删除 2停用）',
		  `create_by` varchar(255) NOT NULL COMMENT '创建人',
		  `create_date` datetime NOT NULL COMMENT '创建日期',
		  `update_by` varchar(255) NOT NULL COMMENT '更新人',
		  `update_date` datetime NOT NULL COMMENT '更新日期',
		  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
		  `spare1` varchar(255) DEFAULT NULL COMMENT '备用1',
		  `spare2` varchar(255) DEFAULT NULL COMMENT '备用2',
		  `spare3` varchar(255) DEFAULT NULL COMMENT '备用3',
		  `spare4` varchar(255) DEFAULT NULL COMMENT '备用4',
		  `spare5` varchar(255) DEFAULT NULL COMMENT '备用5',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';
				 */
		StringBuffer createGoodsTb=new StringBuffer();
		createGoodsTb.append("CREATE TABLE rfid_goods (");
		createGoodsTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createGoodsTb.append("goodsCode varchar(100) NOT NULL,");
		createGoodsTb.append("serverId varchar(32) ,");
		createGoodsTb.append("goodsName varchar(255) NOT NULL,");
		createGoodsTb.append("supplier varchar(255) NOT NULL,");
		createGoodsTb.append("standard varchar(255),");
		createGoodsTb.append("unit varchar(255),");
		createGoodsTb.append("price double,");
		createGoodsTb.append("quantity integer(10),");
		createGoodsTb.append("status varchar(11),");
		createGoodsTb.append("remarks varchar text,");
		createGoodsTb.append("createBy varchar(32) NOT NULL,");
		createGoodsTb.append("createDate varchar(64) NOT NULL,");
		createGoodsTb.append("updateBy varchar(32),");
		createGoodsTb.append("updateDate varchar(64)");
		createGoodsTb.append(")");
		db.execSQL(createGoodsTb.toString());
		
		/*
		 * 货位
		 * 
		 * CREATE TABLE `rfid_goods_allocation` (
		  `id` varchar(255) NOT NULL,
		  `goods_allocation_name` varchar(255) NOT NULL COMMENT '货位名字',
		  `warehouse_id` varchar(255) NOT NULL COMMENT '所属仓库编号',
		  `status` char(11) NOT NULL COMMENT '状态（0正常 1删除 2停用）',
		  `create_by` varchar(64) NOT NULL COMMENT '创建人',
		  `create_date` datetime NOT NULL COMMENT '创建日期',
		  `update_by` varchar(64) NOT NULL COMMENT '更新人',
		  `update_date` datetime NOT NULL COMMENT '更新时间',
		  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
		  PRIMARY KEY (`id`),
		  KEY `rfid_warehouse_code` (`warehouse_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货位表';

		 */
		StringBuffer createGoodsAllocationTb=new StringBuffer();
		createGoodsAllocationTb.append("CREATE TABLE rfid_goods_allocation (");
		createGoodsAllocationTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createGoodsAllocationTb.append("serverId varchar(32) ,");
		createGoodsAllocationTb.append("goodsAllocationName varchar(255) NOT NULL,");
		createGoodsAllocationTb.append("warehouseId varchar(255) NOT NULL,");
		createGoodsAllocationTb.append("status varchar(11),");
		createGoodsAllocationTb.append("remarks varchar text,");
		createGoodsAllocationTb.append("createBy varchar(32) NOT NULL,");
		createGoodsAllocationTb.append("createDate varchar(64) NOT NULL,");
		createGoodsAllocationTb.append("updateBy varchar(32),");
		createGoodsAllocationTb.append("updateDate varchar(64)");
		createGoodsAllocationTb.append(")");
		db.execSQL(createGoodsAllocationTb.toString());
		
		/*
		 * rfid表
		 * CREATE TABLE `rfid` (
		  `id` varchar(255) NOT NULL COMMENT '编号',
		  `rfid_code` varchar(255) NOT NULL COMMENT 'rfid序号',
		  `goods_id` varchar(255) NOT NULL COMMENT '商品编号',
		  `warehouse_id` varchar(255) NOT NULL COMMENT '仓库编号',
		  `warehouse_name` varchar(255) NOT NULL COMMENT '仓库名字',
		  `goods_name` varchar(255) NOT NULL COMMENT '商品名字',
		  `goods_counts` varchar(255) NOT NULL,
		  `status` char(11) NOT NULL COMMENT '状态',
		  `create_by` varchar(64) NOT NULL COMMENT '创建人',
		  `create_date` datetime NOT NULL COMMENT '创建时间',
		  `update_by` varchar(64) NOT NULL COMMENT '更新人',
		  `update_date` datetime NOT NULL COMMENT '更新时间',
		  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
		  PRIMARY KEY (`id`),
		  KEY `goods_id` (`goods_id`),
		  KEY `warehouse_id` (`warehouse_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='rfid管理';
		 * 
		 */
		StringBuffer createRfidTb=new StringBuffer();
		createRfidTb.append("CREATE TABLE rfid (");
		createRfidTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createRfidTb.append("serverId varchar(32) ,");
		createRfidTb.append("rfidCode varchar(255) NOT NULL,");
		createRfidTb.append("goodsId varchar(32) NOT NULL,");
		createRfidTb.append("goodsName varchar(255),");
		createRfidTb.append("warehouseId varchar(32) NOT NULL,");
		createRfidTb.append("warehouseName varchar(255),");
		createRfidTb.append("goodsCounts integer(10) NOT NULL,");
		createRfidTb.append("status varchar(11),");
		createRfidTb.append("remarks varchar text,");
		createRfidTb.append("createBy varchar(32) NOT NULL,");
		createRfidTb.append("createDate varchar(64) NOT NULL,");
		createRfidTb.append("updateBy varchar(32),");
		createRfidTb.append("updateDate varchar(64)");
		createRfidTb.append(")");
		db.execSQL(createRfidTb.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	/**
	 * 增加数据
	 * @param tableName 表名
	 * @param nullColumnHack 空列 
	 * @param values 字段-值
	 */
	public long insert(String tableName,String nullColumnHack,ContentValues values){
		SQLiteDatabase db = getReadableDatabase();
		return db.insert(tableName, null, values);
	}
	
	/**
	 * 更新
	 * @param tableName 表名
	 * @param values 字段-值
	 * @param whereClause 条件
	 * @param whereArgs 条件的值
	 * @return 影响的行数
	 */
	public int update(String tableName,ContentValues values,String whereClause,String[] whereArgs){
		SQLiteDatabase db = getReadableDatabase();
		return db.update(tableName, values, whereClause, whereArgs);
	}
	
	/**
	 * 查询网络配置表数据
	 * @return
	 */
	public NetworkConfig queryNetworkConfig(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TableNamesConstant.RFID_NETWORK_CONFIG, null, null, null, null, null, null);
		NetworkConfig network=null;
		if(cursor.moveToFirst()){
			String id=cursor.getString(cursor.getColumnIndex("id"));
			String serverIp=cursor.getString(cursor.getColumnIndex("serverIp"));
			int serverPort=cursor.getInt(cursor.getColumnIndex("serverPort"));
			String contextPath=cursor.getString(cursor.getColumnIndex("contextPpath"));
			String createBy=cursor.getString(cursor.getColumnIndex("createBy"));
			String createDate=cursor.getString(cursor.getColumnIndex("createDate"));
			String updateBy=cursor.getString(cursor.getColumnIndex("updateBy"));
			String updateDate=cursor.getString(cursor.getColumnIndex("updateDate"));
			network=new NetworkConfig(id, serverIp, serverPort, contextPath, createBy, updateBy, createDate, updateDate);
		}
		return network;
	}
	
	/**
	 * 查询仓库 表
	 * @param selection 条件,
	 * @param selectionArgs 条件值
	 * createWarehouseTb.append("CREATE TABLE rfid_warehouse (");
		createWarehouseTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createWarehouseTb.append("serverId varchar(32),");
		createWarehouseTb.append("warehouseName varchar(255) ,");
		createWarehouseTb.append("warehouseAddress varchar(255) ,");
		createWarehouseTb.append("userCode varchar(255),");
		createWarehouseTb.append("status varchar(11) NOT NULL,");
		createWarehouseTb.append("createBy varchar(64) NOT NULL,");
		createWarehouseTb.append("createDate varchar(64) NOT NULL,");
		createWarehouseTb.append("updateBy varchar(64),");
		createWarehouseTb.append("updateDate varchar(64),");
		createWarehouseTb.append("remarks varchar(255)");
		createWarehouseTb.append(")");
	 * 
	 * @return
	 */
	public List<RfidWarehouse> queryWarehouse(String selection ,String[] selectionArgs ){
		List<RfidWarehouse> list=new ArrayList<RfidWarehouse>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TableNamesConstant.RFID_WAREHOUSE, null, selection, selectionArgs, null, null, null);
		while (cursor.moveToNext()){ 
			String id=cursor.getString(cursor.getColumnIndex("id"));
			String serverId=cursor.getString(cursor.getColumnIndex("serverId"));
			String warehouseName=cursor.getString(cursor.getColumnIndex("warehouseName"));
			String warehouseAddress=cursor.getString(cursor.getColumnIndex("warehouseAddress"));
			String userCode=cursor.getString(cursor.getColumnIndex("userCode"));
			String status=cursor.getString(cursor.getColumnIndex("status"));
			String remarks=cursor.getString(cursor.getColumnIndex("remarks"));
			String createBy=cursor.getString(cursor.getColumnIndex("createBy"));
			String createDate=cursor.getString(cursor.getColumnIndex("createDate"));
			String updateBy=cursor.getString(cursor.getColumnIndex("updateBy"));
			String updateDate=cursor.getString(cursor.getColumnIndex("updateDate"));
			RfidWarehouse warehouse=new RfidWarehouse(id, serverId,warehouseName, warehouseAddress, userCode, status, createBy, createDate, updateBy, updateDate, remarks);
			list.add(warehouse);
		}
		return list;
		
	}
	/**
	 * 查询货位数量
	 * @param selectionArgs
	 * @return
	 */
	public int queryGoodsAllocationCount(String sql ,String[] selectionArgs){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		if(cursor.moveToFirst()){
			return cursor.getInt(0);
		}
		return -1;
	}

	/**
	 * 查询货位
	 * @param sql select * from 表名 where 条件=?
	 * @param selectionArgs
	 * @return
	 */
	public List<RfidGoodsAllocation> queryGoodsAllocationt(String sql ,String[] selectionArgs){
		List<RfidGoodsAllocation> list=new ArrayList<RfidGoodsAllocation>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while(cursor.moveToNext()){
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String serverId = cursor.getString(cursor.getColumnIndex("serverId"));
			String goodsAllocationName = cursor.getString(cursor.getColumnIndex("goodsAllocationName"));
			String warehouseId = cursor.getString(cursor.getColumnIndex("warehouseId"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
			String createBy=cursor.getString(cursor.getColumnIndex("createBy"));
			String createDate=cursor.getString(cursor.getColumnIndex("createDate"));
			String updateBy=cursor.getString(cursor.getColumnIndex("updateBy"));
			String updateDate=cursor.getString(cursor.getColumnIndex("updateDate"));
			
			RfidGoodsAllocation rfidGoodsAllocation=new RfidGoodsAllocation(id, serverId,goodsAllocationName, warehouseId, status, remarks, createBy, updateBy, createDate, updateDate, null);
			list.add(rfidGoodsAllocation);
		}
		return list;
	}

	public List<RfidGoods> queryGoods(String sql , String[] selectionArgs){
		List<RfidGoods> goodsList=new ArrayList<RfidGoods>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		/*
			createGoodsTb.append("CREATE TABLE rfid_goods (");
		createGoodsTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createGoodsTb.append("goodsCode varchar(100) NOT NULL,");
		createGoodsTb.append("serverId varchar(32) ,");
		createGoodsTb.append("goodsName varchar(255) NOT NULL,");
		createGoodsTb.append("supplier varchar(255) NOT NULL,");
		createGoodsTb.append("standard varchar(255),");
		createGoodsTb.append("unit varchar(255),");
		createGoodsTb.append("price double,");
		createGoodsTb.append("quantity integer(10),");
		createGoodsTb.append("status varchar(11),");
		createGoodsTb.append("remarks varchar text,");
		createGoodsTb.append("createBy varchar(32) NOT NULL,");
		createGoodsTb.append("createDate varchar(64) NOT NULL,");
		createGoodsTb.append("updateBy varchar(32),");
		createGoodsTb.append("updateDate varchar(64)");
		 */
		while (cursor.moveToNext()){
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String goodsCode = cursor.getString(cursor.getColumnIndex("goodsCode"));
			String serverId = cursor.getString(cursor.getColumnIndex("serverId"));
			String goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
			String supplier = cursor.getString(cursor.getColumnIndex("supplier"));
			String standard = cursor.getString(cursor.getColumnIndex("standard"));
			String unit = cursor.getString(cursor.getColumnIndex("unit"));
			double price = cursor.getDouble(cursor.getColumnIndex("price"));
			int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			String createBy = cursor.getString(cursor.getColumnIndex("createBy"));
			String createDate = cursor.getString(cursor.getColumnIndex("createDate"));
			String updateBy = cursor.getString(cursor.getColumnIndex("updateBy"));
			String updateDate = cursor.getString(cursor.getColumnIndex("updateDate"));
			String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
			goodsList.add(new RfidGoods(id, goodsCode, serverId, goodsName, supplier, standard, unit, price, quantity, status, remarks, createBy, updateBy, createDate, updateDate));
		}
		return goodsList;
	}
	public RfidGoods queryUniqueGoods(String sql , String[] selectionArgs){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		/*
			createGoodsTb.append("CREATE TABLE rfid_goods (");
		createGoodsTb.append("id varchar(32) PRIMARY KEY NOT NULL ,");
		createGoodsTb.append("goodsCode varchar(100) NOT NULL,");
		createGoodsTb.append("serverId varchar(32) ,");
		createGoodsTb.append("goodsName varchar(255) NOT NULL,");
		createGoodsTb.append("supplier varchar(255) NOT NULL,");
		createGoodsTb.append("standard varchar(255),");
		createGoodsTb.append("unit varchar(255),");
		createGoodsTb.append("price double,");
		createGoodsTb.append("quantity integer(10),");
		createGoodsTb.append("status varchar(11),");
		createGoodsTb.append("remarks varchar text,");
		createGoodsTb.append("createBy varchar(32) NOT NULL,");
		createGoodsTb.append("createDate varchar(64) NOT NULL,");
		createGoodsTb.append("updateBy varchar(32),");
		createGoodsTb.append("updateDate varchar(64)");
		 */
		if (cursor.moveToFirst()){
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String goodsCode = cursor.getString(cursor.getColumnIndex("goodsCode"));
			String serverId = cursor.getString(cursor.getColumnIndex("serverId"));
			String goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
			String supplier = cursor.getString(cursor.getColumnIndex("supplier"));
			String standard = cursor.getString(cursor.getColumnIndex("standard"));
			String unit = cursor.getString(cursor.getColumnIndex("unit"));
			double price = cursor.getDouble(cursor.getColumnIndex("price"));
			int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			String createBy = cursor.getString(cursor.getColumnIndex("createBy"));
			String createDate = cursor.getString(cursor.getColumnIndex("createDate"));
			String updateBy = cursor.getString(cursor.getColumnIndex("updateBy"));
			String updateDate = cursor.getString(cursor.getColumnIndex("updateDate"));
			String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
			return new RfidGoods(id, goodsCode, serverId, goodsName, supplier, standard, unit, price, quantity, status, remarks, createBy, updateBy, createDate, updateDate);
		}
		return null;
	}
}
