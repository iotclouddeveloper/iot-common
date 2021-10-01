package io.github.iotclouddeveloper.common.interfaces;

import io.github.iotclouddeveloper.common.dto.*;
import io.github.iotclouddeveloper.common.vo.*;
import io.github.iotclouddeveloper.common.dto.LogDto;
import io.github.iotclouddeveloper.common.vo.ResultVO;

public interface ILog
{
    ResultVO<Integer> logRecord(final LogDto logDto);
}
