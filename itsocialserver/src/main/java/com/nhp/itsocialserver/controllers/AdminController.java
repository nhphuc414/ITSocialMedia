package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.dtos.responses.stat.UserByMonth;
import com.nhp.itsocialserver.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin/", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class AdminController {
    @Autowired
    private StatService statService;
    @GetMapping("/stat/get-user-by-month")
    public ResponseEntity<ModelResponse> countNumberOfUserByMonth(@RequestParam(value = "year", required = false, defaultValue = "2023") int year) {
        System.out.println("Hello");
        List<UserByMonth> results = statService.countUserByMonth(year);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }
}