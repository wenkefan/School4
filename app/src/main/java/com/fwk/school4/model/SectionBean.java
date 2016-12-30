package com.fwk.school4.model;

import java.util.ArrayList;

public class SectionBean {
	/**
	 * 每个分组的开始位置
	 */
	private ArrayList<Integer> sectionIndices = null ;
	/**
	 * 分组名（在分组栏上显示的）
	 */
	private ArrayList<String> sectionLetter = null;
	/**
	 * 每个分组学生数量
	 */
	private ArrayList<Integer> childCountBySection = null;
	/**
	 * 分组列表（所有站点）
	 */
	private ArrayList<StationBean> stationBeans = null;
//	/**
//	 * 所有学生
//	 */
//	private ArrayList<ChildrenBean> childrenBeans = null;

	public ArrayList<Integer> getSectionIndices() {
		return sectionIndices;
	}

	public ArrayList<String> getSectionLetter() {
		return sectionLetter;
	}

	public ArrayList<Integer> getChildCountBySection() {
		return childCountBySection;
	}

	public ArrayList<StationBean> getStationBeans() {
		return stationBeans;
	}

//	public ArrayList<ChildrenBean> getChildrenBeans() {
//		return childrenBeans;
//	}

	public void setSectionIndices(ArrayList<Integer> sectionIndices) {
		this.sectionIndices = sectionIndices;
	}

	public void setSectionLetter(ArrayList<String> sectionLetter) {
		this.sectionLetter = sectionLetter;
	}

	public void setChildCountBySection(ArrayList<Integer> childCountBySection) {
		this.childCountBySection = childCountBySection;
	}

	public void setStationBeans(ArrayList<StationBean> stationBeans) {
		this.stationBeans = stationBeans;
	}

//	public void setChildrenBeans(ArrayList<ChildrenBean> childrenBeans) {
//		this.childrenBeans = childrenBeans;
//	}
}
