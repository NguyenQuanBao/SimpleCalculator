import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(12344);

        byte[] buf;
        DatagramPacket dpReceive;
        DatagramPacket dpSend;

        while (true) {
            buf = new byte[65535];

            dpReceive = new DatagramPacket(buf, buf.length);
            ds.receive(dpReceive);

            String inp = new String(dpReceive.getData(), dpReceive.getOffset(), dpReceive.getLength());
            System.out.println("Phép tính nhận được" + inp);

            if (inp.equals("bye")) {
                System.out.println("Client gửi lệnh 'bye' .......ĐANG THOÁT");
                break;
            }
            int result;
            StringTokenizer st = new StringTokenizer(inp);
            int oprnd1 = Integer.parseInt(st.nextToken());
            String operation = st.nextToken();
            int oprnd2 = Integer.parseInt(st.nextToken());

            if (operation.equals("+"))
                result = oprnd1 + oprnd2;
            else if (operation.equals("-"))
                result = oprnd1 - oprnd2;
            else if (operation.equals("*"))
                result = oprnd1 * oprnd2;
            else
                result = oprnd1 / oprnd2;

            System.out.println("Sending result.....");
            String res = Integer.toString(result);
            buf = res.getBytes();

            int port = dpReceive.getPort();

            dpSend = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);

            ds.send(dpSend);

        }
        ds.close();

    }
}
