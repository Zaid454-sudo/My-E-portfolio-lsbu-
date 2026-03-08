package com.example.adp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeMap;

@RestController
class HelloController {

    private final TreeMap<Long,MyPOJO> map = new TreeMap<>();

    @GetMapping("/")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/helloagain")
    public String[] helloAgain() {
        return new String[] { "hello world!", "again", "hang on what's this?", "press", "roman", "rover" };
    }

    @GetMapping("/pojo")
    public Collection<MyPOJO> getAll() {
        return map.values();
    }

    @GetMapping("/pojo/{id}")
    public MyPOJO getById(@PathVariable("id") Long id) {
        MyPOJO item = map.get(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such id " + id);
        } else {
            return item;
        }
    }

    @GetMapping("/pojo/{first}/{last}/{id}")
    public MyPOJO myPojo( @PathVariable("first") String firstName,
                          @PathVariable("last") String lastName,
                          @PathVariable("id") int idNumber) {
        return new MyPOJO(firstName, lastName, idNumber);
    }

    @PutMapping("/pojo")
    public MyPOJO putPojo(@RequestBody MyPOJO body) {
        this.map.put(body.getIdNumber(), body);
        return body;
    }
    @DeleteMapping("/pojo/{id}")
    public String[] deletePojo(@PathVariable long id) {
        if (this.map.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such id " + id);
        } else {
            return new String[] {"item removed"};
        }
    }
    @PostMapping("/pojo")
    public MyPOJO postPojo(@RequestBody MyPOJO body) {
        long nextId = 1;
        if (!this.map.isEmpty()) {
            nextId = ((SortedSet<Long>) this.map.keySet()).last() + 1;
        }
        MyPOJO newItem = new MyPOJO(body.getFirstName(), body.getLastName(), nextId);
        this.map.put(nextId, newItem);
        return newItem;
    }

}
