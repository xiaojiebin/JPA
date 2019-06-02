import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xiao.dao.CustomerDao;
import xiao.entity.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: 肖杰斌
 * @CreateDate: 2019/5/29 19:55
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2019/5/29 19:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 更具条件，查询单个对象
     */
    @Test
    public void testSpec() {
        /**
         * 定义查询条件
         *  1.实现Specification接口(提供泛型，查询的对象类型)
         *  2.实现toPredicate方法(构造查询条件)
         *  3.需要借助方法参数中的两个参数(
         *      root:需要查询的对象属性
         *      CriterIaBuilder：构造查询条件的，内部封装了很多的查询条件(模糊查询，精准查询）
         *   案例:根据客户名称查询，查询客户名为肖杰斌的客户
         *      查询条件:
         *          1.查询方式
         *              cb对象
         *          2.比较的属性名称
         *              root对象
         *
         */
        //匿名内部类
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                /**2.构造查询条件 select * from cst_customer where cust_name='肖杰斌';
                 * 参数1:需要比较的属性(path对象)
                 * 参数2:当前需要比较的取值
                 */
                Predicate xiao = cb.equal(custName, "肖杰斌");//进行精准的匹配（比较的属性：比较的属性的取值）
                return xiao;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }

    /**
     * 案例：根据客户名和所属行业进行查询。
     */
    @Test
    public void testNameAndIndustry() {
        /**
         * root:获取属性
         *  客户名
         *  所属行业
         * cb:构造查询
         *  1.构造客户的精准匹配查询
         *  2.构造所属行业的精准匹配查询
         *  3.将以上两个查询联系起来
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                //精准匹配
                Predicate p1 = cb.equal(custName, "肖杰斌");
                Predicate p2 = cb.equal(custIndustry, "IT行业");
                //将两个精准匹配联系起来
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }

    /**
     * 模糊匹配
     */

    @Test
    public void testLike() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custname = root.get("custName");
                //查询方式:模糊查询
                Predicate like = cb.like(custname.as(String.class), "肖%");
                return like;
            }
        };
        //        List<Customer> all = customerDao.findAll(spec);
        //        for (Customer cs : all
        //        ) {
        //            System.out.println(cs);
        //        }

        /**
         *  将查询的结构进行排序
         *  创建排序对象:Sort
         *  第一个参数：排序的顺序(倒序，正序)
         *  第二个参数：排序的属性名称。
         */
        Sort custId = new Sort(Sort.Direction.ASC, "custId");
        List<Customer> all = customerDao.findAll(spec, custId);
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }
    /**
     * 分页
     */
    @Test
    public void testTotal(){
        /**
         *
         */
        /**
         * 第一个参数：当前页
         * 第二个参数：每页显示条数
         */
        PageRequest pageRequest = new PageRequest(1,2);
        Page<Customer> all = customerDao.findAll(null, pageRequest);
        System.out.println(all.getContent());//获得数据集合列表
        System.out.println(all.getNumber());
        System.out.println(all.getTotalElements());//获取总条数
        System.out.println(all.getTotalPages());//得到总页数
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }
}
