import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_state.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/ui/error_page.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({Key? key}) : super(key: key);

  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Padding(
            padding: const EdgeInsets.only(left: 100.0),
            child: Image.asset(
              'assets/images/logo.png',
              width: 150,
              fit: BoxFit.cover,
            ),
          ),
          backgroundColor: Colors.grey.shade900,
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Stack(children: [
                Image.asset('assets/images/Foto1.PNG'),
                const Padding(
                  padding: EdgeInsets.only(left: 10, top: 10),
                  child: Text(
                    'Nº 1 en Europa en venta de coches online',
                    style: TextStyle(color: Colors.white, fontSize: 30),
                  ),
                )
              ]),
              Padding(
                padding: const EdgeInsets.only(top: 450.0),
                child: Stack(
                  children: [
                    Image.asset('assets/images/Foto2.PNG'),
                    const Padding(
                      padding: EdgeInsets.only(top: 25.0, left: 35),
                      child: Text(
                        '¿Qué marca estás buscando?',
                        style: TextStyle(color: Colors.white, fontSize: 24),
                      ),
                    ),
                    const Padding(
                      padding: EdgeInsets.only(top: 60.0, left: 50),
                      child: Text(
                        'Encuentra en nuestro concesionario multimarca el coche de tus sueños',
                        style: TextStyle(color: Colors.white, fontSize: 18),
                      ),
                    )
                  ],
                ),
              )
            ],
          ),
        ));
  }
}

Widget _createVehiculo(BuildContext context) {
  return BlocBuilder<VehiculosBloc, VehiculosState>(builder: (context, state) {
    if (state is VehiculosInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is VehiculoFetchError) {
      return ErrorPage(
        mensaje: state.mensaje,
        retry: () {
          context.watch<VehiculosBloc>().add(const FetchVehiculo());
        },
      );
    } else if (state is VehiculosFetched) {
      return _createVehiculoView(context, state.vehiculos);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _createVehiculoView(BuildContext context, List<VehiculoContent> vehiculos) {
  return Column(children: [
    SizedBox(
      height: 500,
      child: ListView.separated(itemBuilder: (BuildContext context, int index) {
        return _createVehiculoViewItem(context, vehiculos[index]);
      },
      separatorBuilder: (context, index) => const VerticalDivider(color: Colors.transparent,
      width: 6.0,),
      itemCount: vehiculos.length,),
    ),
  ]);
}

Widget _createVehiculoViewItem(BuildContext context, VehiculoContent vehiculo) {
  return Container(child: Column(children: <Widget>[Text(vehiculo.modelo), Image.network(vehiculo.foto1.replaceAll('localhost', '10.0.2.2'),
  fit: BoxFit.cover,
  height: 200,)],));
}
