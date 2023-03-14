package com.irdeto.playstore.datamodel;

import java.io.File;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Apk")
public class Apk {
	private String fileName;
	private String filePath;
//	private File apkFile;
//	public File getApkFile() {
//		return apkFile;
//	}
//	public void setApkFile(File apkFile) {
//		this.apkFile = apkFile;
//	}


}
