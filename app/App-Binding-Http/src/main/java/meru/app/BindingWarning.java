package meru.app;

public class BindingWarning extends BindingMessage {

  public BindingWarning(String message) {
    super(message);
  }

  public BindingWarning(String code,
                        Object entity) {
    super(code,
          entity);
  }

  @Override
  public String getType() {
    return "warning";
  }
}
