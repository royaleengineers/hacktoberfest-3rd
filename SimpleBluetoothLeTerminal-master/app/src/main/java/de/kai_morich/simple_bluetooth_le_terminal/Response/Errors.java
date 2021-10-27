package de.kai_morich.simple_bluetooth_le_terminal.Response;

public class Errors {
    @StateDefinition.ErrorState
    private int mErrorStatus;

    private String mErrorMessage;

    // Constructor
    public Errors(@StateDefinition.ErrorState int status, String message) {
        this.mErrorStatus = status;
        this.mErrorMessage = message;
    }

    @StateDefinition.ErrorState
    public int getErrorStatus() {
        return mErrorStatus;
    }

    public void setErrorStatus(@StateDefinition.ErrorState int mErrorStatus) {
        this.mErrorStatus = mErrorStatus;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}
