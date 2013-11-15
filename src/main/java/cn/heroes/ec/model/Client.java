package cn.heroes.ec.model;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 客户端对象
 * 
 * @author Leon Kidd
 * @version 1.00, 2013-11-15
 * @since 1.0
 */
public class Client {
	/** IP地址 */
	private String ip;
	/** 发起端口号 */
	private int port;

	public Client() {

	}

	public Client(String ip, int port) {
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
		if(obj instanceof Client) {
			// TODO
			return true;
		} else {
			return false;
		}
	}

}
