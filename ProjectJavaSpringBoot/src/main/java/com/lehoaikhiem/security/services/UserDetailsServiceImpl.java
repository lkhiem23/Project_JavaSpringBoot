package com.lehoaikhiem.security.services;

import com.lehoaikhiem.entity.User;
import com.lehoaikhiem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Để đảm bảo việc tải dữ liệu là một transaction

@Service
@RequiredArgsConstructor //tạo constructor cho các final field (UserService)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository; // Inject UsersRepository của bạn
    // <--- BẰNG DÒNG NÀY (Đã xóa private final UserService userService;)

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm người dùng trong cơ sở dữ liệu bằng username, sử dụng usersRepository
        User user = usersRepository.findByUsername(username) // <-- SỬ DỤNG usersRepository.findByUsername()
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        // Xây dựng UserDetailsImpl từ User entity tìm được
        return UserDetailsImpl.build(user);
    }
}
