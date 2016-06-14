package monto.service.registration;

import com.google.gson.annotations.SerializedName;

public class RegisterServiceResponse {

  private final String response;

  @SerializedName("bind_on_port")
  private final int connectToPort;

  public RegisterServiceResponse(String response) {
    this.response = response;
    connectToPort = -1;
  }

  public RegisterServiceResponse(String response, int bindOnPort) {
    this.response = response;
    this.connectToPort = bindOnPort;
  }

  public String getResponse() {
    return response;
  }

  public int getConnectToPort() {
    return connectToPort;
  }
}
