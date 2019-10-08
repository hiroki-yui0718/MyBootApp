package com.tuyano.springboot;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController {
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
@RequestMapping(value="/")
public ModelAndView index(ModelAndView mav) {
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
	mav.addObject("data",data);
	return mav;
}
@RequestMapping(value="/",method=RequestMethod.POST)
public ModelAndView send(@RequestParam("text1") String str,ModelAndView mav){ //メソッド名は適当
	mav.addObject("msg","こんにちは,"+str+"さん!!");
	mav.addObject("value",str);
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
}
