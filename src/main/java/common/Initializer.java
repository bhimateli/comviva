package common;
/*
@author Bhimashankar Teli
 */


import java.util.Properties;

import org.jose4j.lang.JoseException;
import org.testng.annotations.Test;
import util.PropertyFileSource;


public class Initializer extends Begin{

    private static Properties configProperties = new Properties();
    static Configurations configurationObj = Configurations.getInstance(); //Singleton
    protected static void init(String configFile) throws JoseException {
        System.out.println("Initializer class is going to begin "+configFile);
        PropertyFileSource propertyfileUtil = new PropertyFileSource();

        configProperties = propertyfileUtil.loadParameters(configFile);
        configurationObj.setUrlBasePath(configProperties.getProperty("BaseUrl"));
        configurationObj.setContentType(configProperties.getProperty("ContentType"));
        configurationObj.setUsersCount(configProperties.getProperty("TotalUsersCount"));
        configurationObj.setPostsCount(configProperties.getProperty("TotalNumberOfPOST"));
        configurationObj.setCommentsCount(configProperties.getProperty("TotalNumberOfComments"));


        System.out.println("The file name is "+configFile);
        System.out.println("Base URI is  "+configurationObj.getUrlBasePath());
        System.out.println("ContentType  "+configurationObj.getContentType());
        System.out.println("TotalUsersCount  "+configurationObj.getUsersCount());
        System.out.println("TotalNumberOfPOST  "+configurationObj.getPostsCount());
        System.out.println("TotalNumberOfComments  "+configurationObj.getCommentsCount());
    }


}
