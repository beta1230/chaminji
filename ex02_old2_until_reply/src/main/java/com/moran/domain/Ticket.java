package com.moran.domain;

import lombok.Data;

@Data
public class Ticket {
	private int tno;
//	private Integer tno;
	private String owner;
	private String grade;
}
