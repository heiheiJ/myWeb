package com.jhyarrow.myWeb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.Music;
import com.jhyarrow.myWeb.mapper.MusicMapper;
import com.jhyarrow.myWeb.service.MusicService;

public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicMapper musicMapper;
	
	public ArrayList<Music> getMusicList() {
		return musicMapper.getMusicList();
	}

}
