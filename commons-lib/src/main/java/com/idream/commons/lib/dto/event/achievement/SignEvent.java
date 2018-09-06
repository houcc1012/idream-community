package com.idream.commons.lib.dto.event.achievement;

import com.idream.commons.lib.dto.event.basis.BasisEvent;

/**
 * @author charles
 */
public class SignEvent extends BasisEvent {
    public SignEvent() {

    }

    public SignEvent(Integer organizationId, Integer userId) {
        this(BasisEvent.ACTION_ADD, organizationId, userId);
    }

    public SignEvent(String action, Integer organizationId, Integer correlationId) {
        super(action, organizationId, correlationId);
    }
}


