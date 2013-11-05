package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.EventService;
import domain.Event;

@Controller
@RequestMapping("/findEvents")
public class FindEventsFormController {
	private EventService eventService;

	/**
	 * 
	 * @param eventService
	 */
	@Autowired
	public FindEventsFormController(EventService eventService) {
		this.eventService = eventService; 
	}

	/**
	 * 
	 * @param dataBinder
	 */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
    }

    /**
     * 
     * @param model
     * @return
     */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("event", new Event());
		return "findEvents";
	}

	/**
	 * @param event
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(Event event, BindingResult result, Model model) {
		// events by event name
		String e = event.getName();
		Collection<Event> results = eventService.findEvents(e);
		if (results.size() < 1) {
				result.rejectValue("name", "notFound", "not found");
			return "findEvents";
		}
		if (results.size() > 1) {
			// multiple events found
			model.addAttribute("selections", results);
			return "events";
		} else {
			// 1 event found
			event = results.iterator().next();
			return "redirect:event.do?eventId=" + event.getId();
		}
	}
}