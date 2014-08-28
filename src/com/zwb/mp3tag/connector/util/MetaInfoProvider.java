package com.zwb.mp3tag.connector.util;

import com.zwb.mp3tag.connector.api.IMetadataPersister;

public class MetainfoProvider
{
	IMetadataPersister provider;
	
	public MetainfoProvider(String path, boolean simpleForm)
	{
		if(simpleForm)
		{
			throw new RuntimeException("NOT IMPLEMENTED YET!");
		}
		else
		{
			this.provider = new MetainfoPropertyPersister(path);
		}
	}

	public MetainfoProvider(String folder, String filename, boolean simpleForm)
	{
		if(simpleForm)
		{
			throw new RuntimeException("NOT IMPLEMENTED YET!");
		}
		else
		{
			this.provider = new MetainfoPropertyPersister(folder, filename);
		}
	}

	public String getArtistName()
	{
		return provider.getValue(AttributeKeys.getKeyArtistName());
	}
	
	public void setArtistName(String name)
	{
		provider.setValue(AttributeKeys.getKeyArtistName(), name);
	}

	public String getReleaseName()
	{
		return provider.getValue(AttributeKeys.getKeyReleaseName());
	}
	
	public void setReleaseName(String name)
	{
		provider.setValue(AttributeKeys.getKeyReleaseName(), name);
	}
	
	public String getTrackName(int trackNo)
	{
		return provider.getValue(AttributeKeys.getKeyTrackName(trackNo));
	}
	
	public void setTrackName(int trackNo, String trackName)
	{
		provider.setValue(AttributeKeys.getKeyTrackName(trackNo), trackName);
	}

}
