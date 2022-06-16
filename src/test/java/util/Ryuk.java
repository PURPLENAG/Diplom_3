package util;

import api.client.AuthClient;
import api.model.CredentialDto;
import api.model.UserDto;
import ui.model.User;

import java.util.ArrayList;
import java.util.List;

public class Ryuk {
  private final List<CredentialDto> credentials = new ArrayList<>();

  public User remember(User user) {
    credentials.add(new CredentialDto(user.getEmail(), user.getPassword()));
    return user;
  }

  public UserDto remember(UserDto user) {
    credentials.add(new CredentialDto(user.getEmail(), user.getPassword()));
    return user;
  }

  public void wakeUp() {
    for (var credential : credentials) {
      var loginResponse = AuthClient.login(credential);
      if (loginResponse.statusCode() != 200) {
        continue;
      }
      var accessToken = loginResponse.body().jsonPath().getString("accessToken");
      AuthClient.deleteUser(accessToken);
    }
  }
}
