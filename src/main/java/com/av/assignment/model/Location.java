package com.av.assignment.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(NON_NULL)
public class Location {

    private String code, name, description;
    private Location parent;
    private Set<Location> children;
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
