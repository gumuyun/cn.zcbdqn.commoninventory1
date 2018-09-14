package cn.zcbdqn.commoninventory.entity;


public class RfidGoodsAllocation {
	public static long serialVersionUID=1;
	private String id;		// 货位编号
	private String serverId;		//
	private String goodsAllocationName;		// 货位名字
	private String warehouseId;		// 所属仓库
	private String status;		// 状态
	private String remarks;		// 描述
	
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	
	private RfidWarehouse wareHouse;
	public RfidGoodsAllocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RfidGoodsAllocation(String goodsAllocationName, String warehouseId,
			RfidWarehouse wareHouse) {
		super();
		this.goodsAllocationName = goodsAllocationName;
		this.warehouseId = warehouseId;
		this.wareHouse = wareHouse;
	}
	
	
	public RfidGoodsAllocation(String id, String goodsAllocationName) {
		super();
		this.id = id;
		this.goodsAllocationName = goodsAllocationName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsAllocationName() {
		return goodsAllocationName;
	}
	public void setGoodsAllocationName(String goodsAllocationName) {
		this.goodsAllocationName = goodsAllocationName;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public RfidWarehouse getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(RfidWarehouse wareHouse) {
		this.wareHouse = wareHouse;
	}
	public RfidGoodsAllocation(String id, String goodsAllocationName,
			String warehouseId, String status, String remarks, String createBy,
			String updateBy, String createDate, String updateDate,
			RfidWarehouse wareHouse) {
		super();
		this.id = id;
		this.goodsAllocationName = goodsAllocationName;
		this.warehouseId = warehouseId;
		this.status = status;
		this.remarks = remarks;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.wareHouse = wareHouse;
	}
	public RfidGoodsAllocation(String id, String serverId, String goodsAllocationName,
			String warehouseId, String status, String remarks, String createBy,
			String updateBy, String createDate, String updateDate,
			RfidWarehouse wareHouse) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.goodsAllocationName = goodsAllocationName;
		this.warehouseId = warehouseId;
		this.status = status;
		this.remarks = remarks;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.wareHouse = wareHouse;
	}
	public RfidGoodsAllocation(String goodsAllocationName, String warehouseId,
			String status, String remarks, String createBy, String createDate,
			RfidWarehouse wareHouse) {
		super();
		this.goodsAllocationName = goodsAllocationName;
		this.warehouseId = warehouseId;
		this.status = status;
		this.remarks = remarks;
		this.createBy = createBy;
		this.createDate = createDate;
		this.wareHouse = wareHouse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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