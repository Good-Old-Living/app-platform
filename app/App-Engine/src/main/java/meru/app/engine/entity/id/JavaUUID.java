package meru.app.engine.entity.id;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class JavaUUID implements UID {

  // @Override
  // public String getId() {
  //
  // return UUID.randomUUID()
  // .toString()
  // .replaceAll("-",
  // "");
  // }

  @Override
  public String getId() {

    return UUID.randomUUID()
               .toString();
  }

  @Override
  public String toId(byte[] binaryId) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(binaryId)
                                      .order(ByteOrder.BIG_ENDIAN);
    Long high = byteBuffer.getLong();
    Long low = byteBuffer.getLong();

    return new UUID(high,
                    low).toString();
  }

  @Override
  public byte[] getBinaryId() {

    return getBinaryId(UUID.randomUUID());
  }

  @Override
  public byte[] toId(String id) {
    return getBinaryId(UUID.fromString(id));
  }

  private static final byte[] getBinaryId(UUID uuid) {

    byte[] binaryId = new byte[16];
    ByteBuffer.wrap(binaryId)
              .order(ByteOrder.BIG_ENDIAN)
              .putLong(uuid.getMostSignificantBits())
              .putLong(uuid.getLeastSignificantBits());
    return binaryId;

  }

  public static void main(String[] args) {

    JavaUUID uuid = new JavaUUID();
    String uuidStr = uuid.getId();
    System.out.println(uuidStr);
    byte[] bytes = uuid.toId(uuidStr);
    uuidStr = uuid.toId(bytes);
    System.out.println(uuidStr);
  }

}
