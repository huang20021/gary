package com.msad.school.room.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msad.school.room.entity.Teacher;
import com.msad.school.room.service.ITeacherService;
import com.msad.school.room.util.MyFileUtil;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Override
	public List<Teacher> findAll() {
		return MyFileUtil.readTeacherListFromFile();
	}

	@Override
	public Teacher findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher deleteById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher update(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTeachersToFile(List<Teacher> teachers) {
		// TODO Auto-generated method stub
		MyFileUtil.updateTeachersToFile(teachers);
	}

}
