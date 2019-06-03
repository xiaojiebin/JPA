import com.xiao.dao.RoleDao;
import com.xiao.dao.UserDao;
import com.xiao.domain.Role;
import com.xiao.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd() {
        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("java程序员");
        userDao.save(user);
        roleDao.save(role);
    }
}
