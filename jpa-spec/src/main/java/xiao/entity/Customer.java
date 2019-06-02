package xiao.entity;

/**
 * @Description: java类作用描述
 * @Author: 肖杰斌
 * @CreateDate: 2019/5/25 9:39
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2019/5/25 9:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 *      * 所有的注解都是使用JPA的规范提供的注解，
 *          封装了CRUD基本擦欧洲哦
 *      * 所以在导入注解包的时候，一定要导入javax.persistence下的
 *          封装了复杂操作(分页)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity //声明实体类
@Table(name="cst_customer") //建立实体类和表的映射关系
public class Customer {

    @Id//声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name = "cust_id") //指定和表中cust_id字段的映射关系
    private Long custId;

    @Column(name = "cust_name") //指定和表中cust_name字段的映射关系
    private String custName;

    @Column(name = "cust_source")//指定和表中cust_source字段的映射关系
    private String custSource;

    @Column(name = "cust_industry")//指定和表中cust_industry字段的映射关系
    private String custIndustry;

    @Column(name = "cust_level")//指定和表中cust_level字段的映射关系
    private String custLevel;

    @Column(name = "cust_address")//指定和表中cust_address字段的映射关系
    private String custAddress;

    @Column(name = "cust_phone")//指定和表中cust_phone字段的映射关系
    private String custPhone;
}