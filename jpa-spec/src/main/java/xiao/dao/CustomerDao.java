package xiao.dao;


/**
 * @Description: java类作用描述
 * @Author: 肖杰斌
 * @CreateDate: 2019/5/25 10:39
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2019/5/25 10:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xiao.entity.Customer;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
   }
