package common;
/*
@author Bhimashankar Teli
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Configurations {

    private Configurations() {

        System.out.println("Social Network:: Configuration Object Initialised");

    }

    private static Configurations instance = null;

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    private  Properties configProperties;
    private static Logger logger = LoggerFactory.getLogger(Configurations.class);
    private String urlBasePath;
    private String contentType;
    private String usersCount;
    private String postsCount;
    private String commentsCount;
}
