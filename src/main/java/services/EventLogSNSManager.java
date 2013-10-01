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

/**
 * This is a utility class to manage SNS communication for entries and
 * comments.  It's largely responsible for taking the entities passed in
 * to the methods, breaking them down into SNS requests, and then
 * sending those requests to the SNS client.
 *
 * The SNS process involves three steps, all covered within this class:
 * <ol>
 * <li>Creating a new topic</li>
 * <li>Subscribing to a topic</li>
 * <li>Posting a new message to a topic</li>
 * </ol>
 *
 * We do not have support here for unsubscribing from a topic.  SNS
 * automatically handles the process of confirming a user's desire
 * to be subscribed.  Each message sent also includes an unsubscribe
 * link.  So this process can largely be dealt with automatically.
 *
 */
public class EventLogSNSManager  {

	private static final String EMAIL_PROTOCOL="email";
	private static final String SMS_PROTOCOL="sms";

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
	 * Creates the SNS topic associated with an entry.  When the topic is created,
	 * we will get an ARN (Amazon Resource Name) which uniquely identifies the
	 * SNS topic.  We write that ARN to the entry entity so that we can refer to it
	 * later when subscribing commenters, etc.
	 *
	 * @param entry the new entry that's associated with the topic
	 * @return the result returned from AWS
	 */
	public CreateTopicResult createTopic (Event event) {
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
	public PublishResult publish(Event event) {
		PublishRequest request = new PublishRequest();
		request.setTopicArn(event.getSnsArn());

		StringBuilder subject = new StringBuilder("Event posted'");
		subject.append("Title");					// hardcoded for now
		request.setSubject(subject.toString());

		StringBuilder body = new StringBuilder();
		body.append("The following event was added '").append(event.getName()).append("'\n");
		body.append("body of the notification");	// hardcoded for now

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
		SubscribeRequest request = new SubscribeRequest(event.getSnsArn(),EMAIL_PROTOCOL, email); 	// hard coded for now	
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

	public static String getKey () {
		Configuration config = Configuration.getInstance();
		return config.getProperty("accessKey");
	}

	public static String getSecret () {
		Configuration config = Configuration.getInstance();
		return config.getProperty("secretKey");
	}
}
