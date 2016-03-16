package monto.service.registration;

public class RegisterServiceResponse {

    private final String response;
    private final int conectToPort;

    public RegisterServiceResponse(String response) {
        this.response = response;
        conectToPort = -1;
    }

    public RegisterServiceResponse(String response, int bindOnPort) {
        this.response = response;
        this.conectToPort = bindOnPort;
    }

    public String getResponse() {
        return response;
    }

    public int getConnectToPort() {
        return conectToPort;
    }
}
