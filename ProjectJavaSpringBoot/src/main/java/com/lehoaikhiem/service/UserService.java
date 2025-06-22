package com.lehoaikhiem.service;

import com.lehoaikhiem.entity.Customer;
import com.lehoaikhiem.entity.ERole;
import com.lehoaikhiem.entity.Roles;
import com.lehoaikhiem.entity.User;
import com.lehoaikhiem.repository.CustomerRepository;
import com.lehoaikhiem.repository.RolesRepository;
import com.lehoaikhiem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    public User registerUser(String username, String rawPassword, String email) {
        Roles userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setRoles(Collections.singleton(userRole));

        User savedUser = usersRepository.save(user);

        // Tạo Customer profile cho người dùng mới
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setName(username);
        customer.setEmail(email);
        // Thiết lập các giá trị mặc định khác nếu cần.  Vì password, isDelete và isActive đã có trong Customer entity, chúng ta không cần set lại.
        customer.setPhone("NONE");
        customer.setAddress("NONE");
        customer.setIsActive(true);
        customer.setIsDelete(false);

        customerRepository.save(customer);

        return savedUser;
    }

    // Nếu bạn muốn thêm user với role khác
    public User registerUserWithRole(String username, String rawPassword, String email, String roleName) {
        Roles role = roleRepository.findByName(ERole.valueOf(roleName.toUpperCase()))
                .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setRoles(Collections.singleton(role));

        User savedUser = usersRepository.save(user);

        // Tạo Customer profile cho user có role khác (nếu cần)
        Customer customer = new Customer();
        customer.setUsername(username); // Sử dụng username làm username ban đầu
        customer.setName(username); // Sử dụng username làm tên ban đầu
        customer.setEmail(email);
        // Thiết lập các giá trị mặc định khác nếu cần.  Vì password, isDelete và isActive đã có trong Customer entity, chúng ta không cần set lại.
        customer.setPhone("0000000000"); // Ví dụ: giá trị mặc định
        customer.setAddress("Địa chỉ mặc định"); // Ví dụ: giá trị mặc định
        customer.setIsActive(true); // Ví dụ: giá trị mặc định
        customer.setIsDelete(false); // Ví dụ: giá trị mặc định
        customerRepository.save(customer);

        return savedUser;
    }

    public Boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}