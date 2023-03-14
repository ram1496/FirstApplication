package com.irdeto.playstore.datamodel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Annotations
@Data
@Document(collection = "AppMetaData")

//Class
public class AppMetaData {
	@Id
	private long id;
	private String appName;
	private String appDescription;
	private String country;
	private ObjectId apk;
	private Binary  appImage;
	
	
	
	public long getId() {
		return id;
	}
	
	public AppMetaData(long id, String appName, String appDescription, String country,ObjectId apk,Binary image) {
		super();
		this.id = id;
		this.appName = appName;
		this.appDescription = appDescription;
		this.country = country;
		this.apk = apk;
		this.appImage = image;
		
	}

	

	public AppMetaData() {
		super();
	}

	public AppMetaData(String appName, String appDescription, String country) {
		super();
		this.appName = appName;
		this.appDescription = appDescription;
		this.country = country;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AppMetaData [id=" + id + ", appName=" + appName + ", appDescription=" + appDescription + ", country="
				+ country + ", apk=" + apk + ", appImage=" + appImage + "]";
	}

	public Binary getAppImage() { return appImage; }
	  
	  public void setAppImage(MultipartFile appImage) { 
		  System.out.println("AppMetaData.setAppImage() ==== 1 ");
		  try {
		this.appImage = new Binary(BsonBinarySubType.BINARY, appImage.getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }

	public ObjectId getApk() {
		return apk;
	}

	public void setApk(ObjectId apk) {
		this.apk = apk;
	}
	 
	
	

}