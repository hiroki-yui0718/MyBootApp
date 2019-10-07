package com.tuyano.springboot;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
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
@RequestMapping(value="/")
public ModelAndView index(ModelAndView mav) {
	mav.setViewName("index");
	mav.addObject("msg","currnt data.");
	DataObject obj = new DataObject(123,"hanako","hanako@flower");
	mav.addObject("object",obj);
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
}
