package com.hvisions.common.interfaces;

import com.hvisions.common.dto.*;
import com.hvisions.common.vo.*;

public interface ILog
{
    ResultVO<Integer> logRecord(final LogDto logDto);
}
