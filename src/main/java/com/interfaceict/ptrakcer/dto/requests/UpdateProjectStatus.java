//
// Created by htrap19 on 1/17/22
//

package com.interfaceict.ptrakcer.dto.requests;

import com.interfaceict.ptrakcer.enums.ProjectStatus;
import lombok.Data;

@Data
public class UpdateProjectStatus
{
    private ProjectStatus status;
}
