import java.nio.ByteBuffer;

public class PacketData {
    short status = 0;
    byte returnID = 0;
    byte reserved = 0;
    FiringData[] firingData = new FiringData[50];

    public PacketData() {
    }

    public PacketData(short status) {
        this.status = status;
    }

    public PacketData(FiringData[] firingDatas) {
        this.firingData = firingDatas;
    }

    public FiringData[] getFiringData() {
        return firingData;
    }

    public void setFiringData(FiringData[] firingData) {
        this.firingData = firingData;
    }

    public void setReturnID(byte returnID) {
        this.returnID = returnID;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }

    public ByteBuffer returnPacketData(){
        ByteBuffer buffer = ByteBuffer.allocate(2204);
        buffer.putShort(status);
        buffer.put(returnID);
        buffer.put(reserved);
        for(int i =0;firingData.length > i; i++){
            buffer.put(firingData[i].returnData().array());
        }
        return buffer;
    }
}
