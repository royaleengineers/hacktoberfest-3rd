package de.kai_morich.simple_bluetooth_le_terminal.Response;

import java.util.List;

public class DataResponse<T> {
    @StateDefinition.State
    private int state;

    private List<T> data;

    private Errors errorData;

    // Constructor
    public DataResponse(@StateDefinition.State int state, List<T> data, Errors errorData) {
        this.state = state;
        this.data = data;
        this.errorData = errorData;
    }

    @StateDefinition.State
    public int getState() {
        return state;
    }

    public void setState(@StateDefinition.State int state) {
        this.state = state;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Errors getErrorData() {
        return errorData;
    }

    public void setErrorData(Errors errorData) {
        this.errorData = errorData;
    }
}
