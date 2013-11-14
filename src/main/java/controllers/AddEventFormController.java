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

import services.EventLogSNSManager;
import services.EventService;
import domain.Event;

/**
 * {@link AddEventFormController} controller responsible for adding events and
 * sending notifications 
 */
@Controller
@RequestMapping("/addEvent")
public class AddEventFormController {
	private EventService eventService;

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
		EventLogSNSManager sns = new EventLogSNSManager();
		//Verify event creation
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
			sns.sendAddNotification(event, "ADD");
			model.addAttribute("events", eventService.findAllEvents());
			return "events";
		}
	}
	
	 /**
     * @param binder the spring databinder object that we connect to the date editor
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}