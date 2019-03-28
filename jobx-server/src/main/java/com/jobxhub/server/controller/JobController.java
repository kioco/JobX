/**
 * Copyright (c) 2015 The JobX Project
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jobxhub.server.controller;

import org.apache.dubbo.config.annotation.Reference;
import com.jobxhub.server.util.SessionUtils;
import com.jobxhub.service.api.JobService;
import com.jobxhub.service.model.Job;
import com.jobxhub.service.model.User;
import com.jobxhub.service.vo.PageBean;
import com.jobxhub.service.vo.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {

    @Reference
    private JobService jobService;

    @PostMapping("/view")
    public RestResult view(PageBean pageBean, Job job) {
        PageBean pager = jobService.getPageBean(pageBean, job);
        return RestResult.rest(pager);
    }

    @PostMapping("/getJob")
    public RestResult getJob(Integer jobType) {
        List<Job> jobList = jobService.getJob(jobType);
        return RestResult.ok(jobList);
    }

    /**
     * 添加一个简单作业|工作流节点作业
     * @param job
     * @return
     */
    @PostMapping("/addJob")
    public RestResult addJob(Job job) {
        jobService.addJob(job);
        return RestResult.ok(job);
    }

    /**
     * 添加一个工作流节点作业
     * @param job
     * @return
     */
    @PostMapping("/addNode")
    public RestResult addNode(Job job) {
        jobService.addNode(job);
        return RestResult.ok(job);
    }

    /**
     * 添加一个工作流作业
     * @param job
     * @return
     */
    @PostMapping("/addFlow")
    public RestResult addFlow(Job job) {
        jobService.addFlow(job);
        return RestResult.ok(job);
    }


}
