import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xiao.dao.CustomerDao;
import xiao.dao.LinkManDao;
import xiao.entity.Customer;
import xiao.entity.LinkMan;

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
    @Transactional
    @Rollback(false)
    public void test() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testTwo() {
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1 ");

        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testTree() {
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1 ");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }


    /**
     * 级联添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度1");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1");

        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
    }
    /**
     * 级联删除
     * 删除客户同时，删除1号客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove(){
        Customer one = customerDao.findOne(1l);
        customerDao.delete(one);
    }

}
