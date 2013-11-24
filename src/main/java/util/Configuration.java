package util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple singleton class to manage loading the property files 
 */
public class Configuration {

    private static Configuration configuration = new Configuration();    

    public static final String S3_ENDPOINT_KEY = "S3";
    public static final String SNS_ENDPOINT_KEY = "SNS";
    private Properties simpleJpaProperties = new Properties();
    private Properties endpoints = new Properties();

    private static final String AWS_CREDENTIALS_PROPERTIES = "/AwsCredentials.properties";
    private static final String ENDPOINTS_PROPERTY_PATH = "/endpoints.properties";

    private Logger logger = Logger.getLogger(Configuration.class.getName());

    private Configuration() {
        try {
            simpleJpaProperties.load(this.getClass().getResourceAsStream(AWS_CREDENTIALS_PROPERTIES));
            endpoints.load(this.getClass().getResourceAsStream(ENDPOINTS_PROPERTY_PATH));
        } catch ( Exception e ) {
            logger.log(Level.SEVERE, "Unable to load configuration: " + e.getMessage(), e);
        }
    }

    public static final Configuration getInstance() {
        return configuration;
    }

    public String getProperty (String propertyName) {
        return simpleJpaProperties.getProperty(propertyName);
    }
    
    public String getServiceEndpoint(String service) {
        return endpoints.getProperty(service);
    }
}
