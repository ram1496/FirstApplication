package com.irdeto.playstore.datamodel;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "IconImage")
public class IconImage {
	@Id
	private long id;
	private Binary  appImage;
	public IconImage(long id, Binary appImage) {
		super();
		this.id = id;
		this.appImage = appImage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Binary getAppImage() {
		return appImage;
	}
	public void setAppImage(Binary appImage) {
		this.appImage = appImage;
	}
	
}
