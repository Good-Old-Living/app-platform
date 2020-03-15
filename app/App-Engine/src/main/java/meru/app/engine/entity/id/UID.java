package meru.app.engine.entity.id;

public interface UID {

  String getId();

  String toId(byte[] binaryId);

  byte[] getBinaryId();

  byte[] toId(String id);
}
