package cn.heroes.ec.model;

import java.net.Socket;

import org.apache.commons.lang.builder.EqualsBuilder;
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
	/** 端口号(与Agent连接的发起端口) */
	private int port;
	/** 客户端的socket连接 */
	private Socket socket;
	/** 关联的EC代理对象 */
	private Agent agent;

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
		if (obj instanceof Client) {
			Client c = (Client) obj;
			return new EqualsBuilder().append(this.ip, c.ip)
					.append(this.port, c.port).isEquals();
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

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 *            the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
