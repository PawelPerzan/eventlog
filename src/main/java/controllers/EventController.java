package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.EventService;

@Controller
public class EventController {
	private EventService eventService;
	private AuditService auditService;

	/**
	 * @param eventService
	 * @param auditService
	 */
	@Autowired
	public EventController(EventService eventService, AuditService auditService) {
		this.eventService = eventService; 
		this.auditService = auditService;
	}

	/**
	 * @return home view
	 */
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	/**
	 * Handle displaying the list of events
	 * @param eventId
	 * @return model
	 */
	@RequestMapping(value="/events", method=RequestMethod.GET)
	public ModelAndView listEvents() {
		ModelAndView model = new ModelAndView("events");
		model.addObject("events", this.eventService.findAllEvents());
		return model;
	}
	
	/**
	 * Handle displaying the list of audit logs
	 * @param eventId
	 * @return model
	 */
	@RequestMapping(value="/logs", method=RequestMethod.GET)
	public ModelAndView listLogs() {
		ModelAndView model = new ModelAndView("logs");
		model.addObject("logs", this.auditService.findAll());
		return model;
	}

	
	/**
	 * Handle displaying single event
	 * @param eventId
	 * @return model
	 */
	@RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
	public ModelAndView eventHandler(@PathVariable("eventId")int eventId) {
		ModelAndView model = new ModelAndView("detail");
		model.addObject(this.eventService.loadEvent(eventId));
		return model;
	}
}
