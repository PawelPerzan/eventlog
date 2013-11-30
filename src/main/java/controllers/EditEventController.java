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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import services.AuditService;
import services.EventLogSNSManager;
import services.EventService;
import domain.AuditLog;
import domain.Event;
import event.constants.EventLogConstants;


@Controller
@RequestMapping("/events/{eventId}/edit")
@SessionAttributes("event")
public class EditEventController {
	private EventService eventService;
	private AuditService auditService;

	/**
	 * 
	 * @param eventService
	 * @param auditService
	 */
	@Autowired
	public EditEventController(EventService eventService, AuditService auditService) {
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
		dataBinder.setDisallowedFields("id");
	}
	
	/**
     * @param binder the spring databinder object connected to the date editor
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * Set up the form while editing event
     * @param eventId
     * @param model
     * @return
     */
	@RequestMapping(method = {RequestMethod.GET})
	public String setupForm(@PathVariable("eventId") int eventId, Model model) {
		
		Event event = this.eventService.loadEvent(eventId);
		model.addAttribute("event", event);
		return "eventForm";
	}

	/**
	 * Submit the form with updated event while editing one event
	 * @param event
	 * @param result
	 * @param status
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST})
	public String processSubmit(@ModelAttribute("event") Event event, BindingResult result, SessionStatus status) {
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
			return "eventForm";
		} else {
			this.eventService.saveEvent(event);
			
			AuditLog log = new AuditLog();
			log.setUserId(EventLogConstants.RECIPIENT_EMAIL);
			log.setAuditDate(new Date());
			log.setAuditMessage("Event Edited");
			log.setObjectId(event.getName());
			auditService.saveAuditLog(log);
			
			sns.sendEditNotification(event, "EDIT");
			status.setComplete();
			return "redirect:/events";
		}
	}
}
