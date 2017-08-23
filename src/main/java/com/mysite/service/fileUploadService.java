package com.mysite.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileUploadService {
	public String upload(MultipartFile file) {
		String orgName = file.getOriginalFilename(); // 원래 이름을 따로 저장
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); // 파일 확장자 따로
																											// 저장
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; // 랜덤으로 해도 파일이 많으면 겹칠수 있으니
																								// 앞에 시간과 같이 저장해서 완전한
																								// 랜덤으로 저장
																								// 파일 확장자는 따로 뺀것을 붙여서 저장
		long fileSize = file.getSize(); // 파일 사이즈 저장, byte라서 long으로 받음

		String url = "D:\\javaStudy\\file\\";

		System.out.println(orgName);
		System.out.println(exName);
		System.out.println(saveName);
		System.out.println(fileSize);
		System.out.println(url);
		
		String filePath = url + saveName;
		try {

			byte[] fileData = file.getBytes(); // size가 아니고 실제데이터 그림의 배열같은거
			System.out.println("fileData: " + fileData);
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			if (bout != null) {
				bout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
}
