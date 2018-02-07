import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;

public class SocketServerExample implements Runnable {

	private ServerSocket serverSocket;

	public SocketServerExample(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);

	}

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);

		try {
			SocketServerExample socketSeverExample = new SocketServerExample(
					port);
			Thread t = new Thread(socketSeverExample);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		while (true) {

			System.out.println("Waiting for client on port "
					+ serverSocket.getLocalPort() + "...");

			try {
				Socket server = serverSocket.accept();
				System.out.println("Just connected to"
						+ server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(
						server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream(
						server.getOutputStream());
				out.writeUTF("Thank you for connecting  to"
						+ server.getLocalSocketAddress() + "\ngoodbye");
				server.close();

			} catch (SocketTimeoutException s) {

				System.out.println("Socket timed out !");
				break;
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}

		}

	}

}
