package com.mosso.client.cloudfiles;

import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14.
 */
public class FilesClientTest {
	@Test
	public void listContainersInfo() throws Exception {
		List<FilesContainerInfo>  list = filesClient.listContainersInfo();
		for (FilesContainerInfo info : list) {
			System.out.println(info.toString());
		}
	}

	@Test
	public void getObjectMetaData() throws Exception {
		FilesObjectMetaData metaData = filesClient.getObjectMetaData("xuchuntest", "objectWithMeta.ext");
		System.out.println(metaData.toString());

	}
	@Test
	public void storeObjectWithMeta() throws Exception {
		File file = new File("c:\\textMeta.txt");
		if (!file.exists()) file.createNewFile();
		FileUtils.write(file, "ddd");
		Map map = new HashMap<>();
		map.put("maaa", "zidingyi");
		filesClient.storeObjectAs("xuchuntest", file, "ext", "objectWithMeta.ext", map);
		listObjects();
	}

	@Test
	public void deleteObject() throws Exception {
		filesClient.deleteObject("xuchuntest", "destination2.ext");
		listObjects();
	}

	@Test
	public void storeObjectAs() throws Exception {
		File file = new File("c:\\text.txt");
		if (!file.exists()) file.createNewFile();
		FileUtils.write(file, "ddd");
		filesClient.storeObjectAs("xuchuntest", file, "ext","destination2.ext");
		listObjects();
	}

	@Test
	public void storeObject() throws Exception {
		byte[] serverByte = filesClient.getObject("xuchuntest", "destination2.ext");
		Assert.assertTrue(serverByte.length > 0 );
	}

	@Test
	public void deleteContainer() throws Exception {
		filesClient.deleteContainer("testCreateContainer2");
		this.listContainers();
	}

	@Test
	public void createContainer() throws Exception {
		filesClient.createContainer("testCreateContainer4");
		this.listContainers();
	}


	FilesClient filesClient;

	@Before
	public void login() throws Exception {
		filesClient = new FilesClient("testuser:swift", "tthksqNaJdS6RvNaOh9p5aTRu5qkMDCLITi7iYFM","","http://192.168.6.93/auth/");
		filesClient.login();
	}

	@Test
	public void listContainers() throws Exception {
		List<FilesContainer> list = filesClient.listContainers();
		for (FilesContainer constants : list) {
			System.out.println(constants.getName());
		}
	}
	@Test
	public void listObjects() throws Exception {
		List<FilesObject> list = filesClient.listObjects("xuchuntest");
		for (FilesObject constants : list) {
			System.out.println(constants.toString());
		}
	}
	@Test
	public void listObjectInfo() throws Exception {
		List<FilesObject> list = filesClient.listObjects("temp",4);
		for (FilesObject constants : list) {
			System.out.println(constants.toString());
		}
		System.out.println(list.size());

		list = filesClient.listObjects("temp",5,"0016bbc3-a582-43a9-a77b-5a7b8009c6d6.jpg");
		for (FilesObject constants : list) {
			System.out.println(constants.toString());

		}
		System.out.println(list.size());
	}

	@Test
	public void listObjectsStaringWith() throws Exception {
		List<FilesObject> list = filesClient.listObjectsStaringWith("temp","","",4,"");
		for (FilesObject constants : list) {
			System.out.println(constants.toString());
		}
		System.out.println(list.size());

		list = filesClient.listObjects("temp",5,"导入模板");
		for (FilesObject constants : list) {
			System.out.println(constants.toString());

		}
		System.out.println(list.size());
	}

	@Test
	public void listObjectsEndWith() throws Exception {
//		List<FilesObject> list = filesClient.listObjectsStaringWith("temp","","",4,"0016bbc3-a582-43a9-a77b-5a7b8009c6d6.jpg");
		List<FilesObject> list = new ArrayList<>();

		for (FilesObject constants : list) {
			System.out.println(constants.toString());
		}
		System.out.println(list.size());

		list = filesClient.listObjectsStaringWith("temp","000","",1,"0016bbc3-a582-43a9-a77b-5a7b8009c6d6.jpg",".jpeg");
		for (FilesObject constants : list) {
			System.out.println(constants.toString());

		}
		System.out.println(list.size());
	}

}