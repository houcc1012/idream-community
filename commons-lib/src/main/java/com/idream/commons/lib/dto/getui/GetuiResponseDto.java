package com.idream.commons.lib.dto.getui;

import java.util.Map;

/**
 * @author hejiang
 */
public class GetuiResponseDto {

    private String resultCode;

    private String taskId;

    private String messageId;

    private Map<String, Object> response;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    public static GetuiResponseDto returnErrorData(String resultCode) {
        GetuiResponseDto data = new GetuiResponseDto();
        data.setResultCode(resultCode);
        return data;
    }
}
