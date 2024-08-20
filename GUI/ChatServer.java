package cmp.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	public static final int PORT = 8003;
	public ServerSocket server;
	public Vector<Client> vc;

	public ChatServer() {
		try {
			server = new ServerSocket(PORT);
			vc = new Vector<Client>();
		} catch (Exception e) {
			System.out.println("Error in Server");
			System.exit(1); // 비정상 종료
		}
		System.out.println("*********************");
		System.out.println("ChatServer start");
		System.out.println("*********************");
		try {
			while (true) {
				Socket sock = server.accept();
				Client ct = new Client(sock);
				ct.start();
				// 접속한 Client를 Vector에 저장
				vc.addElement(ct);
			}
		} catch (Exception e) {
			System.out.println("Error in Socket");
		}
	}
	
	//전체에게 메세지 보내는 기능
	public void sendAllMessage(String msg) {
		for (Client ct : vc) {
			ct.sendMessage(msg);
		}
	}
	
	//접속이 끊어진 Client를 vc에서 제거
	public void removeClient(Client ct) {
		vc.remove(ct);
	}
	
	//CHATLIST:aaa;bbb;홍길동;ccc;
	public String getIdList() {
		String list = "";
		for (Client ct : vc) {
			list += ct.id+";";
		}
		return list;
	}
	
	//매개변수 id로 Client  찾기
	public Client findClient(String id) {
		Client fct = null;
		for (Client ct : vc) {
			if(id.equals(ct.id)) {
				fct = ct;
				break;
			}
		}
		return fct;
	}

	class Client extends Thread {

		Socket sock;
		BufferedReader in;
		PrintWriter out;
		String id;

		public Client(Socket sock) {
			try {
				this.sock = sock;
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out = new PrintWriter(sock.getOutputStream(), true);
				System.out.println(sock + " 접속");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				sendMessage("사용할 아이디를 입력하세요.");
				while (true) {
					String line = in.readLine();
					if (line == null)
						break;
					else
						routine(line);
				}
			} catch (Exception e) {
				removeClient(this);
				System.err.println(sock + " 끊음");
				String str = ChatProtocol.CHATLIST;
				str += ChatProtocol.MODE1 + getIdList();
				sendAllMessage(str);
			}
		}

		// Client에 전달된 메세지 분석
		public void routine(String line) {
			// CHATALL : 오늘은 즐거운 수요일
			int idx = line.indexOf(ChatProtocol.MODE1);
			String cmd = line.substring(0, idx); // CHATALL
			String data = line.substring(idx + 1); // 오늘은
			if (cmd.equals(ChatProtocol.ID)) {
				id = data;
				// 새로운 접속자 추가되면서 CHATLIST 전송
				sendAllMessage(ChatProtocol.CHATLIST + ChatProtocol.MODE1 + getIdList());
				// 새로운 접속자 Welcome 메시지 전송
				sendAllMessage(ChatProtocol.CHATALL + ChatProtocol.MODE1 + "[" + id + "]님이 입장하였습니다.");
			} else if (cmd.equals(ChatProtocol.CHATALL)) {
				sendAllMessage(ChatProtocol.CHATALL + ChatProtocol.MODE1 + "[" + id + "]" + data);
			} else if (cmd.equals(ChatProtocol.CHAT)) {
				// CHAT : bbb;밥먹자
				idx = data.indexOf(ChatProtocol.MODE2);
				cmd = data.substring(0, idx); // bbb
				data = data.substring(idx + 1); // 밥먹자
				Client ct = findClient(cmd/* bbb */);
				if (ct != null) {
					// bbb 에게만 보냄
					ct.sendMessage(ChatProtocol.CHAT + ChatProtocol.MODE1 + "[" + id + "(S)] " + data);
					// aaa 에게만 보냄
					sendMessage(ChatProtocol.CHAT + ChatProtocol.MODE1 + "[" + id + "(S)] " + data);
				} else { // bbb가 현재 접속자가 아님.
					sendMessage(ChatProtocol.CHAT + ChatProtocol.MODE1 + "[" + cmd + "]님 현재 접속자가 아닙니다.");
				}
			} else if (cmd.equals(ChatProtocol.MESSAGE)) {
				// MESSAGE : bbb;언제 마쳐?
				idx = data.indexOf(ChatProtocol.MODE2);
				cmd = data.substring(0, idx); // bbb
				data = data.substring(idx + 1); // 언제마쳐?
				Client ct = findClient(cmd);
				if (ct != null) {
					ct.sendMessage(ChatProtocol.MESSAGE + ChatProtocol.MODE1 + id + ChatProtocol.MODE2 + data);
				} else {
					sendMessage(ChatProtocol.CHAT + ChatProtocol.MODE1 + "[" + cmd + "]님 현재 접속자가 아닙니다.");
				}
			}
		}

		public void sendMessage(String msg) {
			out.println(msg);

		}
	}// --Client2

	public static void main(String[] args) {
		new ChatServer();
	}

}
