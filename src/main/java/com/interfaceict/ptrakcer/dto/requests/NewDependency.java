//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.dto.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewDependency
{
    @NotNull @NotEmpty
    private String name;

    @Min(1)
    private Long projectID;

    private String description;

    private Boolean status;
}
