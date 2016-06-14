package monto.service.configuration;

public class Setting<T> {

  private String optionId;
  private T value;

  public Setting(String optionId, T value) {
    this.optionId = optionId;
    this.value = value;
  }

  public String getOptionId() {
    return optionId;
  }

  public T getValue() {
    return value;
  }
}
