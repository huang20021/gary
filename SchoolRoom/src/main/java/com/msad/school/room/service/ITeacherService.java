package com.msad.school.room.service;

import java.util.List;

import com.msad.school.room.entity.Teacher;

public interface ITeacherService {
	public List<Teacher> findAll();
	public Teacher findById();
	public Teacher deleteById();
	public Teacher update(Teacher teacher);
	
	public void updateTeachersToFile(List<Teacher> teachers);
}
