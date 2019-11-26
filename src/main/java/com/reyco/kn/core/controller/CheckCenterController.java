package com.reyco.kn.core.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.reyco.kn.core.service.WebSocketServer;

@Controller
public class CheckCenterController {
	//页面请求主页
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView("/index");
		return mv;
	}
	//页面请求
	@GetMapping("/socket/{cid}")
	public ModelAndView socket(@PathVariable(name="cid") String cid) {
		ModelAndView mv=new ModelAndView("/socket");
		mv.addObject("cid",cid);
		return mv;
	}
	//推送数据接口
	//@ResponseBody
	@RequestMapping("/socket/push")
	public ModelAndView pushToWeb(String cid,String message) {  
		ModelAndView view = new ModelAndView("/index");
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			view.addObject("error",e.getMessage());
			return view;
		}  
		return view;
	} 
} 
