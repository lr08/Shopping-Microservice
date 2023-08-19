package com.example.demo.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {

	private String customerName;
	private String customerEmail;
	private Address customerBillingAddress;
	private List<ProdInvDto> prodInfo;
	private Double totalAmount;
}
