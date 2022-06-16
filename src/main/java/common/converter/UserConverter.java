package common.converter;

import api.model.UserDto;
import ui.model.User;

public abstract class UserConverter {

  public static UserDto toUserDto(User user) {
    return new UserDto(
        user.getName(),
        user.getEmail(),
        user.getPassword()
    );
  }
}
