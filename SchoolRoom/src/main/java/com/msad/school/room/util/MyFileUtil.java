package com.msad.school.room.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.msad.school.room.entity.Teacher;


public class MyFileUtil {
	private static Logger logger = Logger.getLogger(MyFileUtil.class.getName());
	
	public static List<Teacher> readTeacherListFromFile(){
		List<Teacher> list = new ArrayList<Teacher>();
		InputStream in = null;
		BufferedReader reader = null;
		try {
			Resource resource = new ClassPathResource("teacheNameList.txt");
			File file = resource.getFile();
			in = new FileInputStream(file);
//			in = ClassLoader.getSystemResourceAsStream("teacheNameList.txt");
//			in = ClassLoader.getSystemClassLoader().getResource("teacheNameList.txt").openStream();
			
			reader = new BufferedReader(new InputStreamReader(in));
			String tempString = null;
//			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out.println("line " + line++ + ": " + tempString);
				String[] teacherArr = tempString.split("\\|");
				Teacher t = new Teacher();
				t.setId(Integer.valueOf(teacherArr[0]));
				t.setName(teacherArr[1]);
				t.setStatus(teacherArr[2]);
				t.setOrder(Integer.valueOf(teacherArr[3]));
				list.add(t);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "read teacher file error, 读取老师列表文件出错！", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		OutputStream in = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {
			
			URL url = ClassLoader.getSystemResource("teacheNameList.txt");
			String urlPath = url.getFile();
			System.out.println(url.getFile());
			String fileName = urlPath.substring(urlPath.lastIndexOf("/")+1);
			System.out.println(fileName);
			String filePath = urlPath.substring(1,urlPath.lastIndexOf("/")+1);
			System.out.println(filePath);
			
			String backupFileName = "teacheNameList_backup.txt";
			//备份原文件,文件名为teacheNameList_backup.txt
			File file = new File(filePath + fileName);
			File backupFile = new File(filePath + backupFileName);
			if (backupFile.exists()) {
				backupFile.delete();
			}
			file.renameTo(backupFile);
			
			//重新写文件
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			for (int i = 0; i < args.length; i++) {
				
			}
			writer.newLine();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "读取老师列表文件出错！", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static void updateTeachersToFile(List<Teacher> teachers){
		BufferedWriter writer = null;
		OutputStream os = null;
		try {
			Resource resource = new ClassPathResource("teacheNameList.txt");
			File file = resource.getFile();
			String fullPath = file.getPath();
			String fileName = file.getName();
			//String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
			System.out.println(fileName);
			String filePath = fullPath.substring(0,fullPath.lastIndexOf("\\")+1);
			System.out.println(filePath);
			
//			URL url = ClassLoader.getSystemResource("teacheNameList.txt");
//			String urlPath = url.getFile();
//			System.out.println(url.getFile());
//			String fileName = urlPath.substring(urlPath.lastIndexOf("/")+1);
//			System.out.println(fileName);
//			String filePath = urlPath.substring(1,urlPath.lastIndexOf("/")+1);
//			System.out.println(filePath);
			
			String backupFileName = "teacheNameList_backup.txt";
			//备份原文件,文件名为teacheNameList_backup.txt
			//File file = new File(filePath + fileName);
			File backupFile = new File(filePath + backupFileName);
			if (backupFile.exists()) {
				backupFile.delete();
			}
			file.renameTo(backupFile);
			
			//重新写文件
			os = new FileOutputStream(file);
			writer = new BufferedWriter(new OutputStreamWriter(os));
			for (Teacher teacher : teachers) {
				writer.write(teacher.getId() + "|" + teacher.getName() + "|" + teacher.getStatus() + "|" + teacher.getOrder());
				writer.newLine();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "更新老师列表文件出错！", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
