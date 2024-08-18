package com.ToDo.RestFull.Mapper;

import org.springframework.stereotype.Component;

@Component
public interface IMapper <I, O>{
    public O map(I source);
}
