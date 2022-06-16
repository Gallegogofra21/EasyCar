import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/register_dto.dart';

abstract class EditEvent extends Equatable {
  const EditEvent();

  @override
  List<Object> get props => [];
}

class DoEditEvent extends EditEvent {
  final RegisterDto registerDto;
  final String image;

  const DoEditEvent(this.registerDto, this.image);
}
