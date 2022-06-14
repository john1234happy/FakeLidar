import java.nio.ByteBuffer;

public class LidarPacket {
    int packetSig = 0x75bd7e97;
    int messageSize = 2224;
    int timeStamp = 0;
    int timeStampNano = 0;
    byte APIVM = 0;
    byte APIVm = 1;
    byte APIVP = 0;
    byte packetType = 0x04;
    PacketData data = null;

    public LidarPacket() {
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTimeStampNano() {
        return timeStampNano;
    }

    public void setTimeStampNano(int timeStampNano) {
        this.timeStampNano = timeStampNano;
    }

    public ByteBuffer getData() {
        return data.returnPacketData();
    }

    public void setData(PacketData data) {
        this.data = data;
    }

    public byte[] getBytePacket(){
        ByteBuffer buffer = ByteBuffer.allocate(2224);
        buffer.putInt(packetSig);
        buffer.putInt(messageSize);
        buffer.putInt(timeStamp);
        buffer.putInt(timeStampNano);
        buffer.put(APIVM);
        buffer.put(APIVm);
        buffer.put(APIVP);
        buffer.put(packetType);
        buffer.put(data.returnPacketData().array());
        return buffer.array();
    }
    public byte[] getBytePacketShort(){
        ByteBuffer buffer = ByteBuffer.allocate(24);
        buffer.putInt(packetSig);
        buffer.putInt(messageSize);
        buffer.putInt(timeStamp);
        buffer.putInt(timeStampNano);
        buffer.put(APIVM);
        buffer.put(APIVm);
        buffer.put(APIVP);
        buffer.put(packetType);
        //buffer.put(data.returnPacketData().array());
        return buffer.array();
    }
}
