package com.door.match.controller;

import com.door.match.base.FileTypeEnum;
import com.door.match.base.UploadFileInfo;
import com.door.match.config.Global;
import com.door.match.utils.FileUtils;
import com.door.match.utils.FtdStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JackMore
 */
@RestController
@RequestMapping(value = "/upload/*", produces = "application/json;charset=UTF-8")
public class UploadController {

    @Autowired
    private Global global;

    /**
     * 图片上传 单个
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadSingle", method = {RequestMethod.POST})
    public UploadFileInfo uploadAttachments(@RequestParam("uploadFile") MultipartFile file) throws Exception {

        UploadFileInfo fileInfo = this.saveUploadFile(file);
        return fileInfo;
    }

    /**
     * 多个图片上传
     *
     * @param files
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadMulti", method = {RequestMethod.POST})
    public List<UploadFileInfo> uploadAttachment(@RequestParam("uploadFiles") List<MultipartFile> files) throws Exception {
        List<UploadFileInfo> fileInfos = saveUploadFile(files);
        return fileInfos;
    }


    private UploadFileInfo saveUploadFile(MultipartFile file) throws Exception {
        if (null == file)
            return null;
        String fileType = FileTypeEnum.OTHER.toCode();
        if (file.getContentType().contains("image")) {
            fileType = FileTypeEnum.PICTURE.toCode();
        } else {
            fileType = FileTypeEnum.DOCUMENT.toCode();
        }
        String path = global.getSystemPath() + FileUtils.separator() + "img";
        UploadFileInfo fileInfo = new UploadFileInfo();
        fileInfo.setSuffix(FileUtils.getSuffix(file.getOriginalFilename()));
        fileInfo.setAliasName(FtdStringUtil.GenerateRanNum(8) + "." + fileInfo.getSuffix());
        fileInfo.setFileType(fileType);
        fileInfo.setOldName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize() / 1000);// save size as kb
        fileInfo.setContextPath(global.getImgNgixPath() + FileUtils.separator() + "img/" + fileInfo.getAliasName());
        FileUtils.saveFileAsStream(file.getInputStream(), path + FileUtils.separator() + fileInfo.getAliasName());
        return fileInfo;
    }

    private List<UploadFileInfo> saveUploadFile(List<MultipartFile> files) throws Exception {
        if (files == null || files.size() == 0)
            return null;
        List<UploadFileInfo> infos = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            infos.add(this.saveUploadFile(multipartFile));
        }
        return infos;
    }
}
