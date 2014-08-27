package com.zwb.mp3tag.connector.junit;

import java.sql.Timestamp;

import com.zwb.mp3tag.connector.util.PropertyProvider;

import junit.framework.TestCase;

public class PropertyWriterTest extends TestCase
{
	public void testPropertyAccess()
	{
		String path = "c:\\zwb\\geekOlogy\\gkMp3TaggerConnector\\TestData";
		PropertyProvider w = new PropertyProvider(path, "sample.properties");
		System.out.println(w.printFormatted());
		w.setProperty("time", new Timestamp(System.currentTimeMillis()).toGMTString());
		w.setProperty("key."+Integer.toString((int)(Math.random()*100)), Integer.toString((int)(Math.random()*100)));
	}
}
