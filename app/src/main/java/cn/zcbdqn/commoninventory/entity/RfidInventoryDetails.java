/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.zcbdqn.commoninventory.entity;


/**
 * 盘点列表Entity
 * @author guoge
 * @version 2018-08-01
 */
public class RfidInventoryDetails {
	
	private String id;		// 编号
	private String serverId;		// 编号
	private RfidInventory inventoryId;		// 盘点编号 父类
	private String goodsId;		// 商品编号
	private String goodsName;		// 商品名称
	private Integer goodsQuantity;		// 商品存库
	private Integer realQuantity;		// 实际库存
	private Integer differValue;		// 库存差值
	private String inventoryResult;		// 盘点结果
	private RfidGoods rfidGoods;    //商品
	
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	public RfidInventoryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RfidInventoryDetails(String id, RfidInventory inventoryId,
			String goodsId, String goodsName, Integer goodsQuantity,
			Integer realQuantity, Integer differValue, String inventoryResult,
			RfidGoods rfidGoods, String createBy, String updateBy,
			String createDate, String updateDate) {
		super();
		this.id = id;
		this.inventoryId = inventoryId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsQuantity = goodsQuantity;
		this.realQuantity = realQuantity;
		this.differValue = differValue;
		this.inventoryResult = inventoryResult;
		this.rfidGoods = rfidGoods;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RfidInventory getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(RfidInventory inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	public Integer getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(Integer realQuantity) {
		this.realQuantity = realQuantity;
	}
	public Integer getDifferValue() {
		return differValue;
	}
	public void setDifferValue(Integer differValue) {
		this.differValue = differValue;
	}
	public String getInventoryResult() {
		return inventoryResult;
	}
	public void setInventoryResult(String inventoryResult) {
		this.inventoryResult = inventoryResult;
	}
	public RfidGoods getRfidGoods() {
		return rfidGoods;
	}
	public void setRfidGoods(RfidGoods rfidGoods) {
		this.rfidGoods = rfidGoods;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	
}