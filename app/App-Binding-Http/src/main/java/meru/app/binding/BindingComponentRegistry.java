package meru.app.binding;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BindingComponentRegistry {

  private static final BindingComponentRegistry INSTANCE = new BindingComponentRegistry();

  private Map<String, BindingComponent<?>> bindingComponents = new ConcurrentHashMap<String, BindingComponent<?>>(1);

  public static BindingComponentRegistry getInstance() {
    return INSTANCE;
  }

  public void addComponentComponent(BindingComponent<?> bindingComponent) {
    bindingComponents.put(bindingComponent.getType(),
                           bindingComponent);
  }

  public BindingComponent<?> getBindingComponent(String type) {
    return bindingComponents.get(type);
  }
}
