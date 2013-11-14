package services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import util.Configuration;
import util.StageUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

import domain.Event;
import event.constants.EventLogConstants;

public class EventLogSNSManager  {

	private static final String EMAIL_PROTOCOL="email";

	private static Logger logger = Logger.getLogger(EventLogSNSManager.class.getName());

	private static AmazonSNSClient snsClient;

	static {
		AWSCredentials creds = new BasicAWSCredentials(getKey(), getSecret());
		snsClient = new AmazonSNSClient(creds);
        String snsEndpoint = Configuration.getInstance().getServiceEndpoint(Configuration.SNS_ENDPOINT_KEY);
        if ( snsEndpoint != null ) {
            snsClient.setEndpoint(snsEndpoint);
        }
	}

	/**
	 * Creates the SNS topic associated with an entry.  
	 */
	public CreateTopicResult createTopic(Event event) {
		CreateTopicRequest request = new CreateTopicRequest(getTopicName(event));
		CreateTopicResult result = snsClient.createTopic(request);
		event.setSnsArn(result.getTopicArn());
		return result;
	}

	/**
	 * 
	 * @param event
	 */
	public void deleteTopic(Event event) {
		DeleteTopicRequest request = new DeleteTopicRequest(event.getSnsArn());
		snsClient.deleteTopic(request);
	}

	/**
	 * Publish method
	 * @param event
	 * @return {@link PublishRequest} object
	 */
	public PublishResult publish(Event event, String actionPerformed, String body) {
		PublishRequest request = new PublishRequest();
		request.setTopicArn(event.getSnsArn());

		StringBuilder subject = new StringBuilder("Event posted: ");
		subject.append(event.getName() + " - Action: " + actionPerformed);					
		request.setSubject(subject.toString());

		request.setMessage(body.toString());
		return snsClient.publish(request);
	}

	/**
	 * Subscribe method
	 * @param event
	 * @param email
	 * @return {@link SubscribeRequest} object
	 */
	public SubscribeResult subscribe(Event event, String email) {
		if (StringUtils.isEmpty(event.getSnsArn())) {
			//If ARN isn't set then entry didn't have an SNS topic created with it so we ignore
			logger.log(Level.WARNING,"Entry did not have an SNS topic associated with it");
			return null;
		}
		SubscribeRequest request = new SubscribeRequest(event.getSnsArn(),EMAIL_PROTOCOL, email); 		
		SubscribeResult result = snsClient.subscribe(request);
		return result;
	}

	/**
	 * This method returns a unique topic name by using the entry id.
	 * @param entry the entry to get a topic name for
	 * @return returns the topic name
	 */
	private String getTopicName (Event event) {
		return "entry" + StageUtils.getResourceSuffixForCurrentStage() + "-" + "0";		// hard coded for now
	}

	/**
	 * @return key
	 */
	public static String getKey() {
		Configuration config = Configuration.getInstance();
		return config.getProperty("accessKey");
	}

	/**
	 * @return
	 */
	public static String getSecret() {
		Configuration config = Configuration.getInstance();
		return config.getProperty("secretKey");
	}
	
	/**
	 * Helper method to send notification
	 * @param event
	 * @return Event that was 
	 */
	public Event sendAddNotification(Event event, String actionPerformed) {
		EventLogSNSManager sns = new EventLogSNSManager();
		sns.createTopic(event);
		sns.subscribe(event, EventLogConstants.RECIPIENT_EMAIL);
		sns.publish(event, actionPerformed, "Hello,\n Please note, a new event  id: " + event.getId() + " " + EventLogConstants.ADDEVENT_MESSAGE);
		return event;	
	}
	
	/**
	 * Helper method to send notification
	 * @param event
	 * @return Event that was 
	 */
	public Event sendEditNotification(Event event, String actionPerformed) {
		EventLogSNSManager sns = new EventLogSNSManager();
		sns.createTopic(event);
		sns.subscribe(event, EventLogConstants.RECIPIENT_EMAIL);
		sns.publish(event, actionPerformed, "Hello,\n Please note, an existing event  id: " + event.getId() + " " + EventLogConstants.EDITEVENT_MESSAGE);
		return event;	
	}
}
