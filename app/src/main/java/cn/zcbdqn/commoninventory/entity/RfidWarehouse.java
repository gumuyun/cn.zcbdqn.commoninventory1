package cn.zcbdqn.commoninventory.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 仓库
 * @author gumuyun
 *
 */
public class RfidWarehouse {

	@SerializedName("androidId")
	private String id;
	private String serverId;
	private String warehouseName;
	private String warehouseAddress;
	private String userCode; // 仓库管理员
	private String status;
	private String remarks;
	
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	
	
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public RfidWarehouse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RfidWarehouse(String id , String serverId, String warehouseName, String warehouseAddress,
			String userCode, String status, String createBy, String createDate,
			String updateBy, String updateDate, String remarks) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.warehouseName = warehouseName;
		this.warehouseAddress = warehouseAddress;
		this.userCode = userCode;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseAddress() {
		return warehouseAddress;
	}
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public RfidWarehouse(String id, String warehouseName) {
		super();
		this.id = id;
		this.warehouseName = warehouseName;
	}

}
