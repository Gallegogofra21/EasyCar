import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/user.dart';

abstract class EditState extends Equatable {
  const EditState();

  @override
  List<Object> get props => [];
}

class EditInitial extends EditState {}

class EditLoading extends EditState {}

class EditSuccessState extends EditState {
  final User editResponse;

  const EditSuccessState(this.editResponse);

  @override
  List<Object> get props => [editResponse];
}

class LoginErrorState extends EditState {
  final String message;

  const LoginErrorState(this.message);

  @override
  List<Object> get props => [message];
}
