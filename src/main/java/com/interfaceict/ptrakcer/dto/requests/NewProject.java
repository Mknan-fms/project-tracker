package com.interfaceict.ptrakcer.dto.requests;

import lombok.Data;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Simple POJO class to hold new project required parameters fields
 * 
 * @author Mustafa Ibrahim
 *
 */
@Data
public class NewProject
{
	@NotNull @NotEmpty
    private String name;
	
	private String description;
	
	@NotNull
    private Long duration;
	
	private LocalDate startDate;
	private LocalDate completionDate;
	private LocalDate deliveryDate;

	public NewProject()
	{
	}
}
