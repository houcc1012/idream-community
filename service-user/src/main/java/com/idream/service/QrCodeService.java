package com.idream.service;

import com.idream.commons.lib.enums.SystemEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface QrCodeService {
    String getQrCode(Integer businessId, SystemEnum.MiniQrCodeType codeType) throws IOException;
}
