package com.iisi.api.security;

public abstract interface FileSysUtil {
	
	public abstract UserInfo getUser();

	public abstract ExecutantType getExecutant();

	public abstract ExecutantType getExecutant(String paramString);

	public abstract String getUserId();

	public abstract String getSiteId();
}
