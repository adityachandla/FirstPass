package com.firstpass.controllers;

import com.firstpass.dto.RequestDTO;
import com.firstpass.dto.RequestDTOWithUserSecret;
import com.firstpass.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/getMyPass")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @PostMapping
    public Map<String, String> generatePassword(@RequestBody RequestDTOWithUserSecret request) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("userSecret", request.getUserSecret());
        if(request.getUserSecret() == null) {
            responseMap.put("password", passwordService.getPassword(request.getAppName()));
        } else {
            responseMap.put("password", passwordService.getPassword(request.getUserSecret(), request.getAppName()));
        }
        return responseMap;
    }

}
