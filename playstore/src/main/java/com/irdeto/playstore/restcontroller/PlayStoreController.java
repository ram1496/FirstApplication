package com.irdeto.playstore.restcontroller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.irdeto.playstore.datamodel.AppMetaData;
import com.irdeto.playstore.datamodel.IconImage;
import com.irdeto.playstore.datamodel.ResponseMessage;
import com.irdeto.playstore.repository.MongoDBRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/playstore/")
public class PlayStoreController {

	@Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

	@Autowired
	private MongoDBRepository repository;
	
	@GetMapping("/")
	public String greetings(){
		System.out.println("Please recv it");
		return "Done";
	}

	@GetMapping("getAllApps")
	public List<AppMetaData> getAllApps() {
		System.out.println("PlayStoreController.getAllApps() >>>>>>>>>>.  ANDROIDDDDDD");
		List<AppMetaData> findAll = repository.findAll();
		for(int i = 0; i<findAll.size(); i++) {
			System.out.println(">>>>>>>>>>>>>>..  " + findAll.get(i));
		}
		return findAll;
	}
//
//	@GetMapping("/getAppDetails/{id}")
//	public AppMetaData getAPK(@PathVariable String id) {
//		
//		Optional<AppMetaData> findById = repository.findById(Integer.parseInt(id));
//		if (findById.isPresent()) {
//			return findById.get();
//		}
//		return new AppMetaData();
//	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("appImage") MultipartFile file,@RequestParam("id") long id, @RequestParam("appName") String appName,@RequestParam("appDescription") String appDescription,@RequestParam("apk") MultipartFile apk,@RequestParam("country") String country){
		try {
			System.out.println("PlayStoreController.uploadFile()"+appName);
			DBObject metaData = new BasicDBObject(); 
	        metaData.put("type", "apk"); 
	        ObjectId apkFs = gridFsTemplate.store(
	          apk.getInputStream(), apk.getName(), apk.getContentType(), metaData); 
			repository.save(new AppMetaData(id,appName,appDescription,country ,apkFs,new Binary(BsonBinarySubType.BINARY, file.getBytes())));
		} catch (IOException e) {
			System.out.println("PlayStoreController.uploadFile() INSIDE CATCH");
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("image and apk upload succesfully"));
	
	}
	

//	@PostMapping("/upload")
//	public AppMetaData uploadFile(@RequestBody  AppMetaData app) {
////		System.out.println("Ye chala hai");
////		long i =  new Date().getTime();
////		app.setId(i);
////		try {
////			app.setAppImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
////		} catch (IOException e) {
////			e.printStackTrace();
////		} 
//		System.out.println(app);
//		//repository.insert(app);
//		return repository.save(app);
//	}
}
