package com.demo.api.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePojo {
	private Long id;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "IST")
	private LocalDateTime created;
}
