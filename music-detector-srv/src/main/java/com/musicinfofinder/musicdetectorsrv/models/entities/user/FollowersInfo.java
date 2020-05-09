package com.musicinfofinder.musicdetectorsrv.models.entities.user;

/**
 * Contains information about the user's followers.
 * https://developer.spotify.com/documentation/web-api/reference/object-model/#followers-object
 */
public class FollowersInfo {
	private String href;
	private int total;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
