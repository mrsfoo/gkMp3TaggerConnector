package com.zwb.mp3tag.connector.junit;

import java.sql.Timestamp;

import com.zwb.mp3tag.connector.util.MetainfoPropertyPersister;

import junit.framework.TestCase;

public class PropertyProviderTest extends TestCase
{
	public void testPropertyAccess()
	{
		String path = "c:\\zwb\\geekOlogy\\gkMp3TaggerConnector\\TestData";
		MetainfoPropertyPersister w = new MetainfoPropertyPersister(path, "sample.properties");
		System.out.println(w.printFormatted());
		w.setValue("time", new Timestamp(System.currentTimeMillis()).toGMTString());
		w.setValue("key."+Integer.toString((int)(Math.random()*100)), Integer.toString((int)(Math.random()*100)));
		w.sort();
		System.out.println("------------------------------------");
		System.out.println(w.printFormatted());
	}
}
