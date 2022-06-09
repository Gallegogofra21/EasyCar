import 'package:equatable/equatable.dart';

abstract class TiposEvent extends Equatable {
  const TiposEvent();

  @override
  List<Object> get props => [];
}

class FetchTipoWithType extends TiposEvent {
  const FetchTipoWithType();

  @override
  List<Object> get props => [];
}

class FetchTipoDetails extends TiposEvent {
  final int id;
  const FetchTipoDetails(this.id);

  @override
  List<Object> get props => [];
}
