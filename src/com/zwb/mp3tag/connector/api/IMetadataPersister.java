package com.zwb.mp3tag.connector.api;

import java.util.Map;

public interface IMetadataPersister 
{
	public String getValue(String key);
	public void setValue(String key, String value);
	public void setValues(Map<String,String> values);
	public String toString();
	public String printFormatted();
	public void sort();
}
