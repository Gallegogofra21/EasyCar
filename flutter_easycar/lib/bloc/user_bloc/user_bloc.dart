import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_event.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_state.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  final UserRepository userRepository;

  UserBloc(this.userRepository) : super(UserWithPostInitial()) {
    on<FetchUserWithType>(_usersFetched);
  }

  void _usersFetched(
    FetchUserWithType event, Emitter<UserState> emit) async {
      try {
        final users = await userRepository.fetchUser();
        emit(UsersFetched(users));
        return;
      } on Exception catch (e) {
        emit(UserFetchedError(e.toString()));
      }
    }

}
