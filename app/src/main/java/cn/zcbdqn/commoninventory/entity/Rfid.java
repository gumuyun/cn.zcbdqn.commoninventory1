package cn.zcbdqn.commoninventory.entity;


public class Rfid {
	
	
	private String id;
	private String serverId;
	/**
	 * Rfid序号
	 */
	private String rfidCode;		// 
	/**
	 * 商品编号
	 */
	private String goodsId;		// 商品编号
	/**
	 * 仓库编号
	 */
	private String warehouseId;		// 仓库编号
	/**
	 * 仓库名字
	 */
	private String warehouseName;		// 仓库名字
	/**
	 * 商品名字
	 */
	private String goodsName;		// 商品名字
	private String remarks;		// 
	private String status;		// 
	/**
	 * 当前RFID对应的商品个数,一个RFID标签对应多件小商品
	 */
	private Integer goodsCounts=1;       //商品数量
	
	
	
	
	private boolean checked=true;
	
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	
	public Rfid(String rfidCode, String string2, String string3, String warehouseName, String goodsName) {
		super();
		this.rfidCode=rfidCode;
		this.warehouseName=warehouseName;
		this.goodsName=goodsName;
	}
	public Rfid(String rfidCode, String goodsId, String warehouseId,
			String warehouseName, String goodsName, Integer goodsCounts,
			boolean checked, String createBy, String updateBy,
			String createDate, String updateDate) {
		super();
		this.rfidCode = rfidCode;
		this.goodsId = goodsId;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.goodsName = goodsName;
		this.goodsCounts = goodsCounts;
		this.checked = checked;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Rfid [id=" + id + ", rfidCode=" + rfidCode + ", goodsId="
				+ goodsId + ", warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", goodsName=" + goodsName + ", goodsCounts="
				+ goodsCounts + ", checked=" + checked + ", createBy="
				+ createBy + ", updateBy=" + updateBy + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	public Rfid(String id, String rfidCode, String goodsId, String warehouseId,
			String warehouseName, String goodsName, Integer goodsCounts,
			boolean checked, String createBy, String updateBy,
			String createDate, String updateDate) {
		super();
		this.id = id;
		this.rfidCode = rfidCode;
		this.goodsId = goodsId;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.goodsName = goodsName;
		this.goodsCounts = goodsCounts;
		this.checked = checked;
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
	public String getRfidCode() {
		return rfidCode;
	}
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
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
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getGoodsCounts() {
		return goodsCounts;
	}
	public void setGoodsCounts(Integer goodsCounts) {
		this.goodsCounts = goodsCounts;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Rfid(String rfidCode, String goodsName, Integer goodsCounts,
			boolean checked, String createBy, String createDate) {
		super();
		this.rfidCode = rfidCode;
		this.goodsName = goodsName;
		this.goodsCounts = goodsCounts;
		this.checked = checked;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Rfid rfid = (Rfid) o;

		return rfidCode.equals(rfid.rfidCode);
	}

	@Override
	public int hashCode() {
		return rfidCode.hashCode();
	}
}
