//
// Created by htrap19 on 1/20/22
//

package com.interfaceict.ptrakcer.dto.requests;

import com.interfaceict.ptrakcer.enums.ApplicationType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewProjectSoftwareDetail
{
    @NotNull @NotEmpty
    private String name;

    private String description;

    @NotNull
    private ApplicationType type;

    @NotNull @NotEmpty
    private String lang;

    private String frameworkUsed;
}
