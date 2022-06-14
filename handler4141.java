import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
            Double h = 0.00070002;
            Thread.sleep(40);
            while(true) {
                //System.out.println(Arrays.toString(lidarPackets.get(i).getBytePacket()) + " \n" + lidarPackets.size() + " i= " + i);
                //System.out.println( " i = " + i);
                lidarPackets.get(i).setTimeStamp(((Double)(h*0.000001)).intValue());
                lidarPackets.get(i).setTimeStampNano(h.intValue());
                outputStream.write(lidarPackets.get(i).getBytePacket());
                outputStream.flush();
                //File packetFile = new File("/home/germakovskij/projects/Croudview/outFileForLidarPacketCheck2.txt");
                //FileOutputStream outFile = new FileOutputStream(packetFile,true);
                //outFile.write(lidarPackets.get(i).getBytePacket());
                i++;
                h = h + .000929260;
                Thread.sleep(55);
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
