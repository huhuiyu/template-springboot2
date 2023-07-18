package top.huhuiyu.template.maven.springboot2.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.huhuiyu.template.maven.springboot2.base.BasePageResult;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.base.Page;
import top.huhuiyu.template.maven.springboot2.dao.TbEmployeeMapper;
import top.huhuiyu.template.maven.springboot2.entity.TbEmployee;
import top.huhuiyu.template.maven.springboot2.service.TbEmployeeService;

import java.util.List;
import java.util.Optional;

/**
 * 员工表服务实现
 *
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbEmployeeServiceImpl implements TbEmployeeService {
  private static final Logger logger = LoggerFactory.getLogger(TbEmployeeService.class);
  private final TbEmployeeMapper tbEmployeeMapper;

  public TbEmployeeServiceImpl(TbEmployeeMapper tbEmployeeMapper) {
    this.tbEmployeeMapper = tbEmployeeMapper;
  }

  @Override
  public BasePageResult<List<TbEmployee>> queryAll(TbEmployee employee, Page page) throws Exception {
    BasePageResult<List<TbEmployee>> result = new BasePageResult<>();
    TbEmployee tbEmployee = Optional.ofNullable(employee).orElse(new TbEmployee());
    // 查询参数处理
    logger.debug("参数：{}", tbEmployee);
    if (StringUtils.hasText(tbEmployee.getEmployeeName())) {
      tbEmployee.setEmployeeName(String.format("%%%s%%", tbEmployee.getEmployeeName()));
    }
    if (StringUtils.hasText(tbEmployee.getPhone())) {
      tbEmployee.setPhone(String.format("%%%s%%", tbEmployee.getPhone()));
    }
    // 分页参数处理
    page = Optional.ofNullable(page).orElse(new Page());
    PageHelper.startPage(page.getPageNumber(), page.getPageSize());
    List<TbEmployee> list = tbEmployeeMapper.queryAll(tbEmployee);
    // 获取查询结果的分页信息
    PageInfo<TbEmployee> pageInfo = new PageInfo<>(list);
    page.setPageInfo(pageInfo);
    logger.debug("查询结果：{},{}", list, page);
    result.setData(list);
    result.setSuccess(true);
    result.setCode(200);
    result.setPage(page);
    return result;
  }

  @Override
  public BaseResult<List<TbEmployee>> queryAllAssociation(TbEmployee employee) throws Exception {
    BaseResult<List<TbEmployee>> result = new BaseResult<>();
    TbEmployee tbEmployee = Optional.ofNullable(employee).orElse(new TbEmployee());
    logger.debug("参数：{}", tbEmployee);
    if (StringUtils.hasText(tbEmployee.getEmployeeName())) {
      tbEmployee.setEmployeeName(String.format("%%%s%%", tbEmployee.getEmployeeName()));
    }
    if (StringUtils.hasText(tbEmployee.getPhone())) {
      tbEmployee.setPhone(String.format("%%%s%%", tbEmployee.getPhone()));
    }
    List<TbEmployee> list = tbEmployeeMapper.queryAllAssociation(tbEmployee);
    logger.debug("查询结果：{}", list);
    result.setData(list);
    result.setSuccess(true);
    result.setCode(200);
    return result;
  }

}
