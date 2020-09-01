package com.clickcharm.transactoinapi.controllers;

import com.clickcharm.transactoinapi.model.Category;
import com.clickcharm.transactoinapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public  ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        List<Category> categories = categoryService.fetchAllCategories(userId);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String,Object> categoryRequestBody){
        int userId = (int) request.getAttribute("userId");
        String title = (String) categoryRequestBody.get("title");
        String description = (String) categoryRequestBody.get("description");
        System.out.println("userId: " + userId + " title : " + title + " description : "+description);
        Category category = categoryService.addCategory(new Category(userId,title,description));
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(HttpServletRequest request,
                                                @PathVariable("categoryId") Integer categoryId){
        int userId = (int) request.getAttribute("userId");
        Category category = categoryService.fetchCategoryById(userId,categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category categoryRequestBody){
        int userId = (int) request.getAttribute("userId");
       // String title = (String) categoryRequestBody.get("title");
       // String description = (String) categoryRequestBody.get("description");
        // new Category(title,description)
        categoryService.updateCategory(userId,categoryId,categoryRequestBody);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
