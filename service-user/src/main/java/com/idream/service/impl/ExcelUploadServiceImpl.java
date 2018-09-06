package com.idream.service.impl;

import com.idream.commons.lib.dto.activity.AppLifeTypeDto;
import com.idream.commons.lib.dto.excel.ExcelActivityLifeDto;
import com.idream.commons.lib.dto.excel.ExcelLifeDto;
import com.idream.commons.lib.dto.excel.ExcelLocationDto;
import com.idream.commons.lib.dto.excel.ExcelUserDto;
import com.idream.commons.lib.enums.CommunityEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.ExcelReaderUtils;
import com.idream.service.ExcelUploadService;
import com.idream.service.UserIMService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author charles
 */
@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Autowired
    private CommunityLifeImageMapper communityLifeImageMapper;
    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OpenCityMapper openCityMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private UserSkinRelationMapper userSkinRelationMapper;
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    @Autowired
    private UserIMService userIMService;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;

    @Override
    public void uploadLifeExcel(MultipartFile file, String fileLocation, Integer startId, Integer endId) {
        List<AppLifeTypeDto> types = activityTypeMapper.selectLifeType();
        String [] strFileds=Arrays.stream(ExcelLifeDto.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        File convert=null;
        try {
            convert = convert(file);
            List<Integer> userIds = IntStream.rangeClosed(startId, endId).boxed().collect(Collectors.toList());
            List<ExcelLifeDto> excelLifeDtos = ExcelReaderUtils.readXlsContent(convert, strFileds, ExcelLifeDto.class);

            assert excelLifeDtos != null;
            excelLifeDtos.forEach(i->{
                CommunityLife life = createLife(types, i);
                communityLifeMapper.insertSelective(life);

                Integer lifeId = life.getId();
                String strImage = i.getStrImage();
                Integer userId = i.getUserId();

                addLifeImage(fileLocation, lifeId, strImage, userId);
                addLifeLike(userIds, lifeId, userId);

            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (convert != null) {
                convert.delete();
            }
        }

    }

    @Override
    public void uploadActivityLifeExcel(MultipartFile file, String fileLocation, Integer startId, Integer endId) {
        List<AppLifeTypeDto> types = activityTypeMapper.selectLifeType();
        String [] strFileds=Arrays.stream(ExcelActivityLifeDto.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        File convert=null;
        try {
            convert = convert(file);
            List<Integer> userIds = IntStream.rangeClosed(startId, endId).boxed().collect(Collectors.toList());
            List<ExcelActivityLifeDto> excelLifeDtos = ExcelReaderUtils.readXlsContent(convert, strFileds, ExcelActivityLifeDto.class);

            assert excelLifeDtos != null;
            Random random = new Random();
            excelLifeDtos.forEach(i->{

                Integer activityId = i.getActivityId();
                ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
                if (activityInfo == null) {
                   return;
                }
                Date startTime = activityInfo.getStartTime();
                Date endTime = activityInfo.getEndTime();
                int l = (int) (endTime.getTime() - startTime.getTime());
                int i1 = random.nextInt(( l>0?l:10));
                i.setCreateTime(new Date(startTime.getTime()+i1));

                CommunityLife life = createLife(types, i);
                communityLifeMapper.insertSelective(life);

                Integer lifeId = life.getId();
                String strImage = i.getStrImage();
                Integer userId = i.getUserId();

                addUserActivityRecord(activityId, userId);
                addLifeImage(fileLocation, lifeId, strImage, userId);
                addLifeLike(userIds, lifeId, userId);

            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (convert != null) {
                convert.delete();
            }
        }

    }

    private void addUserActivityRecord(Integer activityId, Integer userId) {
        UserActivityRecord record=new UserActivityRecord();
        record.setActivityId(activityId);
        Date date = new Date();
        record.setCreateTime(date);
        record.setUpdateTime(date);
        record.setExitStatus(1);
        record.setUserId(userId);
        userActivityRecordMapper.insertSelective(record);
    }

    @Override
    public void uploadUser(MultipartFile file, String fileLocation) {

        String [] strFileds=Arrays.stream(ExcelUserDto.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        File convert=null;
        List<ImageInfo> imageInfos = imageInfoMapper.selectImageByCategory(3);
        try {
            convert = convert(file);
            List<ExcelUserDto> excelUserDtos = ExcelReaderUtils.readXlsContent(convert, strFileds, ExcelUserDto.class);
            assert excelUserDtos != null;

            excelUserDtos.forEach(u->{
                Integer userId = createMockUser(fileLocation, u);
                createMockUserSkin(imageInfos, userId);
                userIMService.doGetIMUser(userId);
            });
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (convert != null) {
                convert.delete();
            }
        }

    }

    private void createMockUserSkin(List<ImageInfo> imageInfos, Integer userId) {
        Date date = new Date();
        UserSkinRelation skin=new UserSkinRelation();
        skin.setCreateTime(date);
        skin.setUpdateTime(date);
        skin.setUserId(userId);
        int size = imageInfos.size();
        Random random = new Random();
        int i = random.nextInt(size);
        ImageInfo imageInfo = imageInfos.get(i);
        skin.setImageId(imageInfo.getId());
        skin.setImageUrl(imageInfo.getImage());
        userSkinRelationMapper.insertSelective(skin);
    }

    private Integer createMockUser(String fileLocation, ExcelUserDto u) {
        UserInfo record=new UserInfo();
        record.setNickName(u.getNickName());
        record.setImage(fileLocation+u.getImage());
        record.setCityName(u.getCity());
        record.setGender("男".equals(u.getStrGender())? UserEnum.UserGender.MAN.getCode():UserEnum.UserGender.FEMALE.getCode());
        record.setUserType(UserEnum.UserType.MOCK_USER.getCode());
        record.setUserRole(UserEnum.UserRoleEnum.ORDINARY_USER.getCode());
        ExcelLocationDto location=openCityMapper.selectLocationInfoByCityName(u.getCity());
        if (location != null) {
            record.setCityCode(location.getCityCode());
            ExcelLocationDto parent=openCityMapper.selectLocationInfoByCityCode(location.getParentCode());
            record.setProvinceCode(parent.getCityCode());
            record.setProvinceName(parent.getCityName());
        }
        Date date = new Date();

        record.setCreateTime(date);
        record.setUpdateTime(date);
        userInfoMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public void uploadUser(MultipartFile file, String fileLocation, Integer startId, Integer endId) {
        String [] strFileds=Arrays.stream(ExcelUserDto.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        File convert=null;
        List<ImageInfo> imageInfos = imageInfoMapper.selectImageByCategory(3);
        try {
            convert = convert(file);
            List<ExcelUserDto> excelUserDtos = ExcelReaderUtils.readXlsContent(convert, strFileds, ExcelUserDto.class);
            assert excelUserDtos != null;
            int size = excelUserDtos.size();
            endId=endId-startId>size?startId+size-1:endId;
            IntStream.rangeClosed(startId, endId).forEach(i->{
                ExcelUserDto u = excelUserDtos.get(i - startId);
                UserInfo record=new UserInfo();
                record.setId(i);
                record.setNickName(u.getNickName());
                record.setImage(fileLocation+u.getImage());
                record.setCityName(u.getCity());
                record.setGender("男".equals(u.getStrGender())?UserEnum.UserGender.MAN.getCode():UserEnum.UserGender.FEMALE.getCode());

                ExcelLocationDto location=openCityMapper.selectLocationInfoByCityName(u.getCity());
                if (location != null) {
                    record.setCityCode(location.getCityCode());
                    ExcelLocationDto parent=openCityMapper.selectLocationInfoByCityCode(location.getParentCode());
                    record.setProvinceCode(parent.getCityCode());
                    record.setProvinceName(parent.getCityName());
                }
                userInfoMapper.updateByPrimaryKeySelective(record);
            });
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (convert != null) {
                convert.delete();
            }
        }
    }

    private CommunityLife createLife(List<AppLifeTypeDto> types, ExcelLifeDto i) {
        CommunityLife life=new CommunityLife();
        life.setContent(i.getContent());
        life.setCreateTime(i.getCreateTime());
        life.setFromType(CommunityEnum.CommunityLifeFromType.LIFE.getCode());
        life.setPrivacyLevel(CommunityEnum.PrivacyLevelEnum.OPEN.getCode());
        life.setUpdateTime(i.getCreateTime());
        life.setUserId(i.getUserId());
        life.setStatus(CommunityEnum.CommunityLifeStatusEnum.NORMAL.getCode());
        life.setTypeId(getTypeId(types, i.getTypeName()));
        return life;
    }

    private CommunityLife createLife(List<AppLifeTypeDto> types, ExcelActivityLifeDto i) {
        CommunityLife life = new CommunityLife();
        life.setContent(i.getContent());
        life.setCreateTime(i.getCreateTime());
        life.setActivityId(i.getActivityId());
        life.setFromType(CommunityEnum.CommunityLifeFromType.ACTIVITY.getCode());
        life.setPrivacyLevel(CommunityEnum.PrivacyLevelEnum.ACTIVITY.getCode());
        life.setUpdateTime(i.getCreateTime());
        life.setUserId(i.getUserId());
        life.setStatus(CommunityEnum.CommunityLifeStatusEnum.NORMAL.getCode());
        life.setTypeId(getTypeId(types, i.getTypeName()));
        return life;
    }

    private void addLifeLike(List<Integer> userIds, Integer lifeId, Integer ownerId) {

        Random random=new Random();
        Date date=new Date();
        int i = random.nextInt(9)+2;

        int size = userIds.size();
        Set<Integer> set=new HashSet<>();
        for (int j = 0; j < i; j++) {
            set.add(userIds.get(random.nextInt(size)));
        }

        set.forEach(t->{
            CommunityLifeLikeRecord record=new CommunityLifeLikeRecord();
            record.setLifeId(lifeId);
            record.setOwnerId(ownerId);
            record.setCreateTime(date);
            record.setUpdateTime(date);
            record.setUserId(t);
            communityLifeLikeRecordMapper.insertSelective(record);
        });
    }

    private void addLifeImage(String fileLocation, Integer lifeId, String strImage, Integer userId) {
        List<String> collect=Collections.emptyList();
        collect = convertImageList(fileLocation, strImage, collect);
        Date date=new Date();
        collect.forEach(l->{
            CommunityLifeImage image=new CommunityLifeImage();
            image.setCoverImg(false);
            image.setCreateTime(date);
            image.setImageUrl(l);
            image.setLifeId(lifeId);
            image.setUpdateTime(date);
            image.setUserId(userId);
            communityLifeImageMapper.insertSelective(image);
        });
    }

    private List<String> convertImageList(String fileLocation, String strImage, List<String> collect) {
        if (StringUtils.isNotBlank(strImage)) {
            collect = Arrays.stream(strImage.replaceAll("；",";").split(";")).filter(s-> !s.isEmpty()).map(s -> fileLocation + s).collect(Collectors.toList());
        }
        return collect;
    }

    private Integer getTypeId(List<AppLifeTypeDto> types, String typeName) {
        Optional<Integer> any = types.stream().filter(t -> t.getTypeName().equals(typeName)).map(AppLifeTypeDto::getId).findAny();
        return any.orElse(0);
    }

    public File multipartToFile(MultipartFile file) throws IOException {
        File c = new File(Objects.requireNonNull(file.getOriginalFilename()));
        System.out.println(file.getOriginalFilename());
        file.transferTo(c);
        return c;
    }

    public File convert(MultipartFile file)throws IOException {
        File f = new File(Objects.requireNonNull(file.getOriginalFilename()));
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        fos.close();
        return f;
    }

}