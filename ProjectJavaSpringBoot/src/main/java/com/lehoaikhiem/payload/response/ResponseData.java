package com.lehoaikhiem.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private final int status;
    private final String message;
    private T data;

    /**
     * Response data for the API to retrieve data successfully. For GET, POST only
     * @param status
     * @param message
     * @param data
     */
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Response data when the API executes successfully or getting error. For PUT, PATCH, DELETE
     * @param status
     * @param message
     */

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
