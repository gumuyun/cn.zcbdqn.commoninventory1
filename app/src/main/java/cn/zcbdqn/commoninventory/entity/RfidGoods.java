package cn.zcbdqn.commoninventory.entity;

import com.google.gson.annotations.SerializedName;

public class RfidGoods {

	@SerializedName("androidId")//转换json时更换名字
	private String id;		// 商品编号
	private String goodsCode;		// 商品编码(一维,或二维)
	private String serverId;		// server商品编号
	
	private String goodsName;		// 商品名字
	private String supplier;		// 供应商
	private String standard;		// 规格
	private String unit;		// 单位
	private Double price;		// 商品价格
	private Integer quantity=0;		// 库存量
	
	private String status="1";
	private String remarks;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	
	//private String goodsAllocationId;

	public RfidGoods(String id, String goodsCode, String goodsName, String supplier, String standard, String unit, Double price, String status, String remarks, String createBy, String createDate) {
		this.id = id;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.supplier = supplier;
		this.standard = standard;
		this.unit = unit;
		this.price = price;
		this.status = status;
		this.remarks = remarks;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	public RfidGoods(String id, String goodsName, String supplier, String standard,
					 String unit, Double price, Integer quantity, String createBy,
					 String updateBy, String createDate, String updateDate) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.supplier = supplier;
		this.standard = standard;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	public RfidGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public RfidGoods(String id, String goodsCode, String serverId, String goodsName, String supplier, String standard, String unit, Double price, Integer quantity, String status, String remarks, String createBy, String updateBy, String createDate, String updateDate) {
		this.id = id;
		this.goodsCode = goodsCode;
		this.serverId = serverId;
		this.goodsName = goodsName;
		this.supplier = supplier;
		this.standard = standard;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.remarks = remarks;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
