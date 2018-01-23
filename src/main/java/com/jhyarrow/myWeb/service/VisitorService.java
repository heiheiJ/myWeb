package com.jhyarrow.myWeb.service;

import com.jhyarrow.myWeb.domain.Visitor;

public interface VisitorService {
	int getCnt();
	void addVisitor(Visitor visitor);
	boolean hasCome(String ip);
}
