package de.kai_morich.simple_bluetooth_le_terminal.Response;

/**
 * Created by: Ranit Raj Ganguly on 16/04/21.
 */
public class BooleanResponse {
    @StateDefinition.State
    private int state;

    private boolean isSuccess;

    private Errors errorData;

    // Constructor
    public BooleanResponse(@StateDefinition.State int state, boolean isSuccess, Errors errorData) {
        this.state = state;
        this.isSuccess = isSuccess;
        this.errorData = errorData;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Errors getErrorData() {
        return errorData;
    }

    public void setErrorData(Errors errorData) {
        this.errorData = errorData;
    }
}
