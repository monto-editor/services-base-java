package monto.service.message;

public class RegisterServiceResponse {

    private final String response;
    private final int bindOnPort;

    public RegisterServiceResponse(String response) {
        this.response = response;
        bindOnPort = -1;
    }

    public RegisterServiceResponse(String response, int bindOnPort) {
        this.response = response;
        this.bindOnPort = bindOnPort;
    }

    public String getResponse() {
        return response;
    }

    public int getBindOnPort() {
        return bindOnPort;
    }
}
