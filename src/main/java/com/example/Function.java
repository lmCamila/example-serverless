package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Function {
    DAO dao = new DAO();
  @FunctionName("createcity")
    public City create(@HttpTrigger(
    name = "createcityrest",
    methods = {HttpMethod.POST},
    route = "city"
    )City city){
       return dao.create(city);
    }
    
    @FunctionName("readcity")
    public List<City> read(@HttpTrigger(
    name = "readcityrest",
    methods = {HttpMethod.GET},
    route = "city"
    )String x){
          return dao.read();
    }
    
    @FunctionName("updatecity")
    public City update(@HttpTrigger(
    name = "updatecityrest",
    methods = {HttpMethod.PUT},
    route = "city"
    )City city){
        return dao.update(city);
    }
    
    @FunctionName("deletecity")
    public int delete(@HttpTrigger(
    name = "deletecityrest",
    methods = {HttpMethod.DELETE},
    route = "city/{id}"
    )@BindingName("id") Long id){
        return dao.delete(id);
    }
    
}

@Data @AllArgsConstructor
class City{
    private Long id;
    private String name;
    private State state;
}

@Data @AllArgsConstructor
class State{
    private Long id;
    private String name;
}

class DAO{
    public City create(City city){
        return city;
    }
    
    public List<City> read(){
        return Stream.of(
            new City(1L,"Londrina",new State(1L,"Paraná")),
                new City(2L,"Jijoca de Jericoacoara",new State(2L,"Ceará")),
                new City(3L,"Salvador",new State(3L,"Bahia")),
                new City(4L,"Recife",new State(4L,"Pernambuco")),
                new City(5L,"Maragogi",new State(5L,"Alagoas"))                
        ).collect(Collectors.toList());
    }
    
    public City update (City city){
        city.setName(city.getName()+"-updated");
        return city;
    }
    public int delete(Long id){
        return 200;
    }
}