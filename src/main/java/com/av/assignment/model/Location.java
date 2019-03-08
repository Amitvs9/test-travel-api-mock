package com.av.assignment.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class Location{

    private String code, name, description;
    private Coordinates coordinates;
    private Location parent;
    private Set<Location> children;
    
    public Location(){
    	
    }
    public Location(String code,String name,String description){
    	this.code=code;
    	this.name=name;
    	this.description=description;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Location getParent() {
		return parent;
	}
	public void setParent(Location parent) {
		this.parent = parent;
	}
	public Set<Location> getChildren() {
		return children;
	}
	public void setChildren(Set<Location> children) {
		this.children = children;
	}

}