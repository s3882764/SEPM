package com.spem.application.serviceimpl;

import com.spem.application.pojo.ProjectOption;
import com.spem.application.pojo.Reader;
import com.spem.application.pojo.StudentNumber;
import com.spem.application.pojo.Technology;
import com.spem.application.service.TeamCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamCreationServiceImpl implements TeamCreationService {
    private final int MAX_TEAM_SIZE = 6;
    private final int MIN_TEAM_SIZE = 4;
    private final int MAX_NO_OF_TEAMS = 5;
    int count = 1;
    @Autowired
    ReaderServiceImpl readerServiceImpl;

    @Override
    public List<Reader> createTeam() {
    	count = 1;
    	int noOfGroups = 0;
    	Map<Integer, Integer> groupNumber= new HashMap<Integer, Integer>();
    	Map<Integer, Integer> groupNumberAfterTechnology= new HashMap<Integer, Integer>();
    	Map<Integer, Integer> NumberOfMembersCanBeAdded= new HashMap<Integer, Integer>();
        List<Reader> studentsList = getStudentList();
        Map<String, List<Reader>> map = studentsList.stream().collect(Collectors.groupingBy(Reader::getWorkshopClass));
        map.forEach((key, studentsInWorkshop) -> {
            System.out.println("Why do you come in here unnecessarily?" + key);
            if (studentsInWorkshop.size() > MIN_TEAM_SIZE && studentsInWorkshop.size() < (MAX_TEAM_SIZE * MAX_NO_OF_TEAMS)) {
                Set<List<String>> teamsWithPref = groupSameTeamMembers(studentsInWorkshop);
                
                 
                for(List<String> groupMembers : teamsWithPref){
                    int finalCount = count;
                    System.out.println(finalCount);
                    groupMembers.forEach(member -> {
                        studentsList.stream().filter(student -> student.getStudentId().equals(member))
                                .findFirst()
                                .ifPresent(student -> student.setGroupId(finalCount));
                    });
                    count++;
                }
            } else {
                System.out.println("Cannot meet the constraint of min and max team size for workshop : " + key);
            }
        });
        
        
        for(Reader s:studentsList) {
        	if(s.getGroupId() != null) {
        		Integer number = s.getGroupId();
        		if(groupNumber.containsKey(number)) {
        			Integer value = groupNumber.get(number);
        			value = value + 1;
        			groupNumber.put(number, value);
        		}else {
        			groupNumber.put(number, 1);
        		}
        	}
        }
        for(Reader r:studentsList ) {
   		 System.out.println("studentsList------"+r.toString());
   	 }
        
        System.out.println("groupNumber---"+groupNumber);
        List<Reader> studentsListWithOutGroup = getStudentsListWithOutGroup(studentsList,groupNumber);
        List<String> projOpt = new ArrayList<>();
         for(Reader r: studentsListWithOutGroup) {
        	for(ProjectOption t:r.getProjectOption()) {
        		if(!projOpt.contains(t.getProjectOption())) {
        			projOpt.add(t.getProjectOption());
        		}
        	}
        }
         
         Map<String, List<Reader>> map1 = studentsListWithOutGroup.stream().collect(Collectors.groupingBy(Reader::getWorkshopClass));

         //start
			int memberCount = 0;
			for (Map.Entry<String, List<Reader>> entry : map1.entrySet()) {
				for(String p:projOpt) {
				for (Reader r : entry.getValue()) {
					List<String> proj =  new ArrayList<>();
					if(r.getProjectOption() != null) {
						for(ProjectOption pr : r.getProjectOption()) {
							proj.add(pr.getProjectOption());
						}
					}
					if(proj.contains(p)) {
					if (memberCount < MAX_TEAM_SIZE) {
						r.setGroupId(count);
						memberCount++;
					}else {
					 count ++;
					 memberCount = 0 ;
					 r.setGroupId(count);
					}
				}
			}
			}
			}
			
			
        
         //end
			for (Reader r : studentsListWithOutGroup) {
				 System.out.println("studentsList-8787878787-----"+r.toString());
		   	 
			}
			
			
			
			for(Reader s:studentsListWithOutGroup) {
	        	if(s.getGroupId() != null) {
	        		Integer number = s.getGroupId();
	        		if(groupNumberAfterTechnology.containsKey(number)) {
	        			Integer value = groupNumberAfterTechnology.get(number);
	        			value = value + 1;
	        			groupNumberAfterTechnology.put(number, value);
	        		}else {
	        			groupNumberAfterTechnology.put(number, 1);
	        		}
	        	}
	        }
			
			
			
		List<Reader> studentsListWithGroupAfterTechnology = getStudentsListWithGroupAfterTechnology(studentsList,studentsListWithOutGroup,groupNumberAfterTechnology);
		
			
		for(Reader r:studentsList ) {
	        System.out.println("jbvjhbhjvbhjvjhvjvjvjh)-------"+r.toString());
				}
			
         List<String> tech = new ArrayList<>();
         for(Reader r: studentsListWithOutGroup) {
        	 if(r.getTechnologies() !=null) {
        	for(Technology t:r.getTechnologies()) {
        		if(!tech.contains(t.getTechnology())) {
        			tech.add(t.getTechnology());
        		}
        	}
        }
         }
			if (studentsListWithGroupAfterTechnology.size() >= MIN_TEAM_SIZE) {
			         Map<String, List<Reader>> map3 = studentsListWithGroupAfterTechnology.stream().collect(Collectors.groupingBy(Reader::getWorkshopClass));
			       //start
						int memberCountAfterTech = 0;
						for (Map.Entry<String, List<Reader>> entry : map3.entrySet()) {
							for(String p:tech) {
							for (Reader r1 : entry.getValue()) {
								List<String> tech1 =  new ArrayList<>();
								if(r1.getProjectOption() != null) {
									for(Technology pr : r1.getTechnologies()) {
										tech1.add(pr.getTechnology());
									}
								}
								if(tech1.contains(p)) {
								if (memberCountAfterTech < MAX_TEAM_SIZE) {
									r1.setGroupId(count);
									memberCountAfterTech++;
								}else {
								 count ++;
								 memberCountAfterTech = 0 ;
								 r1.setGroupId(count);
								}
							}
						}
						}
						
						
			        
			         //end

				}
			}else {
				for (Reader r : studentsListWithGroupAfterTechnology) {
						for(Reader st:studentsList) {
							if(st.getStudentId().equals(r.getStudentId())) {
								st.setGroupId(null);
							}
						}
					}
				}
			
			
         System.out.println("projOpt---"+projOpt);
         System.out.println("tech---"+tech);
	     
         for(Reader r:studentsList ) {
 	        System.out.println("after programming)-------"+r.toString());
 				}
         
        
        
        
		/*
		 * for (Map.Entry<Integer,Integer> entry : groupNumber.entrySet()) {
		 * if(entry.getValue() <= MAX_TEAM_SIZE) {
		 * NumberOfMembersCanBeAdded.put(entry.getKey(),MAX_TEAM_SIZE -
		 * entry.getValue()); } }
		 */
        //System.out.println("NumberOfMembersCanBeadded---"+NumberOfMembersCanBeAdded);
        List<Reader> studentWithNoGroupsAdded = getStudentsAddingMembersToListWithProjectOption(studentsList, studentsListWithGroupAfterTechnology, NumberOfMembersCanBeAdded);
        
        List<Reader> read = removeOwnStudentId(studentsList);
        
       
        return read;
    }

    private Set<List<String>> groupSameTeamMembers(List<Reader> studentsInWorkshop) {
        return
                studentsInWorkshop.stream()
                        .map(s -> {
                            s.getPreferStudents().add(s.getStudentId());
                            Collections.sort(s.getPreferStudents());
                            return s.getPreferStudents();
                        }).collect(Collectors.toSet());
    }

    private List<Reader> removeOwnStudentId(List<Reader> studentsList){
        return
                studentsList.stream()
                        .map(s -> {
                            if(s.getPreferStudents() != null) {
                                s.getPreferStudents().remove(s.getStudentId());
                            }
                            return s;
                        }).collect(Collectors.toList());
    }

    private List<Reader> getStudentList(){
        return readerServiceImpl.getExistingStudents();
    }
    
    private List<Reader> getStudentsListWithOutGroup(List<Reader> studentsList, Map<Integer, Integer> groupNumber){
    	List<Reader> studentsListWithoutGroup = new ArrayList<>();
        List<Integer> groups = new ArrayList<>(); 
    	
    	for(Reader r:studentsList ) {
    	   if(r.getGroupId() == null) {
    		   studentsListWithoutGroup.add(r);
    	   }
       }
    	 for (Map.Entry<Integer,Integer> entry : groupNumber.entrySet())  {
          if(entry.getValue() < MIN_TEAM_SIZE) {
        	  groups.add(entry.getKey());
          }
     } 
    	 for(Integer grp:groups) {
    		 for(Reader r:studentsList ) {
    		 if(grp.equals(r.getGroupId())) {
    			 if(!studentsListWithoutGroup.contains(r)) {
    				 studentsListWithoutGroup.add(r);
    			 }
    		 }
    	 }
    		 }
    	
    	 for(Reader r:studentsListWithoutGroup ) {
    		 System.out.println("r-r-o-"+r.toString());
    	 }
    	return studentsListWithoutGroup;
    }
    
    
    
    //after tech
    private List<Reader> getStudentsListWithGroupAfterTechnology(List<Reader> studentsList, List<Reader> studentsListWithGroupAfterTechnology, Map<Integer, Integer> groupNumberAfterTechnology){
    	List<Reader> studentsListWithoutGroup = new ArrayList<>();
        List<Integer> groups = new ArrayList<>(); 
        List<Integer> groupsWithProperGroup = new ArrayList<>();
        
        //removing member after group assigned
        for (Map.Entry<Integer,Integer> entry : groupNumberAfterTechnology.entrySet())  {
            if(entry.getValue() < MIN_TEAM_SIZE) {
          	  groups.add(entry.getKey());
            }else {
            	groupsWithProperGroup.add(entry.getKey());
            }
        }  
            System.out.println("groups-------tech------"+groups);
			for (Integer number : groupsWithProperGroup) {
				for (Reader r : studentsListWithGroupAfterTechnology) {
					if(number.equals(r.getGroupId())) {
						for(Reader st:studentsList) {
							if(st.getStudentId().equals(r.getStudentId())) {
								st.setGroupId(number);
							}
						}
					}
				}
			}
        
        
        
			
        
        
        
        
        ////
			
			for(Integer grp:groups) {
	    		 for(Reader r:studentsList ) {
	    		 if(grp.equals(r.getGroupId())) {
	    			 if(!studentsListWithoutGroup.contains(r)) {
	    				 studentsListWithoutGroup.add(r);
	    			 }
	    		 }
	    	 }
	    		 }
			////
        
    	for(Reader r:studentsList ) {
    	   if(r.getGroupId() == null) {
    		   studentsListWithoutGroup.add(r);
    	   }
       }
    	   
		return studentsListWithoutGroup;
     } 
		
		 
    //emd tech
    
    
    private List<Reader> getStudentsListWithGroupAfterProgramming(List<Reader> studentsList, List<Reader> studentsListWithGroupAfterTechnology, Map<Integer, Integer> groupNumberAfterTechnology){
    	List<Reader> studentsListWithoutGroup = new ArrayList<>();
        List<Integer> groups = new ArrayList<>(); 
        List<Integer> groupsWithProperGroup = new ArrayList<>();
        
        //removing member after group assigned
        for (Map.Entry<Integer,Integer> entry : groupNumberAfterTechnology.entrySet())  {
            if(entry.getValue() < MIN_TEAM_SIZE) {
          	  groups.add(entry.getKey());
            }else {
            	groupsWithProperGroup.add(entry.getKey());
            }
        }  
            System.out.println("groups-------tech------"+groups);
		System.out.println("Trying something");
			for (Integer number : groupsWithProperGroup) {
				for (Reader r : studentsListWithGroupAfterTechnology) {
					if(number.equals(r.getGroupId())) {
						for(Reader st:studentsList) {
							if(st.getStudentId().equals(r.getStudentId())) {
								st.setGroupId(number);
							}
						}
					}
				}
			}
        
        
        
			
        
        
        
        
        ////
			
			for(Integer grp:groups) {
	    		 for(Reader r:studentsList ) {
	    		 if(grp.equals(r.getGroupId())) {
	    			 if(!studentsListWithoutGroup.contains(r)) {
	    				 studentsListWithoutGroup.add(r);
	    			 }
	    		 }
	    	 }
	    		 }
			////
        
    	for(Reader r:studentsList ) {
    	   if(r.getGroupId() == null) {
    		   studentsListWithoutGroup.add(r);
    	   }
       }
    	   
		return studentsListWithoutGroup;
     } 
    //After Technology
    
    
    //End Technology
    private List<Reader> getStudentsAddingMembersToListWithProjectOption(List<Reader> studentsList,List<Reader> studentsListWithNoGroup,Map<Integer, Integer> NumberOfMembersCanBeAdded){
    	List<Reader> studentsListWithoutGroup = new ArrayList<>();
        
    	
    	for(Reader r:studentsList ) {
    	   if(r.getGroupId() == null) {
    		   studentsListWithoutGroup.add(r);
    	   }
       }
    	
    	
    	
    	return studentsListWithoutGroup;
    }
}
