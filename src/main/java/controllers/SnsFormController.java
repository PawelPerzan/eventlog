package controllers;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/sendSns.do")
public class SnsFormController {
	private EventService eventService;

	@Autowired
	public SnsFormController(EventService eventService) {
		this.eventService = eventService; 
	}
	

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields(new String[] {"id"});
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("event", new Event());
		return "sendSns";
	}

	@RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
	public String processSubmit(Event event, BindingResult result, Model model, HttpServletRequest request) {
		// send sns
		event = new Event();
		event.setId(0);
		event.setName("Sample event");

		EventLogSNSManager sns = new EventLogSNSManager();
		sns.createTopic(event);

		if (request.getParameter("name") != null) {
			sns.subscribe(event, request.getParameter("name"));
		}

		//sns.publish(event, "ADD Event");

		return "redirect:home.do";
	}
}

