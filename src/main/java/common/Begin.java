package common;
/*
@author Bhimashankar Teli
 */

import org.databene.feed4testng.FeedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Begin extends FeedTest {

    protected Logger logger = LoggerFactory.getLogger(Begin.class);

    @BeforeSuite(alwaysRun = true)
    @Parameters({ "config_file" })
    public void setUpTest(@Optional("C:\\project\\expense\\comviva\\src\\test\\resources\\MainDriver.Properties") String configFile) throws Exception {
        System.out.println("Begin class is going to begin");
        if (configFile.isEmpty()) {
            configFile = "C:\\project\\expense\\comviva\\src\\test\\resources\\MainDriver.Properties";
        }
        Initializer.init(configFile);
    }


}