package com.jhyarrow.myWeb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhyarrow.myWeb.domain.Music;
import com.jhyarrow.myWeb.domain.Visitor;
import com.jhyarrow.myWeb.service.MusicService;
import com.jhyarrow.myWeb.service.VisitorService;

@Controller
public class IndexController {
	@Autowired
	private VisitorService visitorService;
	@Autowired
	private MusicService musicService;
	
	@RequestMapping("/index")
	public ModelAndView getIndex(HttpServletRequest request) throws Exception{
		String ip = request.getRemoteAddr();
		String port = request.getRemoteHost();
		if(!visitorService.hasCome(ip)) {
			Visitor visitor = new Visitor();
			visitor.setIp(ip);
			visitor.setPort(port);
			visitorService.addVisitor(visitor);
		}
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }
	
	@RequestMapping("/getMain")
	public ModelAndView getMain(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        return modelAndView;
    }
	
	@RequestMapping("/getMusic")
	public ModelAndView getMusic(HttpServletRequest request) throws Exception{
		ArrayList<Music> musicList = musicService.getMusicList();
		int size = musicList.size();
		int tmp = (int) Math.floor(Math.random() * size);
		String path = musicList.get(tmp).getPath();
		String name = musicList.get(tmp).getName();
		if(path == null || path.length() == 0) {
			path = "music/";
		}
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music");
        modelAndView.addObject("musicPath", path+name);
        return modelAndView;
    }
}
