package com.laoxu.demo.controller;

import com.laoxu.demo.dataobject.entity.WorkerDO;
import com.laoxu.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/test1")
    public String test1(){
        WorkerDO workerDO = new WorkerDO();
        workerDO.setName("张三");
        workerDO.setAge(35);
        workerDO.setSex("男");
        workerDO.setWorkId(1505);
        workerDO.setDepartment("开发部");
        workerService.insert(workerDO);
        return "ok";
    }

    @GetMapping("/test2")
    public String test2(){
        WorkerDO workerDO = workerService.queryById(1);
        System.out.println(workerDO);
        return "ok";
    }

    @GetMapping("/test3")
    public String test3(){
        WorkerDO workerDO = new WorkerDO();
        workerDO.setId(1);
        workerDO.setName("李四");
        workerDO.setAge(35);
        workerDO.setSex("男");
        workerDO.setWorkId(1350);
        workerDO.setDepartment("开发部");
        workerService.update(workerDO);
        return "ok";
    }

    @GetMapping("/test4")
    public String test4(){
        workerService.deleteById(1);
        return "ok";
    }

    @GetMapping("/test5")
    public List<WorkerDO> test5(){
        return  workerService.queryByName("张三");
    }

    @GetMapping("/test6")
    public List<WorkerDO> test6(){
        workerService.batchUpdate("张三");
        return  workerService.queryByName("王五");
    }


    @GetMapping("/test7")
    public List<WorkerDO> test7(){
        List<WorkerDO> workerDOList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            WorkerDO workerDO = new WorkerDO();
            workerDO.setName("张老" + i);
            workerDO.setAge(i);
            workerDO.setSex("男");
            workerDO.setDepartment("开发部");
            workerDO.setWorkId(35 + i);
            workerDOList.add(workerDO);
        }
        workerService.batchInsert(workerDOList);
        return  workerService.queryAll();
    }

    @GetMapping("/test8")
    public Set<String> test8(){
        return  workerService.workerNameSet();
    }

    @GetMapping("/test9")
    public Map<Integer,String> test9(){
        return  workerService.idNameMap();
    }

    @GetMapping("/test10")
    public String test10(){
        workerService.deleteByName("张三");
        return "ok";
    }

}
