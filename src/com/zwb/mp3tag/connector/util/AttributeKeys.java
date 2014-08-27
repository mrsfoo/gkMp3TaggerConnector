package com.zwb.mp3tag.connector.util;

public class AttributeKeys 
{
	private static final String ARTIST_NAME = "release.artist";
	private static final String RELEASE_NAME = "release.name";
	private static final String TRACK_NAME_PREFIX = "track.#.name";
	private static final String TRACK_CNT = "release.trackcnt";
	
	public static String getKeyArtistName()
	{
		return ARTIST_NAME;
	}
	
	public static String getKeyReleaseName()
	{
		return RELEASE_NAME;
	}
	
	public static String getKeyTrackName(int trackNo)
	{
		String key = new String(TRACK_NAME_PREFIX);
		key.replaceAll("#", Integer.toString(trackNo));
		return key;
	}
	
	public static String getKeyTrackCount()
	{
		return TRACK_CNT;
	}
}
