package controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import services.EventService;
import domain.Event;


@Controller
@RequestMapping("/events/{eventId}/edit")
@SessionAttributes(types = Event.class)
public class EditEventController {
	private EventService eventService;
	
	/**
	 * @param eventService
	 */
	@Autowired
	public EditEventController(EventService eventService) {
		this.eventService = eventService; 
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("eventId") int eventId, Model model) {
		Event event = this.eventService.loadEvent(eventId);
		model.addAttribute(event);
		return "eventForm";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(@ModelAttribute Event event, BindingResult result, SessionStatus status) {
		this.eventService.saveEvent(event);
		status.setComplete();
		return "redirect:/events";
	}
}
