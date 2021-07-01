package com.raghu.user.service.service;

import com.raghu.user.service.VO.Department;
import com.raghu.user.service.VO.ResponseTemplateVO;
import com.raghu.user.service.entity.User;
import com.raghu.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService class");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserandDeparmentById(Long userId) {
        log.info("Inside getUserandDeparmentById method of UserService class");
        ResponseTemplateVO responseTemplateVO= new ResponseTemplateVO();
        User user= userRepository.findUserByUserId(userId);
        //Department department= restTemplate.getForObject("http://localhost:9001/departments/"+userId,Department.class);
        /* Below is using Eureaka service discovery, The restTemplate needs to be annotated with @LoadBalanced
        to inform spring to load balance if finds mutiple instance in same name. */
        Department department= restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+userId,Department.class);
        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }
}
