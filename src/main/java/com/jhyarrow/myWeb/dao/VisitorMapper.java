package com.jhyarrow.myWeb.dao;

import com.jhyarrow.myWeb.domain.Visitor;

public interface VisitorMapper {
	int getCnt();
	void updateCnt(int cnt);
	void addVisitor(Visitor visitor);
	int hasCome(String ip);
}
