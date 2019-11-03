package com.tuyano.springboot.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Suica;
import com.tuyano.springboot.repositories.SuicaRepository;
import com.tuyano.springboot.service.SuicaService;
import com.tuyano.springboot.socket.*;

@Controller
public class ServerController {

	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {
		mav.setViewName("server");
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				ServerSide s1 = new ServerSide();
				String line = s1.runSample();
				mav.addObject("msg",line);
			}
		};
		timer.schedule(task, 0, 1000);
		return mav;
	}
	//	@RequestMapping("/show_id")
	//	public ModelAndView send(ModelAndView mav) {
	//		List<Suica> list = service.findId();
	//		mav.addObject("datalits",list);
	//		return mav;
	//	}

}
