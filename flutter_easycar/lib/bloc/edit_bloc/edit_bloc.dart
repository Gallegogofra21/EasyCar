import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/bloc/edit_bloc/edit_event.dart';
import 'package:flutter_easycar/bloc/edit_bloc/edit_state.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repository.dart';

class EditBloc extends Bloc<EditEvent, EditState> {
  final AuthRepository authRepository;

  EditBloc(this.authRepository) : super(EditInitial()) {
    on<DoEditEvent>(_doEditEvent);
  }

  void _doEditEvent(
      DoEditEvent event, Emitter<EditState> emit) async {
    try {
      final loginResponse =
          await authRepository.register(event.registerDto, event.image);
      emit(EditSuccessState(loginResponse));
      return;
    } on Exception catch (e) {
      emit(LoginErrorState(e.toString()));
    }
  }
}
