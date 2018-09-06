package com.idream.commons.lib.dto.rabbitmq;

/**
 * @author hejiang
 */
public class IntegrationAwardAsyncInsertDto {

    private int userId; //用户ID

    private int integrationPoolId; //积分抽奖奖池信息

    public IntegrationAwardAsyncInsertDto() {
    }

    public IntegrationAwardAsyncInsertDto(int userId, int integrationPoolId) {
        this.userId = userId;
        this.integrationPoolId = integrationPoolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIntegrationPoolId() {
        return integrationPoolId;
    }

    public void setIntegrationPoolId(int integrationPoolId) {
        this.integrationPoolId = integrationPoolId;
    }
}
