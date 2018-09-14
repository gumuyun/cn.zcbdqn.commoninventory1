/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.zcbdqn.commoninventory.entity;


import java.util.Date;




/**
 * 盘点列表Entity
 * @author guoge
 * @version 2018-08-01
 */
public class RfidInventory {
	
	private String id;		// 盘点仓库编号
	private String serverId;		// 盘点仓库编号
	private String warehouseId;		// 盘点仓库编号
	private String inventoryBy;		// 盘点人
	private Date inventoryDate;		// 盘点时间
	private String auditBy;		// 审核人
	private Date auditDate;		// 审核时间
	private RfidGoods rfidGoods;    //商品
	
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	public RfidInventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RfidInventory(String id, String warehouseId, String inventoryBy,
			Date inventoryDate, String auditBy, Date auditDate,
			RfidGoods rfidGoods, String createBy, String updateBy,
			String createDate, String updateDate) {
		super();
		this.id = id;
		this.warehouseId = warehouseId;
		this.inventoryBy = inventoryBy;
		this.inventoryDate = inventoryDate;
		this.auditBy = auditBy;
		this.auditDate = auditDate;
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
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getInventoryBy() {
		return inventoryBy;
	}
	public void setInventoryBy(String inventoryBy) {
		this.inventoryBy = inventoryBy;
	}
	public Date getInventoryDate() {
		return inventoryDate;
	}
	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	public String getAuditBy() {
		return auditBy;
	}
	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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