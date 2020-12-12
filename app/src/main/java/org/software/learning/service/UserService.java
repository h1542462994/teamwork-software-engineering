package org.software.learning.service;

import org.software.learning.common.UserTool;
import org.software.learning.exception.HttpStatusException;
import org.software.learning.forms.UserAutoLoginForm;
import org.software.learning.forms.UserForm;
import org.software.learning.forms.UserLoginForm;
import org.software.learning.model.common.ApiResponse;
import org.software.learning.model.common.ResponseTokens;
import org.software.learning.model.common.UserCredit;
import org.software.learning.model.entity.UserToken;
import org.software.learning.repository.UserRepository;
import org.software.learning.repository.UserTokenRepository;
import org.software.learning.security.UserEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.software.learning.model.entity.User;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class UserService {
    private UserRepository userRepository;
    private UserTokenRepository userTokenRepository;
    private UserEncryptor userEncryptor;

    public ApiResponse<?> register(UserForm userForm) {
        User user = userEncryptor.encrypt(UserTool.toUserNormal(userForm));
        if (userRepository.findById(user.getUid()).isPresent()) {
            return new ApiResponse<>(ResponseTokens.Login.registered);
        }
        userRepository.save(user);
        return new ApiResponse<>(ResponseTokens.ok, "已注册成功", user);
    }

    public ApiResponse<?> login(UserLoginForm userLoginForm) throws HttpStatusException {
        User formUser = userEncryptor.encrypt(UserTool.toUserNormal(userLoginForm));
        Optional<User> user = userRepository.findById(userLoginForm.getUid());
        if (user.isEmpty()) {
            throw new HttpStatusException(new ApiResponse<>(ResponseTokens.Login.noUser));
        } else if (user.get().getPassword().equals(formUser.getPassword())) {
            throw new HttpStatusException(new ApiResponse<>(ResponseTokens.Login.passwordError));
        }

        UserToken token = UserTool.generateToken(user.get());
        userTokenRepository.save(token);
        return new ApiResponse<>(ResponseTokens.ok, "登录成功", new UserCredit(user.get(), token));
    }

    public ApiResponse<?> autoLogin(UserAutoLoginForm userAutoLoginForm) throws HttpStatusException {
        Optional<UserToken> token = userTokenRepository.findByUidAndToken(userAutoLoginForm.getUid(), userAutoLoginForm.getToken());
        Timestamp now = Timestamp.from(Instant.now());
        if (token.isEmpty()) {
            throw new HttpStatusException(new ApiResponse<>(ResponseTokens.Login.noToken));
        } else if (now.after(token.get().getExpired())) {
            throw new HttpStatusException(new ApiResponse<>(ResponseTokens.Login.tokenExpired));
        }

        Timestamp newExpired = Timestamp.from(now.toInstant().plus(7, ChronoUnit.DAYS));
        token.get().setExpired(newExpired);
        userTokenRepository.save(token.get());
        User user = userRepository.findById(userAutoLoginForm.getUid()).get();
        return new ApiResponse<>(ResponseTokens.ok, "自动登录成功",new UserCredit(user, token.get()));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserTokenRepository(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Autowired
    public void setUserEncryptor(UserEncryptor userEncryptor) {
        this.userEncryptor = userEncryptor;
    }


}
