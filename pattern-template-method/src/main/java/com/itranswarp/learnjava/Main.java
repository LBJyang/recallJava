package com.itranswarp.learnjava;

import com.itranswarp.learnjava.templatemethod.AbstractSetting;
import com.itranswarp.learnjava.templatemethod.LocalSetting;
import com.itranswarp.learnjava.templatemethod.RedisSetting;

/**
 * App entry for Maven project.
 * 
 * @author liaoxuefeng
 */
public class Main {

	public static void main(String[] args) throws Exception {
		AbstractSetting setting1 = new LocalSetting();
		System.out.println("test = " + setting1.getSetting("test"));
		System.out.println("test = " + setting1.getSetting("test"));
		AbstractSetting setting2 = new RedisSetting();
		System.out.println("autosave = " + setting2.getSetting("autosave"));
		System.out.println("autosave = " + setting2.getSetting("autosave"));
	}
}
