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
@RequestMapping(value = "/api/admin/")
public class AdminController {
    @Autowired
    private StatService statService;
    @GetMapping("/stat/get-user-by-month")
    public ResponseEntity<ModelResponse> countNumberOfUserByMonth(@RequestParam(value = "year", required = false, defaultValue = "2023") int year) {
        List<UserByMonth> results = statService.countUserByMonth(year);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }
}
