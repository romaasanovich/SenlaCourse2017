package com.senla.autoservice;

import com.senla.autoservice.facade.Autoservice;

public class Test {

	public static void main(final String [] s) {
		System.out.println(Autoservice.getInstance().getAllFreePlaces());
		
	}
	
}
