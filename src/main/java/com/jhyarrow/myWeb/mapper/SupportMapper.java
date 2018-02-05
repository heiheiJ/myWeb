package com.jhyarrow.myWeb.mapper;

import java.util.ArrayList;

import com.jhyarrow.myWeb.domain.Support;

public interface SupportMapper {
	public void addSupport(Support support);
	public ArrayList<Support> getSupport(String date);
}
