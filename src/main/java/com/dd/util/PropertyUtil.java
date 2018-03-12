package com.dd.util;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {

    private static Logger logger = Logger.getLogger(PropertyUtil.class);

    private static PropertyUtil instance = new PropertyUtil();

    public static String getProperty(String para) {
        String value = defaultString(instance.prop.getProperty(para)).trim();
        if (isBlank(value))
            logger.warn(format("Cannot find property %s", para));
        return value;
    }

//    public static String getPassword(String para) {
//        return Security.decrypt(getProperty(para));
//    }

    private Properties prop = new Properties();

    private PropertyUtil() {
        try {
            prop.load(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(format("hgdd.properties"))));
        } catch (IOException ex) {
            String errorMsg = "Fail to read properties file!";
            logger.fatal(errorMsg, ex);
            System.exit(1);
        }
    }
}

