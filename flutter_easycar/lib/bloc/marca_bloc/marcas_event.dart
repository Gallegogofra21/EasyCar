import 'package:equatable/equatable.dart';

abstract class MarcasEvent extends Equatable {
  const MarcasEvent();

  @override
  List<Object> get props => [];
}

class FetchMarca extends MarcasEvent {
  const FetchMarca();

  @override
  List<Object> get props => [];
}
