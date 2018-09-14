/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.zcbdqn.commoninventory.entity;

/**
 * 出库记录Entity
 * @author guoge
 * @version 2018-08-08
 */
public class RfidExportWarehouse {
	
	private String id;			
	private String serverId;			
	private String rfidCode;		// rfid编码	
	private String goodsName;		// 商品名
	private String warehouseName;		// 仓库
	private String allocationName;		// 货位
	private Integer quantity;		// 出库量
    private String goodsId;		// 商品编号
    private String warehouseId;		// 仓库编号

	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	public RfidExportWarehouse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RfidExportWarehouse(String id, String serverId, String rfidCode,
			String goodsName, String warehouseName, String allocationName,
			Integer quantity, String goodsId, String warehouseId,
			String createBy, String updateBy, String createDate,
			String updateDate) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.rfidCode = rfidCode;
		this.goodsName = goodsName;
		this.warehouseName = warehouseName;
		this.allocationName = allocationName;
		this.quantity = quantity;
		this.goodsId = goodsId;
		this.warehouseId = warehouseId;
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
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getRfidCode() {
		return rfidCode;
	}
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getAllocationName() {
		return allocationName;
	}
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
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
	@Override
	public String toString() {
		return "RfidExportWarehouse [id=" + id + ", serverId=" + serverId
				+ ", rfidCode=" + rfidCode + ", goodsName=" + goodsName
				+ ", warehouseName=" + warehouseName + ", allocationName="
				+ allocationName + ", quantity=" + quantity + ", goodsId="
				+ goodsId + ", warehouseId=" + warehouseId + ", createBy="
				+ createBy + ", updateBy=" + updateBy + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
	
}