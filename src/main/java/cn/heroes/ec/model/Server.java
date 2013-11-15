package cn.heroes.ec.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 客户端对象
 * 
 * @author Leon Kidd
 * @version 1.00, 2013-11-15
 * @since 1.0
 */
public class Server {
	/** IP地址 */
	private String ip;
	/** 服务端口号 */
	private int port;
	private Set<Client> clients = new HashSet<Client>();

	public Server() {

	}

	public Server(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(ip).append(port).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Server) {
			// TODO
			return true;
		} else {
			return false;
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}
