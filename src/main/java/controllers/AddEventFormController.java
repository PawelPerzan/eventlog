package controllers;

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
@RequestMapping("/addEvent.do")
public class AddEventFormController {
	private EventService eventService;

	@Autowired
	public AddEventFormController(EventService eventService) {
		this.eventService = eventService; 
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
    }

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("event", new Event());
		return "addEvent";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(Event event, BindingResult result, Model model) {
		// events by event name
		String e = event.getName();
		eventService.saveEvent(event);
		return "home";
	}
}