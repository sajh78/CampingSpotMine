package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationVo {
	
	private int r_no;
	private Date r_checkin;
	private Date r_checkout;
	private Date r_date;
	private int r_price;
	private String r_payment;
	private int r_revstatus;
	private int r_cancel;
	private int cr_no;
	private String mc_id;

}
