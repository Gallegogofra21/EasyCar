import 'package:flutter_easycar/models/auth/login_dto.dart';
import 'package:flutter_easycar/models/auth/login_response.dart';
import 'package:flutter_easycar/models/register_dto.dart';
import 'package:flutter_easycar/models/user.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);
  Future<User> register(RegisterDto registerDto, String image);
  Future<User> edit (RegisterDto registerDto, String image);
}
