import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FakeLidar {
    static File packetFile;
    static ArrayList<LidarPacket> packets = new ArrayList<>();

    public static void main(String args[]) {
        try{
            packetFile = new File("/home/germakovskij/projects/Croudview/dataToParse.pcapng");
            if(packetFile.exists()) {
                FileInputStream fileInputStream = new FileInputStream(packetFile);
                byte[] bytes = fileInputStream.readAllBytes();
                ByteBuffer buffer = ByteBuffer.wrap(bytes);

                //parse(buffer);
                //print();
                //packets.stream().sorted(Comparator.comparingInt(LidarPacket::getTimeStampNano)).collect(Collectors.toList());
                populate(30000);
                System.out.println("i did it");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        new Thread(new handler4141(packets)).start();
        new Thread(new handler7780()).start();
    }
    static void parse(ByteBuffer buffer){
        while(buffer.hasRemaining()){
            LidarPacket lidarPacket = new LidarPacket();
            int position = buffer.position();
            short tempShort = buffer.getShort();
            if (tempShort == 0x75bd) {
                buffer.position(position);
                int tempInt = buffer.getInt();
                if (tempInt == 0x75bd7e97) {
                    buffer.position(position+8);

                    lidarPacket.setTimeStamp(buffer.getInt());
                    lidarPacket.setTimeStampNano(buffer.getInt());

                    buffer.position(position + 20);

                    PacketData packetData = new PacketData(buffer.getShort());
                    packetData.setReturnID(buffer.get());
                    packetData.setReserved(buffer.get());

                    FiringData[] firingDatas = new FiringData[50];
                    for(int i  = 0; 50>i;i++){
                        if(buffer.get(buffer.position())== (byte) 184 && buffer.get(buffer.position()+1) == (byte) 174 && buffer.get(buffer.position()+2) == (byte) 237){
                            buffer.position(buffer.position()+66);
                            continue;
                        }else{
                            FiringData firingData = new FiringData();
                            firingData.setPosition(buffer.getShort());
                            firingData.setReserved(buffer.getShort());
                            int[] ints = new int[8];
                            for(int j = 0;8>j;j++) {
                                ints[j] = buffer.getInt();
                            }
                            firingData.setReturnDistance(ints);
                            byte[] bytes = new byte[8];
                            for(int j = 0;8>j;j++) {
                                bytes[j] = buffer.get();
                            }
                            firingData.setReturnIntensities(bytes);
                            firingDatas[i]=firingData;
                        }
                    }
                    packetData.setFiringData(firingDatas);
                    lidarPacket.setData(packetData);
                    packets.add(lidarPacket);
                }
            }
        }
        //new Thread(new handler4141(packets)).start();
    }

    private static void populate(int NumOfPackets){
        Random random = new Random();
        long seed  = random.nextLong();
        System.out.println(seed);
        random.setSeed(seed);
        short s = 0;
        //short s = (short) random.nextInt(0,10399);
        //int rd = random.nextInt(0,20000000);
        int rd = 2500000;
        for(int i = 0; NumOfPackets > i; i++){
            //short s = (short) random.nextInt(0,10399);
            //short s = (short) random.nextInt(0,10399);
            FiringData[] firingDatas = new FiringData[50];
            for(int j = 0; 50 > j; j++){
                int[] ints = new int[8];
                byte[] bytes = new byte[8];
                for(int n = 0 ; ints.length > n; n++){
                    //ints[n] = random.nextInt(0,2500000);

                    ints[n] = rd;
                    //rd = rd - 5;
                    //ints[n] = 0;
                    bytes[n] = (byte) 120;
                }
               // random.nextBytes(bytes);
                firingDatas[j] = new FiringData(s,ints,bytes);
                s++;
                if(s==10399){
                    s = 0;
                }
                //s = (short)(s + 3);
            }
            PacketData packetData = new PacketData(firingDatas);
            LidarPacket lidarPacket = new LidarPacket();
            lidarPacket.setData(packetData);
            packets.add(lidarPacket);
        }
        //make the populate function
    }
    static void print() throws IOException {
        for(int i = 0 ; packets.size() > i ; i++){
            File packetFile = new File("/home/germakovskij/projects/Croudview/outFileForLidarPacketCheck1.txt");
            FileOutputStream outFile = null;
            try {
                outFile = new FileOutputStream(packetFile,true);
                outFile.write(packets.get(i).getBytePacket());
                System.out.println(Arrays.toString(packets.get(i).getBytePacket()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
