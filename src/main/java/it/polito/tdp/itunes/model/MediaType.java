package it.polito.tdp.itunes.model;

public class MediaType {
	
	private Integer mediaTypeId;
	private String name;
	
	public MediaType(Integer mediaTypeId, String name) {
		super();
		this.mediaTypeId = mediaTypeId;
		this.name = name;
	}

	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
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
		result = prime * result + ((mediaTypeId == null) ? 0 : mediaTypeId.hashCode());
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
		MediaType other = (MediaType) obj;
		if (mediaTypeId == null) {
			if (other.mediaTypeId != null)
				return false;
		} else if (!mediaTypeId.equals(other.mediaTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
}
