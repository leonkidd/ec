package cn.heroes.ec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.ec.model.Agent;
import cn.heroes.ec.model.Client;
import cn.heroes.ec.model.Server;
import cn.heroes.jkit.utils.MathUtils;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	// Client_ID, Socket
	//private static final Map<String, Socket> clients = new HashMap<String, Socket>();
	private static final List<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) throws IOException {
		initServerNode();
		
		// 开启服务
		final ServerSocket ss = new ServerSocket(23);
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						// 同个客户端再次联接怎么办
						final Socket s = ss.accept();

						InetAddress ia = s.getInetAddress();
						final String ip = ia.getHostAddress();
						final int port = s.getPort();

						//clients.put(ip, s);
						// Client
						final Client client = new Client(ip, port);
						
						// Server Node
						final Server server = chooseServer();

						new Thread() {
							@Override
							public void run() {
								try {
									final InputStream is = s.getInputStream();
									final OutputStream os = s.getOutputStream();
									
									// Agent
									Socket socket = null;
									try {
										socket = new Socket(server.getIp(), server.getPort());
									} catch(java.net.ConnectException e) {
										server.setAvailable(false);
										logger.error("服务器连接失败{}[{}]", server.getIp(), server.getPort());
										// TODO 重连
									}
									Agent agent = new Agent(socket, socket.getPort());
									
									// 设置相互关系
									agent.setClient(client);
									client.setAgent(agent);
									server.addAgent(agent);
									
									final OutputStream _os = socket.getOutputStream();
									final InputStream _is = socket.getInputStream();

									new Thread() {
										@Override
										public void run() {
											try {
												IOUtils.copy(is, _os);
											} catch (IOException e) {
												logger.error("client {}[{}] 断开连接", ip, port);
											}
											
										}
									}.start();
									new Thread() {
										@Override
										public void run() {
											try {
												IOUtils.copy(_is, os);
											} catch (IOException e) {
												logger.error("client {}[{}] 断开连接", ip, port);
											}
											
										}
									}.start();
									
									logger.info("客户端{}[{}], 代理[{}], 服务器{}[{}], 连接成功", ip, port, agent.getPort(), server.getIp(), server.getPort());
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							}
						}.start();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}
	
	/**
	 * 初始化服务节点
	 */
	public static void initServerNode() {
		Server sv1 = new Server("192.168.200.71", 23);
		Server sv2 = new Server("192.168.200.72", 23);
		Server sv3 = new Server("192.168.200.73", 23);
		servers.add(sv1);
		servers.add(sv2);
		servers.add(sv3);
	}
	
	public static Server chooseServer() {
		// TODO 可用性，当前客户端数
		int size = servers.size();
		int i = MathUtils.random(0, size - 1);
		return servers.get(i);
	}

}
