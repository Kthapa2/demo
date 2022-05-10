package com.example.demo.property;

import com.example.demo.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/api/v1/properties")
    public Map<String, Object> getProperties(){
        return propertyService.getProperties();
    }
    @GetMapping("/api/v1/properties/featured")
    public Map<String, Object> getFeaturedProperties(){
        return propertyService.getFeaturedProperties();
    }

    @GetMapping("/api/v1/properties/{id}")
    @ResponseBody
    public Object getParticularUsers(@PathVariable String id){
        return propertyService.getParticularProperty(id);
    }

    @PostMapping("/api/v1/properties")
    @ResponseBody
    public Object createProperty(@RequestBody Property data){
        return propertyService.createParticularProperty(data);
    }

    @PutMapping("/api/v1/properties/{id}")
    @ResponseBody
    public Object updateProperty(@RequestBody Property data, @PathVariable String id){
        return propertyService.updateParticularProperty(data, id);
    }

    @DeleteMapping("/api/v1/properties/{id}")
    @ResponseBody
    public Object deleteParticularProperty(@PathVariable String id){
        return propertyService.deleteParticularProperty(id);
    }
    @GetMapping("/api/v1/properties/search")
    @ResponseBody
    public Object searchProperty(@RequestParam String query){
        return propertyService.searchProperty(query);
    }


}
