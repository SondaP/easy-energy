package paxxa.com.ees.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.user.UserRepository;

import java.util.List;
@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(final int userId) {
        return userRepository.findOne(userId);
    }

    public void save(User user){
        userRepository.save(user);
    }


}
