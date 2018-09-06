package com.idream.service.impl;

import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.BookExtensionMapper;
import com.idream.commons.lib.mapper.PromotionTeamMapper;
import com.idream.commons.lib.model.BookExtension;
import com.idream.commons.lib.model.PromotionTeam;
import com.idream.service.ImageUploadService;
import com.idream.service.QrCodeService;
import com.idream.utils.AliyunOssUtils;
import com.idream.utils.WeichatMiniProgramUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Autowired
    private BookExtensionMapper bookExtensionMapper;
    @Autowired
    private PromotionTeamMapper promotionTeamMapper;
    @Autowired
    private WeichatMiniProgramUtils weichatMiniProgramUtils;
    @Autowired
    private AliyunOssUtils aliyunOssUtils;
    @Autowired
    private ImageUploadService imageUploadService;

    @Override
    public String getQrCode(Integer businessId, SystemEnum.MiniQrCodeType codeType) throws IOException {
        String qrCode = "";

        if (codeType.equals(SystemEnum.MiniQrCodeType.BOOK)) {
            BookExtension bookExtension = bookExtensionMapper.selectByBookId(businessId);
            if (bookExtension == null) {
                throw new BusinessException("没有该书屋");
            }
            qrCode = bookExtension.getQrCode();
            if (StringUtils.isBlank(qrCode)) {
                qrCode = getWeiChatQrCode(businessId, codeType, qrCode, bookExtension.getCode());
                bookExtension.setQrCode(qrCode);
                bookExtension.setUpdateTime(new Date());
                bookExtensionMapper.updateByPrimaryKey(bookExtension);
            }
        }

        if (codeType.equals(SystemEnum.MiniQrCodeType.TEAM)) {
            PromotionTeam promotionTeam = promotionTeamMapper.selectByPrimaryKey(businessId);
            if (promotionTeam == null) {
                throw new BusinessException("没有该团队");
            }
            qrCode = promotionTeam.getQrCode();
            if (StringUtils.isBlank(qrCode)) {
                qrCode = getWeiChatQrCode(businessId, codeType, qrCode, promotionTeam.getCode());
                promotionTeam.setQrCode(qrCode);
                promotionTeam.setUpdateTime(new Date());
                promotionTeamMapper.updateByPrimaryKey(promotionTeam);
            }
        }

        return qrCode;
    }

    private String getWeiChatQrCode(Integer businessId, SystemEnum.MiniQrCodeType codeType, String qrCode, String code) throws IOException {
        if (StringUtils.isBlank(qrCode)) {
            File file = null;
            byte[] qrcodeByte = weichatMiniProgramUtils.getQrcodeByte(businessId, codeType.getCode());
            try (InputStream is = new ByteArrayInputStream(qrcodeByte)) {
                Path path = Paths.get(System.nanoTime() + ".png");
                Files.copy(is, path);
                file = path.toFile();
                BufferedImage image = ImageIO.read(file);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.BLACK);
                Font song = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 30);
                g.setFont(song);
                g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int x = 20;
                g.drawString(code, x, image.getHeight() - 20);
                g.dispose();
                ImageIO.write(image, "png", file);
                qrCode = aliyunOssUtils.aliOssUpload(file.getName(), SystemEnum.UploadImgFileFolder.MINI_QRCODE.getCode(), file);

            } finally {
                if (file != null) {
                    file.delete();
                }
            }

        }
        return qrCode;
    }
}
