package com.masai.exceptions;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	String resource_name;
	String field_name;
	String field_value1;
	Integer field_value2;

	public ResourceNotFoundException(String rName, String fName, String fValue1) {
		super(String.format("%s Not Found With This %s : %s", rName, fName, fValue1));
		this.resource_name = rName;
		this.field_name = fName;
		this.field_value1 = fValue1;
	}

	public ResourceNotFoundException(String rName, String fName, Integer fValue2) {
		super(String.format("%s Not Found With This %s : %s", rName, fName, fValue2));
		this.resource_name = rName;
		this.field_name = fName;
		this.field_value2 = fValue2;
	}

}
