package meru.app;

public class BindingMessage {

  public String type;
  public String message;
  public Object entity;

  public BindingMessage(String message) {
    this.message = message;
  }
  
  public BindingMessage(BindingMessageType type, String message) {
    this.type = type.name();
    this.message = message;
  }

  public BindingMessage(String message, Object entity) {
    this.message = message;
    this.entity = entity;
  }

  public String getType() {
    return type;
  }
  
  
  public Object getEntity() {
    return entity;
  }

  public String getMessage() {
    return message;
  }
  
  
  public static enum BindingMessageType {
    Warning,
    Error;
    
  }
}
