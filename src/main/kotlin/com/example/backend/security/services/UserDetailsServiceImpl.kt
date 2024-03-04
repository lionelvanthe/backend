package com.example.backend.security.services

import com.example.backend.repository.AccountRepository
import com.example.backend.security.services.UserDetailsImpl.Companion.build
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier

@Service
class UserDetailsServiceImpl(
        @Autowired
        var accountRepository: AccountRepository
) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = accountRepository.findByUsername(username)!!
                .orElseThrow(Supplier { UsernameNotFoundException("User Not Found with username: $username") })!!
        return build(user)
    }
}
