//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.dto.requests;

import com.interfaceict.ptrakcer.enums.DatabaseType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewDatabase
{
    @NotNull @NotEmpty
    private String name;

    @Min(1) @NotNull
    private Long projectSoftDetailID;

    @NotNull
    private DatabaseType type;

    private Long version;
}
