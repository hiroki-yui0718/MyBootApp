package com.tuyano.springboot.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.MyDataDaoImpl;
import com.tuyano.springboot.model.MyData;
import com.tuyano.springboot.model.Suica;
import com.tuyano.springboot.repositories.MyDataRepository;
import com.tuyano.springboot.service.MyDataService;
import com.tuyano.springboot.service.SuicaService;

@Controller
public class HeloController {
	
	@Autowired
	MyDataRepository repository;

	@Autowired
	SuicaService service2;
	@PersistenceContext
	EntityManager entityManager; //

	MyDataDaoImpl dao;
	
	@Autowired
	private MyDataService service;
	
//@RequestMapping("/{num}")
//public String index(@PathVariable int num) { //戻り地はString
//	int su= 0;
//	for(int i = 1;i <=num;i++) {
//		su+= i;
//	}
//	return "total:"+su;
////
//	
//String[] names = {"tuyano","hanako","taro","sachiko","ichiro"};
//String[] mails = {"syoda@tuuyano.com","hanako@flower","taro@yamada","sachiko@happy","ichiro@baseball"};
//
//	
//@RequestMapping("/{id}")
//public DataObject index(@PathVariable int id) {
//	return new DataObject(id,names[id],mails[id]);
//}
class DataObject{
	private int id;
	private String name;
	private String value;
	public DataObject(int id,String name,String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		
	}
	public int getId() {return id;}
	public String getName() {return name;}
	public String getValue() {return value;}
	
	public void setId(int id) {this.id = id;}
	public void setName(String Name) {this.name = name;}
	public void setValue(String Value) {this.value = value;}
}
//@RequestMapping("/{num}")
//public String index(@PathVariable int num,Model model) {//model重要
//	int res = 0;
//	for(int i = 1;i <= num;i++) res += i;
//	model.addAttribute("msg","total:" +res);
//	return "index"; 
//}
@RequestMapping("/show/{id}")
public ModelAndView show(@PathVariable int id,ModelAndView mav) {
	mav.setViewName("show");
	mav.addObject("id",id);
	mav.addObject("check",id % 2 == 0);
	mav.addObject("trueVal","Even number!");
	mav.addObject("falseVal","Odd number...");
	return mav;
}
@RequestMapping("/")
public ModelAndView index(ModelAndView mav,Authentication authentication) {
	mav.setViewName("index");
	mav.addObject("msg","message 1<hr>message 2<br>message 3");
//	DataObject obj = new DataObject(123,"hanako","hanako@flower");
//	mav.addObject("object",obj);
//	ArrayList<String[]> data = new ArrayList<String[]>();
//	data.add(new String[] {"taro","taro@yamada","080-999-999"});
//	data.add(new String[] {"hanako","hanako@flower","080-888-888"});
//	data.add(new String[] {"sachiko","sachiko@happy","090-999-999"});
	ArrayList<DataObject> data = new ArrayList<DataObject>();
	data.add(new DataObject (0,"taro","taro@yamada"));
	data.add(new DataObject (1,"hanako","hanako@flower"));
	data.add(new DataObject(2,"sachiko","sachiko@happy"));
	try {
	List<Suica> list = service2.getAll();
	mav.addObject("datalist",list);
	User userDetail = (User)authentication.getPrincipal();
	String name = userDetail.getUsername();
	long id = service2.findId(name);
	mav.addObject("id",id);
	}catch(NullPointerException e) {
		e.printStackTrace();
	}
	mav.addObject("data",data);
	return mav;
}
@RequestMapping(value="/",method=RequestMethod.POST)
public ModelAndView send(ModelAndView mav){ //メソッド名は適当
	mav.setViewName("index");
	return mav;
}
@RequestMapping("/other")
public String other() {
	return "redirect:/";
}
@RequestMapping("/home")
public String home() {
	return "forward:/";
}
@RequestMapping("/month/{month}")
public ModelAndView show2(@PathVariable int month,ModelAndView mav) {
	mav.setViewName("month");
	int m = Math.abs(month) % 12;
	m = m == 0 ? 12 : m;
	mav.addObject("month",m);
	mav.addObject("cehck",Math.floor(m/3));
	return mav;
}
@RequestMapping("/dev/{num}")
public ModelAndView dev(@PathVariable int num,ModelAndView mav) {
	mav.setViewName("dev");
	ArrayList<DataObject> data = new ArrayList<DataObject>();
	data.add(new DataObject (0,"taro","taro@yamada"));
	data.add(new DataObject (1,"hanako","hanako@flower"));
	data.add(new DataObject(2,"sachiko","sachiko@happy"));
	mav.addObject("data",data);
	mav.addObject("num",num);
	if(num>= 0) mav.addObject("check",num >= data.size() ? 0 : num);
	else  mav.addObject("check",num>= data.size() * -1 ? 0 : num * -1);
	return mav;
}
@RequestMapping("/tax/{tax}")
public ModelAndView tax(@PathVariable int tax,ModelAndView mav) {
	mav.setViewName("tax");
	mav.addObject("tax",tax);
	return mav;
}
@RequestMapping("/tmp")
public ModelAndView tmp(ModelAndView mav) {
	mav.setViewName("tmp");
	return mav;
}


@RequestMapping(value="/page",method=RequestMethod.GET)
public ModelAndView index(ModelAndView mav,Pageable pageable) {
	mav.setViewName("index");
	Page<MyData> list = repository.findAll(pageable);
	mav.addObject("datalist",list);
	return mav;
	
}
@RequestMapping(value="/user/data",method=RequestMethod.GET)
public ModelAndView data(@ModelAttribute("formModel") MyData mydata,ModelAndView mav) {
	mav.setViewName("data");
	mav.addObject("msg","MyData - Sample");
	Iterable<MyData> list = service.getAll(); //Hanpuku
	mav.addObject("datalist",list);
	return mav;
}
@RequestMapping(value="/user/data",method=RequestMethod.POST)
@Transactional(readOnly=false)
public ModelAndView form(
		@ModelAttribute("formModel")
		@Validated MyData mydata,
		BindingResult result,
		ModelAndView mov) {
	ModelAndView res = null;
	if(!result.hasErrors()) {
	repository.saveAndFlush(mydata);
	res = new ModelAndView("redirect:/data");
	}else {
		mov.setViewName("data");
		mov.addObject("msg","sorry error is occured...");
		Iterable<MyData> list = repository.findAll();
		mov.addObject("datalist",list);
		res = mov;
	} 
	return res;
}
@PostConstruct
public void init(){
	dao = new MyDataDaoImpl(entityManager);
	MyData d1 = new MyData();
}
@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
public ModelAndView edit(@PathVariable int id,@ModelAttribute("formModel") MyData mydata,ModelAndView mav) {
	mav.setViewName("edit");
	mav.addObject("msg","edit mydata");
	Optional<MyData> data = repository.findById((long)id);
	mav.addObject("formModel",data.get());
	return mav;
}
@RequestMapping(value="/edit",method=RequestMethod.POST)
@Transactional(readOnly=false)
public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav) {
	repository.saveAndFlush(mydata);
	return new ModelAndView("redirect:/data");
}
@RequestMapping(value="/find",method=RequestMethod.GET)
public ModelAndView find(ModelAndView mav) {
	mav.setViewName("find");
	mav.addObject("title","Find Page");
	mav.addObject("msg","MyDataのサンプルです");
	mav.addObject("value","");
	Iterable<MyData> list = service.getAll();
	mav.addObject("datalist",list);
	return mav;
}
@RequestMapping(value="/find",method=RequestMethod.POST)
public ModelAndView search(ModelAndView mav,HttpServletRequest request) {
	mav.setViewName("find");
	String param = request.getParameter("fstr");
	if(param == "") {
		mav = new ModelAndView("redirect:/find");
	}else {
		mav.addObject("title","Find Result");
		mav.addObject("msg","「" + param + "」の検索結果");
		mav.addObject("value",param); //消さないため
		List<MyData> list = service.find(param);
		mav.addObject("datalist",list);
	}
	return mav;
}
@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
public ModelAndView delete(@PathVariable int id,ModelAndView mav) {
	mav.setViewName("delete");
	mav.addObject("title","delete mydata.");
	Optional<MyData> data = repository.findById((long)id);
	mav.addObject("formModel",data.get());
	return mav;
}
@RequestMapping(value="/delete",method=RequestMethod.GET)
@Transactional(readOnly=false) //DBいじる時は書く
public ModelAndView remove(@RequestParam long id,ModelAndView mav) {
	repository.deleteById(id);
	return new ModelAndView("redirect:/");
}
}
