package cn.heroes.ec.model;

import java.net.Socket;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EC代理对象
 * 
 * @author Leon Kidd
 * @version 1.00, 2013-11-18
 * @since 1.0
 */
public class Agent {
	/** 端口号(与服务节点连接的发起端口) */
	private int port;
	/** socket(与服务节点的连接) */
	private Socket socket;
	/** 关联的客户端对象 */
	private Client client;

	public Agent() {

	}

	public Agent(Socket socket, int port) {
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(port).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Agent) {
			Agent a = (Agent) obj;
			return this.port == a.port;
		} else {
			return false;
		}
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
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
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

}
