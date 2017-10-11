package com.msad.school.room.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msad.school.room.entity.Teacher;
import com.msad.school.room.service.ITeacherService;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
	
	@Autowired
	private ITeacherService teacherService;
	
	private List<Teacher> list = new ArrayList<Teacher>();
//	private List<String> statusCache = new ArrayList<String>();
	private Integer idIndex = -1;
	
	private Integer orderIndex = -1;
	
	@Value("${show.title}")
	private String title;
	
	@RequestMapping("/list")
    public String list(Map<String,Object> map){
		initDate();
        map.put("list",list);
        map.put("title",title);
        return "portalList";
    }

	private void initDate() {
		if (list.isEmpty()) {
			list = teacherService.findAll();
			for (Teacher t : list) {
				if (t.getId() > idIndex) {
					idIndex = t.getId();
				}
				if (t.getOrder() > orderIndex) {
					orderIndex = t.getOrder();
				}
			}
		}
	}
	
	@RequestMapping("/admin/list")
    public String manageList(Map<String,Object> map){
		initDate();
        map.put("list",list);
        return "teacherList";
    }
	
	
	@RequestMapping("/add")
    public String add(Map<String,Object> map, Teacher teacher){	
		if (teacher.getId() == null) {//如果ID为空，则是新增，否则为修改
			teacher.setId(++idIndex);
			teacher.setOrder(++orderIndex);
			teacher.setStatus("上班");
			list.add(teacher);      
		}else{
			for (Teacher t : list) {
				if (t.getId()==teacher.getId()) {
					t.setStatus(teacher.getStatus());
				}
			}
		}
		
        map.put("list",list);
        teacherService.updateTeachersToFile(list);
        return "redirect:/teacher/admin/list";
    }
	
	@RequestMapping("/delete")
    public String delete(Map<String,Object> map, Integer id){
		for (Iterator<Teacher> iterator = list.iterator(); iterator.hasNext();) {
			Teacher t = (Teacher) iterator.next();
			if (t.getId()==id) {
				iterator.remove();
			}
		}
        map.put("list",list);
        teacherService.updateTeachersToFile(list);
        return "redirect:/teacher/admin/list";
    }
	
	@RequestMapping("/up")
    public String up(Map<String,Object> map, Integer id){
		Teacher before = null;
		Teacher current = null;
		for (Iterator<Teacher> iterator = list.iterator(); iterator.hasNext();) {
			Teacher t = (Teacher) iterator.next();
			if (t.getId()==id) {
				current = t;
				break;
			}
			before = t;
		}
		
		if (before !=null && current != null) {
			Integer beforeOrder = before.getOrder();
			Integer afterOrder = current.getOrder();
			before.setOrder(afterOrder);
			current.setOrder(beforeOrder);
			
			List<Teacher> orderList = new ArrayList<Teacher>();
			for (Teacher teacher : list) {
				if (teacher.getId() == before.getId()) {
					orderList.add(current);
				}else if (teacher.getId() == current.getId()){
					orderList.add(before);
				}else{
					orderList.add(teacher);
				}
			}
			list = orderList;
			
			teacherService.updateTeachersToFile(list);
		}
		
        map.put("list",list);
        teacherService.updateTeachersToFile(list);
        return "redirect:/teacher/admin/list";
    }
	
	@RequestMapping("/down")
    public String down(Map<String,Object> map, Integer id){
		Teacher before = null;
		Teacher current = null;
		for (int i = list.size()-1;i>=0;i--) {
			Teacher t = list.get(i);
			if (t.getId()==id) {
				current = t;
				break;
			}
			before = t;
		}
		
		if (before !=null && current != null) {
			Integer beforeOrder = before.getOrder();
			Integer afterOrder = current.getOrder();
			before.setOrder(afterOrder);
			current.setOrder(beforeOrder);
			
			List<Teacher> orderList = new ArrayList<Teacher>();
			for (Teacher teacher : list) {
				if (teacher.getId() == before.getId()) {
					orderList.add(current);
				}else if (teacher.getId() == current.getId()){
					orderList.add(before);
				}else{
					orderList.add(teacher);
				}
			}
			list = orderList;
			
			teacherService.updateTeachersToFile(list);
		}
		
        map.put("list",list);
        teacherService.updateTeachersToFile(list);
        return "redirect:/teacher/admin/list";
    }
	
	@ResponseBody
	@RequestMapping("/update")
    public Teacher update(Integer id){
		for (Iterator<Teacher> iterator = list.iterator(); iterator.hasNext();) {
			Teacher t = (Teacher) iterator.next();
			if (t.getId()==id) {
				return t;
			}
		}
		return null;
    }
}
