package com.tuyano.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.socket.*;

@Controller
public class ServerController {

	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {
		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		mav.addObject("msg",line);
		return mav;
	}
//	@RequestMapping("/show_id")
//	public ModelAndView send(ModelAndView mav) {
//		List<Suica> list = service.findId();
//		mav.addObject("datalist",list);
//		return mav;
//	}

}
