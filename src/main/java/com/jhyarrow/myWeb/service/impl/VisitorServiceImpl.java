package com.jhyarrow.myWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhyarrow.myWeb.domain.Visitor;
import com.jhyarrow.myWeb.mapper.VisitorMapper;
import com.jhyarrow.myWeb.service.VisitorService;

public class VisitorServiceImpl implements VisitorService{
	@Autowired
	private VisitorMapper visitormapper;
	
	public int getCnt() {
		return visitormapper.getCnt();
	}

	public void addVisitor(Visitor visitor) {
		visitormapper.addVisitor(visitor);
	}

	public boolean hasCome(String ip) {
		if(visitormapper.hasCome(ip)>0) {
			return true;
		}else {
			return false;
		}
	}

}
