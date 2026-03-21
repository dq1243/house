package com.dq.house.common.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DQ1243
 */
@Data
@ConfigurationProperties(prefix = "email.qq")
public class QQEmailProperties {
    String sendUsername;
    String sendPassword;
    String sendHost;
    String sendPort;
}
