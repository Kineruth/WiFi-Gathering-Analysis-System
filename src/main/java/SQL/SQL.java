package SQL;

public class SQL {

	private String ip, port, dbName, username, password, url, table;

	public SQL(String ip, String port, String dbName, String username, String password, String table) {
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.username = username;
		this.password = password;
//		this.url = url;
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;//+"?useSSL=false" ; 
		this.table = table;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getdbName() {
		return this.dbName;
	}

	public void setdbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String tablename) {
		this.table = tablename;
	}

}