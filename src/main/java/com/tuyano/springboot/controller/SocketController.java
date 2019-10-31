package com.tuyano.springboot.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import com.tuyano.springboot.socket.*;
@Controller
public class SocketController {
	
	@PostConstruct
	void init() {
		ServerSide s1 = new ServerSide();
		s1.runSample();
		ClientSide s2 = new ClientSide();
		s2.runSample();
	}
}
