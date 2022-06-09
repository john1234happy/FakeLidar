import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class handler4141 implements Runnable {
    ArrayList<LidarPacket> lidarPackets;
    ServerSocket socket;
    int port = 4141;

    public handler4141(ArrayList<LidarPacket> lidarPackets) {
        this.lidarPackets = lidarPackets;
    }

    public void run() {
        try {
            socket = new ServerSocket(port);
            Socket connectionSocket = socket.accept();
            InputStream inputStream = connectionSocket.getInputStream();
            OutputStream outputStream = connectionSocket.getOutputStream();
            int i = 0;
            int j = 200;
            Double h = 0.0000002;
            Thread.sleep(20);
            while(true) {
                System.out.println(Arrays.toString(lidarPackets.get(i).getBytePacket()) + " \n" + lidarPackets.size() + " i= " + i);

                lidarPackets.get(i).setTimeStamp(j);
                lidarPackets.get(i).setTimeStampNano(h.intValue());
                outputStream.write(lidarPackets.get(i).getBytePacket());
                outputStream.flush();
                Thread.sleep(10);
                File packetFile = new File("/home/germakovskij/projects/Croudview/outFileForLidarPacketCheck2.txt");
                FileOutputStream outFile = new FileOutputStream(packetFile,true);
                outFile.write(lidarPackets.get(i).getBytePacket());
                i++;
                j++;
                h = h + .000000123;
                Thread.sleep(150);
                if(i>=lidarPackets.size()){
                    i=0;
                    System.out.println("looping");
                }else{
                    //System.out.println("next");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
