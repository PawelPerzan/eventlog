package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;

@Controller
public class EventController {
	private EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService; 
	}
	
	@RequestMapping("/home.do")
	public void home() {
	}

	/**
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value="/events.do", method=RequestMethod.GET)
	public ModelAndView listEvents() {
		ModelAndView model = new ModelAndView("events");
		model.addObject("events", this.eventService.findAllEvents());
		return model;
	}
}
