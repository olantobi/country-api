package com.liferon.countryapi;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        User buhari = new User("Buhari", 76);
//        User osinbajo = new User("Osinbajo", 61);
//
//        assertNull(buhari.getId());
//        assertNull(osinbajo.getId());
//
//        this.userRepository.save(buhari);
//        this.userRepository.save(osinbajo);
//
//        assertNotNull(buhari.getId());
//        assertNotNull(osinbajo.getId());
//    }
//
//    @Test
//    public void testFetchData() {
//        User buhari = userRepository.findByName("Buhari");
//        assertNotNull(buhari);
//        assertEquals(76, buhari.getAge());
//
//        Iterable<User> users = userRepository.findAll();
//        int count = 0;
//
//        for (User u: users) {
//            count++;
//        }
//
//        assertEquals(2, count);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        long totalUsers = userRepository.count();
//
//        assertEquals(2, totalUsers);
//
//        userRepository.deleteAll();
//
//        totalUsers = userRepository.count();
//
//        assertEquals(0, totalUsers);
//    }
}
