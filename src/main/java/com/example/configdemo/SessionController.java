package com.example.configdemo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController
{
	private final HttpSession session;

	@Autowired
	public SessionController(HttpSession session)
	{
		this.session = session;
	}

	@RequestMapping("/")
	public String previous()
	{
		String message = "no previous message in session!";
		Object sessionObject = session.getAttribute("message");
		if (sessionObject instanceof String) {
			message = (String) sessionObject;
		}
		return message;
	}

	@GetMapping("/set")
	public String setMessage(@RequestParam(value = "message", required = false) String message)
	{
		session.setAttribute("message", message);
		return "message " + message + " set!";
	}

	@GetMapping("/clear")
	public String clearMessage()
	{
		session.removeAttribute("message");
		return "session's message has been cleared.";
	}
}