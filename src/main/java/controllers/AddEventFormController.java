package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.EventLogSNSManager;
import services.EventService;
import domain.Event;

/**
 * {@link AddEventFormController} controller responsible for adding events and
 * sending notifications 
 */
@Controller
@RequestMapping("/addEvent.do")
public class AddEventFormController {
	private EventService eventService;
	private static final String RECIPIENT_EMAIL = "put_your_email_here";

	/**
	 * @param eventService
	 */
	@Autowired
	public AddEventFormController(EventService eventService) {
		this.eventService = eventService; 
	}
	
	/**
	 * @param dataBinder
	 */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
    }

    /**
     * Sets up the initial form
     * @param model
     * @return initial view
     */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("event", new Event());
		return "addEvent";
	}

	/**
	 * Process the form submission
	 * @param event
	 * @param result
	 * @param model
	 * @return the view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(Event event, BindingResult result, Model model) {
		eventService.saveEvent(event);
		sendNotification(event, "ADD");
		return "home";
	}
	
	/**
	 * Helper method to send notification
	 * @param event
	 * @return Event that was 
	 */
	private Event sendNotification(Event event, String actionPerformed) {
		EventLogSNSManager sns = new EventLogSNSManager();
		event.setId(0);
		sns.createTopic(event);
		sns.subscribe(event, RECIPIENT_EMAIL);
		sns.publish(event, actionPerformed);
		return event;	
	}
}