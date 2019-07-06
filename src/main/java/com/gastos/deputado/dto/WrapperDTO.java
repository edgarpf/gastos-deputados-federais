package com.gastos.deputado.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrapperDTO {
	@JsonProperty("dados")
	List<Deputado> data;
}
