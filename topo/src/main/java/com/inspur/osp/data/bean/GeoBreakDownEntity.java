package com.inspur.osp.data.bean;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author liyakun
 * @2019年6月19日
 * @description:
 */
public class GeoBreakDownEntity {
	
	private String uuid;
	private String name;
	private String cityUUID;
	private String countyUUID;
	private String systemLevel;
	private String breakReason;
	private String breakdesc;
	private String lng;
	private String lat;
	private String pics;
	private String startTime;
	private String recoverTime;
	private String resType;
	private List<Map<String, String>> routeList;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityUUID() {
		return cityUUID;
	}
	public void setCityUUID(String cityUUID) {
		this.cityUUID = cityUUID;
	}
	public String getCountyUUID() {
		return countyUUID;
	}
	public void setCountyUUID(String countyUUID) {
		this.countyUUID = countyUUID;
	}
	public String getSystemLevel() {
		return systemLevel;
	}
	public void setSystemLevel(String systemLevel) {
		this.systemLevel = systemLevel;
	}
	public String getBreakReason() {
		return breakReason;
	}
	public void setBreakReason(String breakReason) {
		this.breakReason = breakReason;
	}
	public String getBreakdesc() {
		return breakdesc;
	}
	public void setBreakdesc(String breakdesc) {
		this.breakdesc = breakdesc;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}

	public List<Map<String, String>> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Map<String, String>> routeList) {
		this.routeList = routeList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRecoverTime() {
		return recoverTime;
	}

	public void setRecoverTime(String recoverTime) {
		this.recoverTime = recoverTime;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}
}
