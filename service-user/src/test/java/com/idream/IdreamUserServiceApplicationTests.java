package com.idream;

import com.idream.commons.db.redis.RedisCache;
import com.idream.commons.db.redis.RedisKeyConstants;
import com.idream.commons.db.token.JWTTokenService;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.PhoneCode;
import com.idream.rabbit.SmsSendService;
import com.idream.rabbit.publisher.UserEventPublisher;
import com.idream.service.UserIMService;
import com.idream.service.UserService;
import com.idream.utils.WeichatMiniProgramUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.font.FontDesignMetrics;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdreamUserServiceApplicationTests {

    @Resource
    private RedisCache redisCache;

    @Resource
    private JWTTokenService jwtTokenService;

    @Resource
    private UserService userService;

    @Resource
    private SmsSendService smsSendService;

    @Resource
    private UserEventPublisher userEventPublisher;

    @Test
    public void testUserEvent() {
        BasisEvent basisEvent = new BasisEvent();
        basisEvent.setId(88);
        for (int i = 0; i < 100; i++) {

            userEventPublisher.publish(basisEvent);
        }
    }

    @Test
    public void testRedis1() {
        Long result = redisCache.hincrBy("62-21", RedisKeyConstants.SCENE_OPEN_AWARD_COUNT, String.valueOf("46"), 1);
        System.out.println(result);
    }

    /**
     * @param
     *
     * @return void
     */
    @Test
    public void Demo1() {
        RedisCache cache = new RedisCache();
        Object object = cache.getObject("aa");
        System.out.println(object);
    }

    /**
     * @param @throws InterruptedException
     *
     * @return void
     */
    @Test
    public void testRedis() throws InterruptedException {
        redisCache.setex("13616532010", "123", 1000, "4321");
        System.out.println(redisCache.getString("13616532010", "123"));
        Thread.sleep(5000L);
    }

//	@Test
//	public void testToken(){
//		String str = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbklkIjoiVEVTVF9VTklPTklEIiwicGhvbmUiOm51bGwsIm5pY2tOYW1lIjoiTmlja3kiLCJvcGVuSWQiOm51bGwsInVzZXJOYW1lIjpudWxsLCJ1c2VySWQiOjMzLCJ0cyI6MTUyMjQ4OTAwODk3NX0.IXPycEHyUg1mL8zhcIurWi0y6g5e7GunO_tLk8iW1XY";
//		for(int i = 0 ; i < 100 ; i++){
//			System.out.println(JSON.toJSONString(jwtTokenService.verify(str)));
//		}
//	}

    /**
     * @param @throws InterruptedException
     *
     * @return void
     */
    @Test
    public void testCloudStream() throws InterruptedException {
        smsSendService.sendSms("13279217973", (byte) 2, "458932");
        Thread.sleep(5000L);
    }

    @Test
    public void doBindingPhone() {
        JSONPublicParam<PhoneCode> params = new JSONPublicParam<>();
        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUserId(69);
//		userInfo.setUnionId("oneGg0n1y6hGXI0Ply7HF-pz162M");
        params.setAuthUserInfo(userInfo);
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setCode("6472");
        phoneCode.setPhone("18551273903");
        params.setRequestParam(phoneCode);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//				userService.doBindingPhone(params, null);
            }).start();
        }
    }

    @Autowired
    private UserIMService userIMService;

    @Test
    public void testImUser() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                userIMService.doGetIMUser(100);
            }).start();
        }
        Thread.sleep(20000);

    }

    @Autowired
    private WeichatMiniProgramUtils weichatMiniProgramUtils;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void assessTokenTest() {
        RestTemplate tpl = new RestTemplate();
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + weichatMiniProgramUtils.getMiniProgramAccessToken();
        //请求头
        MultiValueMap<String, String> heard = new LinkedMultiValueMap<>();
        heard.add("Content-Type", "application/json");
        String encodeString = "businessId:1,businessType:1";
        //参数
        Map<String, String> map = new HashMap<>();
        map.put("page", "pages/loading/loading");
        map.put("width", "1280");
        map.put("scene", encodeString);
        HttpEntity request = new HttpEntity<>(map, heard);


        ResponseEntity<byte[]> entity = tpl.postForEntity(url, request, byte[].class);
        byte[] body = entity.getBody();
        //上传到服务器
        try (InputStream is = new ByteArrayInputStream(body)
        ) {
            BufferedImage image = ImageIO.read(is);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.BLACK);
            Font song = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 30);
            g.setFont(song);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            FontDesignMetrics metri = FontDesignMetrics.getMetrics(song);
            int fontWidth = 0;
            String content = "t330001";
            for (int i = 0; i < content.length(); i++) {
                fontWidth += metri.charWidth(content.charAt(i));
            }
            int x = image.getWidth() / 2 - fontWidth / 2;
            g.drawString(content, x, image.getHeight() - 20);
            g.dispose();

            Path path = Paths.get("b.png");
            ImageIO.write(image, "png", path.toFile());
//			Files.copy(is, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 工具类
     */
    @Test
    public void imageTest() {
        String location = "C:\\Users\\charl\\Desktop\\模拟数据\\生活动态\\picture2\\image";
        String dirName = "mockLifeImage2";

        Path path = Paths.get(location);
        Path newDir = path.resolveSibling(dirName);
        try {
            boolean exists = Files.exists(newDir);
            if (exists) {
                Files.walk(newDir).sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }

            Files.createDirectory(newDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            String dir = newDir.getFileName().toString();
            paths.forEach(i -> {
                File file = i.toFile();
                Path resolve = newDir.resolve(file.getName());
                try {
                    Thumbnails.of(file).scale(0.5f).toFile(resolve.toString());
                    System.out.println(dir + File.separatorChar + resolve.getFileName());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void fontList() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAvailableFontFamilyNames();
        Arrays.stream(ge.getAvailableFontFamilyNames()).forEach(System.out::println);
    }
}
