package com.idream.controller;

import com.gexin.rp.sdk.base.IPushResult;
import com.idream.commons.lib.dto.getui.PushMessageRequestDto;
import com.idream.service.PushMessageToAndroidService;
import com.idream.service.PushMessageToIOSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/getui")
@Api(tags = "个推推送消息给用户", description = "个推推送消息给用户")
public class PushMessageController {

    @Autowired
    private PushMessageToAndroidService pushMessageToAndroidService;
    @Autowired
    private PushMessageToIOSService pushMessageToIOSService;

/*	@RequestMapping(value="/pushMessageToAllAndroid",method=RequestMethod.POST)
    @ApiOperation(value="个推推送所有安卓用户",notes="个推推送所有安卓用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToAllAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		String url = pushMessageRequestDto.getUrl();
		IPushResult pushMessageToAllAndroid = pushMessageToAndroidService.pushMessageToAllAndroid(title, text, url);
		return pushMessageToAllAndroid;
	}
	
	@RequestMapping(value="/pushMessageToAllIOS",method=RequestMethod.POST)
	@ApiOperation(value="个推推送所有IOS用户",notes="个推推送所有IOS用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToAllIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		String url = pushMessageRequestDto.getUrl();
		IPushResult pushMessageToAllIOS = pushMessageToIOSService.pushMessageToAllIOS(title, text, url);
		return pushMessageToAllIOS;
	}
	
	@RequestMapping(value="/pushMessageToSingleAndroid",method=RequestMethod.POST)
	@ApiOperation(value="个推推送特定的安卓用户",notes="个推推送特定的安卓用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToSingleAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String cid = pushMessageRequestDto.getCid();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToSingleAndroid = pushMessageToAndroidService.pushMessageToSingleAndroid(cid, title, text, customContent);
		return pushMessageToSingleAndroid;
	}
	
	@RequestMapping(value="/pushMessageToSingleIOS",method=RequestMethod.POST)
	@ApiOperation(value="个推推送特定的IOS用户",notes="个推推送特定的IOS用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToSingleIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String cid = pushMessageRequestDto.getCid();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToSingleIOS = pushMessageToIOSService.pushMessageToSingleIOS(cid, title, text, customContent);
		return pushMessageToSingleIOS;
	}
	
	@RequestMapping(value="/pushMessageToSingleAliasAndroid",method=RequestMethod.POST)
	@ApiOperation(value="个推推送指定别名的安卓用户",notes="个推推送指定别名的安卓用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToSingleAliasAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String alias = pushMessageRequestDto.getAlias();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToSingleAliasAndroid = pushMessageToAndroidService.pushMessageToSingleAliasAndroid(alias, title, text, customContent);
		return pushMessageToSingleAliasAndroid;
	}
	
	@RequestMapping(value="/pushMessageToSingleAliasIOS",method=RequestMethod.POST)
	@ApiOperation(value="个推推送指定别名的安卓用户",notes="个推推送指定别名的安卓用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToSingleAliasIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		String alias = pushMessageRequestDto.getAlias();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToSingleAliasIOS = pushMessageToIOSService.pushMessageToSingleAliasIOS(alias, title, text, customContent);
		return pushMessageToSingleAliasIOS;
	}
	
	@RequestMapping(value="/pushMessageToListAndroid",method=RequestMethod.POST)
	@ApiOperation(value="个推推送指定列表安卓用户",notes="个推推送指定列表安卓用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToListAndroid(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		List<String> cids = pushMessageRequestDto.getCids();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToListAndroid = pushMessageToAndroidService.pushMessageToListAndroid(cids, title, text, customContent);
		return pushMessageToListAndroid;
	}
	
	@RequestMapping(value="/pushMessageToListIOS",method=RequestMethod.POST)
	@ApiOperation(value="个推推送指定列表IOS用户",notes="个推推送指定列表IOS用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public IPushResult pushMessageToListIOS(@RequestBody PushMessageRequestDto pushMessageRequestDto){
		List<String> cids = pushMessageRequestDto.getCids();
		String title = pushMessageRequestDto.getTitle();
		String text = pushMessageRequestDto.getText();
		Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
		IPushResult pushMessageToListIOS = pushMessageToIOSService.pushMessageToListIOS(cids, title, text, customContent);
		return pushMessageToListIOS;
	}*/

    @RequestMapping(value = "/pushMessageToAll", method = RequestMethod.POST)
    @ApiOperation(value = "个推推送所有安卓和ios用户", notes = "个推推送所有安卓和ios用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IPushResult> pushMessageToAll(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        String title = pushMessageRequestDto.getTitle();
        String text = pushMessageRequestDto.getText();
        String url = pushMessageRequestDto.getUrl();
        IPushResult pushMessageToAllAndroid = pushMessageToAndroidService.pushMessageToAllAndroid(title, text, url);
        IPushResult pushMessageToAllIOS = pushMessageToIOSService.pushMessageToAllIOS(title, text, url);
        List<IPushResult> list = new ArrayList<>();
        list.add(pushMessageToAllIOS);
        list.add(pushMessageToAllAndroid);
        return list;
    }

    @RequestMapping(value = "/pushMessageToSingle", method = RequestMethod.POST)
    @ApiOperation(value = "个推推送特定的安卓和特定的ios用户(CID)", notes = "个推推送特定的安卓和特定的ios用户(CID)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IPushResult> pushMessageToSingle(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        String cid = pushMessageRequestDto.getCid();
        String title = pushMessageRequestDto.getTitle();
        String text = pushMessageRequestDto.getText();
        Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
        IPushResult pushMessageToSingleAndroid = pushMessageToAndroidService.pushMessageToSingleAndroid(cid, title, text, customContent);
        IPushResult pushMessageToSingleIOS = pushMessageToIOSService.pushMessageToSingleIOS(cid, title, text, customContent);
        List<IPushResult> list = new ArrayList<>();
        list.add(pushMessageToSingleIOS);
        list.add(pushMessageToSingleAndroid);
        return list;
    }

    @RequestMapping(value = "/pushMessageToSingleAlias", method = RequestMethod.POST)
    @ApiOperation(value = "个推推送特定的安卓和特定的ios用户(别名)", notes = "个推推送特定的安卓和特定的ios用户(别名)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IPushResult> pushMessageToSingleAlias(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        String alias = pushMessageRequestDto.getAlias();
        String title = pushMessageRequestDto.getTitle();
        String text = pushMessageRequestDto.getText();
        Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
        IPushResult pushMessageToSingleAliasAndroid = pushMessageToAndroidService.pushMessageToSingleAliasAndroid(alias, title, text, customContent);
        IPushResult pushMessageToSingleAliasIOS = pushMessageToIOSService.pushMessageToSingleAliasIOS(alias, title, text, customContent);
        List<IPushResult> list = new ArrayList<>();
        list.add(pushMessageToSingleAliasIOS);
        list.add(pushMessageToSingleAliasAndroid);
        return list;
    }

    @RequestMapping(value = "/pushMessageToList", method = RequestMethod.POST)
    @ApiOperation(value = "个推推送指定列表安卓和ios用户", notes = "个推推送指定列表安卓和ios用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IPushResult> pushMessageToList(@RequestBody PushMessageRequestDto pushMessageRequestDto) {
        List<String> cids = pushMessageRequestDto.getCids();
        String title = pushMessageRequestDto.getTitle();
        String text = pushMessageRequestDto.getText();
        Map<String, Object> customContent = pushMessageRequestDto.getCustomContent();
        IPushResult pushMessageToListAndroid = pushMessageToAndroidService.pushMessageToListAndroid(cids, title, text, customContent);
        IPushResult pushMessageToListIOS = pushMessageToIOSService.pushMessageToListIOS(cids, title, text, customContent);
        List<IPushResult> list = new ArrayList<>();
        list.add(pushMessageToListIOS);
        list.add(pushMessageToListAndroid);
        return list;
    }

}
