package com.gustas.lrt;

import java.util.List;

import com.gustas.lrt.service.ILiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveController {

    @Autowired
    private ILiveService liveService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/live")
    public List<CurrentlyLive> getLive() {
        return liveService.getCurrentlyLive();
    }
}
