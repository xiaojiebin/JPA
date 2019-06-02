package xiao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xiao.entity.LinkMan;

/**
 * @Description: java类作用描述
 * @Author: 肖杰斌
 * @CreateDate: 2019/6/2 12:23
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2019/6/2 12:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface LinkManDao  extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
