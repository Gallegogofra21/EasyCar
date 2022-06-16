import 'package:flutter_easycar/models/user.dart';

abstract class UserRepository {
  Future<User> fetchUser();
}
