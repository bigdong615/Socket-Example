import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);

		System.out.println("Connecting to " + serverName + " on port " + port);
		try {
			Socket client = new Socket(serverName, port);

			System.out.println("Just Connect to "
					+ client.getRemoteSocketAddress());

			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("Hell From " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("Server says" + in.readUTF());
			client.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
