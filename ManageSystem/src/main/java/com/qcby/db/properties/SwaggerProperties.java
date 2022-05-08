package com.qcby.db.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO Swagger类
 *
 * @author ZT
 * <br>CreateDate 2021/8/30 11:24
 */

@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String title;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String version;
    private String description;
    private String basePackageRest;
    private String termsOfServiceUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasePackageRest() {
        return basePackageRest;
    }

    public void setBasePackageRest(String basePackageRest) {
        this.basePackageRest = basePackageRest;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    @Override
    public String toString() {
        return "SwaggerPropertues{" +
                "title='" + title + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactUrl='" + contactUrl + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", basePackageRest='" + basePackageRest + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                '}';
    }
}
