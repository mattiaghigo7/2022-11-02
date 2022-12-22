package it.polito.tdp.itunes.model;

public class Playlist {
	private Integer playlistId;
	private String name;
	
	public Playlist(Integer playlistId, String name) {
		super();
		this.playlistId = playlistId;
		this.name = name;
	}

	public Integer getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Integer playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playlistId == null) ? 0 : playlistId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		if (playlistId == null) {
			if (other.playlistId != null)
				return false;
		} else if (!playlistId.equals(other.playlistId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	
}
