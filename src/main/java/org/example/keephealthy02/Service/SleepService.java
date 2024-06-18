package org.example.keephealthy02.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.keephealthy02.Entity.Sleep;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 睡眠质量表 服务类
 * </p>
 *
 * @author 陈昕榆
 * @since 2024-06-18
 */
public interface SleepService extends IService<Sleep> {

    String CalculateSleepTime(String userId);

    Sleep MyInfo(String userId);
}
