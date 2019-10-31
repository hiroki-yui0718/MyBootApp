package com.tuyano.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuyano.springboot.model.Data;
import com.tuyano.springboot.socket.*;
@Controller
public class SocketController {
	
	@RequestMapping(value="/line",method=RequestMethod.GET)
	public String send(@ModelAttribute("formModel") Data data) {
		ServerSide s1 = new ServerSide();
		s1.runSample();
		ClientSide s2 = new ClientSide();
		String line = data.getLine();
		s2.runSample(line);
		return "line";
	}
}
