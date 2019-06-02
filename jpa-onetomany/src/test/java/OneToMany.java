import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xiao.dao.CustomerDao;
import xiao.dao.LinkManDao;
import xiao.entity.Customer;
import xiao.entity.LinkMan;

import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: 肖杰斌
 * @CreateDate: 2019/6/2 12:22
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2019/6/2 12:22
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class OneToMany {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    public void test(){
        Customer customer=new Customer();
        customer.setCustName("百度");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");
        Set<LinkMan> setLinkMan = customer.getLinkMans();
        setLinkMan.add(linkMan);
        linkManDao.save(linkMan);
        customerDao.save(customer);


    }

}
