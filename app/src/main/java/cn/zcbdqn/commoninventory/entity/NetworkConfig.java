package cn.zcbdqn.commoninventory.entity;

public class NetworkConfig {
	private String id;
	private String serverIp;
	private Integer serverPort;
	private String contextPath;
	private String createBy;
	private String updateBy;
	private String createDate;
	private String updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
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
	public NetworkConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NetworkConfig(String id, String serverIp, Integer serverPort,
			String contextPath, String createBy, String updateBy,
			String createDate, String updateDate) {
		super();
		this.id = id;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.contextPath = contextPath;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/**
	 *
	 * @return
	 */
	public String toUrl(){
		return serverIp+":"+serverPort+contextPath;
	}

}
