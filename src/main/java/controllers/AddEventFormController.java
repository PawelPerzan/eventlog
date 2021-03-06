package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.AuditService;
import services.EventLogSNSManager;
import services.EventService;
import domain.AuditLog;
import domain.Event;
import event.constants.EventLogConstants;

/**
 * {@link AddEventFormController} controller responsible for adding events and
 * sending notifications 
 */
@Controller
@RequestMapping("/addEvent")
public class AddEventFormController {
	private EventService eventService;
	private AuditService auditService;

	/**
	 * 
	 * @param eventService
	 * @param auditService
	 */
	@Autowired
	public AddEventFormController(EventService eventService, AuditService auditService) {
		this.eventService = eventService; 
		this.auditService = auditService;
	}

	
	/**
	 * Set disallowed fields to prevents from entering by the user 
	 * by modifying request 
	 * @param dataBinder
	 */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
    }
    
    /**
     * 
     * @param binder databinder object that connects to the date editor
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
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
		EventLogSNSManager sns = new EventLogSNSManager();
		//Validate event
        if (event.getName().equals("")) {
            result.reject("name", "Event Name cannot be blank");
        } 
        if (event.getDescription().equals("")) {
        	result.reject("description", "Description cannot be blank");
        }  
        if (event.getLocation().equals("")) {
        	result.reject("location", "Location cannot be blank");
        }
		
		if (result.hasErrors()) {
			return "addEvent";
		} else {
			eventService.saveEvent(event);
			
			AuditLog log = new AuditLog();
			log.setUserId(EventLogConstants.RECIPIENT_EMAIL);
			
			Date d = new Date();
		    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy:HH:mm:SS");
		    df.format(d);
			log.setAuditDate(df.format(d));
			log.setAuditMessage("Event Added");
			log.setObjectId(event.getName());
			auditService.saveAuditLog(log);

			sns.sendAddNotification(event, "ADD");
			model.addAttribute("events", eventService.findAllEvents());
			return "events";
		}
	}
}