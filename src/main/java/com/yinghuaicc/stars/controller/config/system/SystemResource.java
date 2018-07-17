package com.yinghuaicc.stars.controller.config.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/6 下午1:42
 * @Description: 系统配置
 * @Modified:
 */
@Configuration
@PropertySource(value = "classpath:system.properties", encoding = "UTF-8")
public class SystemResource {

    @Value("${pc.aop.token.able.minutes}")
    private String tokenAbleMinutes;

    @Value("${file.upload.image.path.url}")
    private String fileUploadImagePathUrl;

    @Value("${file.upload.excel.path.url}")
    private String fileUploadExcelPathUrl;

    @Value("${file.upload.other.path.url}")
    private String fileUploadOtherPathUrl;

    @Value("${file.access.url}")
    private String fileAccessUrl;

    @Value("${external.url}")
    private String externalUrl;

    public String getTokenAbleMinutes() {
        return tokenAbleMinutes;
    }

    public SystemResource setTokenAbleMinutes(String tokenAbleMinutes) {
        this.tokenAbleMinutes = tokenAbleMinutes;
        return this;
    }

    public String getFileUploadImagePathUrl() {
        return fileUploadImagePathUrl;
    }

    public SystemResource setFileUploadImagePathUrl(String fileUploadImagePathUrl) {
        this.fileUploadImagePathUrl = fileUploadImagePathUrl;
        return this;
    }

    public String getFileUploadExcelPathUrl() {
        return fileUploadExcelPathUrl;
    }

    public SystemResource setFileUploadExcelPathUrl(String fileUploadExcelPathUrl) {
        this.fileUploadExcelPathUrl = fileUploadExcelPathUrl;
        return this;
    }

    public String getFileUploadOtherPathUrl() {
        return fileUploadOtherPathUrl;
    }

    public SystemResource setFileUploadOtherPathUrl(String fileUploadOtherPathUrl) {
        this.fileUploadOtherPathUrl = fileUploadOtherPathUrl;
        return this;
    }

    public String getFileAccessUrl() {
        return fileAccessUrl;
    }

    public SystemResource setFileAccessUrl(String fileAccessUrl) {
        this.fileAccessUrl = fileAccessUrl;
        return this;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public SystemResource setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
        return this;
    }
}
