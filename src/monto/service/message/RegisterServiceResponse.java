package monto.service.message;

public class RegisterServiceResponse {

    private final String respondToServiceID;
    private final String response;
    private final int bindOnPort;

    public RegisterServiceResponse(String respondToServiceID, String response, int bindOnPort) {
        this.respondToServiceID = respondToServiceID;
        this.response = response;
        this.bindOnPort = bindOnPort;
    }

    public String getRespondToServiceID() {
        return respondToServiceID;
    }

    public String getResponse() {
        return response;
    }

    public int getBindOnPort() {
        return bindOnPort;
    }
}
