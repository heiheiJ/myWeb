package com.jhyarrow.myWeb.mapper;

import com.jhyarrow.myWeb.domain.Visitor;

public interface VisitorMapper {
	int getCnt();
	void updateCnt(int cnt);
	void addVisitor(Visitor visitor);
	int hasCome(String ip);
}
