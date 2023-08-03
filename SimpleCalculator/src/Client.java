import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf;

        while(true){
            System.out.println("Nhập phép tính theo định dạng");
            System.out.println("'số_hạng_1 toán tử số_hạng_2'");
            String inp = sc.nextLine();

            buf = inp.getBytes();

            DatagramPacket dpSend = new DatagramPacket(buf , buf.length , ip , 12344);

            ds.send(dpSend);

            if (inp.equals("bye"))
                break;

            buf = new byte[65535];

            DatagramPacket dpReceive = new DatagramPacket(buf , buf.length);

            ds.receive(dpReceive);

            String result = new String(dpReceive.getData(), dpReceive.getOffset(), dpReceive.getLength());
            System.out.println("Kết quả: " + result);
        }
        ds.close();
    }
}
