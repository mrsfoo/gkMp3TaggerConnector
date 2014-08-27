package com.zwb.mp3tag.connector.util;

public class MetaInfoProvider extends PropertyProvider
{
	public MetaInfoProvider(String path)
	{
		super(path);
	}

	public MetaInfoProvider(String folder, String filename)
	{
		super(folder, filename);
	}

	public String getArtistName()
	{
		return this.getProperty(AttributeKeys.getKeyArtistName());
	}
	
	public void setArtistName(String name)
	{
		this.setProperty(AttributeKeys.getKeyArtistName(), name);
	}

	public String getReleaseName()
	{
		return this.getProperty(AttributeKeys.getKeyReleaseName());
	}
	
	public void setReleaseName(String name)
	{
		this.setProperty(AttributeKeys.getKeyReleaseName(), name);
	}

}
