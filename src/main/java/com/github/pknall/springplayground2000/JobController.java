package com.github.pknall.springplayground2000;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/jobs")
public class JobController {

    private List<Job> jobList;

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Job());
        return "jobDesign";
    }

    //https://codingexplained.com/coding/java/spring-framework/neither-bindingresult-nor-plain-target-object-available-as-request-attribute
    /*
    The above assumes a Spring form with a modelAttribute or commandName attribute with the value "design". This code
    does, however, raise the following exception: Neither BindingResult nor plain target object for bean name ‘design’
    available as request attribute.

    This used to work in previous versions of Spring, but it no longer seems to work (currently version 4.1.4). To
    solve this, you have to add a @ModelAttribute annotation before your bean argument in the controller action with
    the POST request method. Below is a working example.
     */
    @PostMapping
    public String processJobForm(@Valid @ModelAttribute("design") Job job, BindingResult errors) {
        if (errors.hasErrors()) {
            return "jobDesign";
        }
        if (jobList == null) jobList = new ArrayList<>();
        jobList.add(job);
        return "redirect:jobs/jobList";
    }

    @GetMapping("/jobList")
    public String jobList(Model model) {
        model.addAttribute("jobList", jobList);
        return "jobList";
    }

}
