package com.zeromass.springbootwebappproject.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {

	private int httpCode;
	private String msg;
	private String Description;
	private T data;
}
