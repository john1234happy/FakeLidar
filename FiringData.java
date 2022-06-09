import java.nio.ByteBuffer;

public class FiringData {
    short position;
    short reserved;
    int[] returnDistance = new int[8];
    byte[] returnIntensities = new byte[8];

    public FiringData(short position, short reserved, int[] returnDistance, byte[] returnIntensities) {
        this.position = position;
        this.reserved = reserved;
        this.returnDistance = returnDistance;
        this.returnIntensities = returnIntensities;
    }
    public FiringData() {
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public short getReserved() {
        return reserved;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    public int[] getReturnDistance() {
        return returnDistance;
    }

    public void setReturnDistance(int[] returnDistance) {
        this.returnDistance = returnDistance;
    }

    public byte[] getReturnIntensities() {
        return returnIntensities;
    }

    public void setReturnIntensities(byte[] returnIntensities) {
        this.returnIntensities = returnIntensities;
    }

    public ByteBuffer returnData(){
        ByteBuffer buffer = ByteBuffer.allocate(44);
        buffer.putShort(position);
        buffer.putShort(reserved);
        for(int i = 0; returnDistance.length >i; i++){
            buffer.putInt(returnDistance[i]);
        }
        for(int j = 0; returnIntensities.length >j; j++){
            buffer.put(returnIntensities[j]);
        }
        return buffer;
    }
}
