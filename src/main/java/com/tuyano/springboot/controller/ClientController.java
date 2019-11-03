package com.tuyano.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.socket.*;
@Controller
public class ClientController {

	@RequestMapping(value="/line",method=RequestMethod.GET)
	public ModelAndView send(ModelAndView mav) {
		mav.setViewName("line");
		mav.addObject("msg","");
		return mav;
	}
	@RequestMapping(value="/line",method=RequestMethod.POST)
	public ModelAndView line(ModelAndView mav,HttpServletRequest request) {
		mav.setViewName("line");
		String line = request.getParameter("data");
		if(line == "") {
			mav = new ModelAndView("redirect:/line");
		}else {
			ClientSide s2 = new ClientSide();
			String data = s2.runSample(line);
			mav.addObject("msg",data);
		}
		return mav;
	}

}
