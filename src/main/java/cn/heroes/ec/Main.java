package cn.heroes.ec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.ec.model.Server;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	// Client_ID, Socket
	private static final Map<String, Socket> clients = new HashMap<String, Socket>();
	private static final List<Server> servers = new ArrayList<Server>();

	public static void main(String[] args) throws IOException {
		
		// Server Nodes
		Server sv1 = new Server("154.189.1.11");
		Server sv2 = new Server("154.189.1.11");
		Server sv3 = new Server("154.189.1.11");
		servers.add(sv1);
		servers.add(sv2);
		servers.add(sv3);
		
		
		final ServerSocket ss = new ServerSocket(23);
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						// 同个客户端再次联接怎么办
						final Socket s = ss.accept();

						InetAddress ia = s.getInetAddress();
						String ip = ia.getHostAddress();
						int port = s.getPort();

						clients.put(ip, s);

						new Thread() {
							@Override
							public void run() {
								try {
									final InputStream is = s.getInputStream();
									final OutputStream os = s.getOutputStream();
									
									Socket socket = new Socket("192.168.200.7", 23);
									final OutputStream _os = socket.getOutputStream();
									final InputStream _is = socket.getInputStream();

									new Thread() {
										@Override
										public void run() {
											try {
												IOUtils.copy(is, _os);
											} catch (IOException e) {
												logger.error("client" + "" + "断开连接");
											}
											
										}
									}.start();
									new Thread() {
										@Override
										public void run() {
											try {
												IOUtils.copy(_is, os);
											} catch (IOException e) {
												logger.error("client" + "" + "断开连接");
											}
											
										}
									}.start();
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							}
						}.start();
						logger.info("新客户{}[{}]连接成功", ip, port);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}
	
	public Server chooseServer() {
		
		return null;
	}

}
