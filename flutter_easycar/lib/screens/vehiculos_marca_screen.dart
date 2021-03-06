import 'dart:convert';
import 'package:flutter_easycar/screens/search_screen.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_state.dart';
import 'package:flutter_easycar/constants.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository_impl.dart';
import 'package:flutter_easycar/screens/details_screen.dart';
import 'package:flutter_easycar/ui/error_page.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'details_screen2.dart';

class VehiculosMarcaScreen extends StatefulWidget {
  final dynamic nombre;
  const VehiculosMarcaScreen({Key? key, required this.nombre})
      : super(key: key);

  @override
  _VehiculosMarcaScreenState createState() => _VehiculosMarcaScreenState();
}

class _VehiculosMarcaScreenState extends State<VehiculosMarcaScreen> {
  late Future<List<VehiculoContent>> vehiculos;
  @override
  void initState() {
    super.initState();
    vehiculos = fetchVehiculosMarca(widget.nombre);
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
            padding: const EdgeInsets.only(left: 80.0),
            child: Image.asset(
              'assets/images/logo.png',
              width: 150,
              fit: BoxFit.cover,
            ),
          ),
          backgroundColor: Colors.grey.shade900,
        ),
        body: Center(
          child: FutureBuilder<List<VehiculoContent>>(
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Center(
                  child: ListView.separated(
                      itemBuilder: (BuildContext context, int index) {
                        return GridTile(
                            child: InkWell(
                                onTap: () {
                                  Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) => DetailsPage(),
                                          settings: RouteSettings(
                                              arguments:
                                                  snapshot.data!.single)));
                                },
                                child: Column(
                                  children: <Widget>[
                                    Padding(
                                      padding: const EdgeInsets.only(top: 8.0),
                                      child: Container(
                                        child: Image.network(
                                            snapshot.data![index].foto1
                                                .replaceAll(
                                                    'localhost', '10.0.2.2'),
                                            fit: BoxFit.cover,
                                            height: 300),
                                        decoration: BoxDecoration(
                                          color: Colors.white,
                                          borderRadius: const BorderRadius.only(
                                              topLeft: Radius.circular(10),
                                              topRight: Radius.circular(10),
                                              bottomLeft: Radius.circular(10),
                                              bottomRight: Radius.circular(10)),
                                          boxShadow: [
                                            BoxShadow(
                                              color:
                                                  Colors.grey.withOpacity(0.6),
                                              spreadRadius: 5,
                                              blurRadius: 7,
                                              offset: const Offset(0,
                                                  3), // changes position of shadow
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                    Padding(
                                      padding: const EdgeInsets.only(top: 8.0),
                                      child: Row(children: [
                                        Padding(
                                          padding: const EdgeInsets.only(
                                              left: 8.0, top: 10),
                                          child: Text(
                                            snapshot.data![index].nombreMarca,
                                            style:
                                                const TextStyle(fontSize: 20),
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(
                                              left: 8.0, top: 10),
                                          child: Text(
                                              snapshot.data![index].modelo,
                                              style: const TextStyle(
                                                  fontSize: 20)),
                                        ),
                                      ]),
                                    ),
                                    Row(
                                      children: [
                                        Padding(
                                          padding:
                                              const EdgeInsets.only(left: 8.0),
                                          child: Text(
                                              snapshot.data![index].version,
                                              style: const TextStyle(
                                                  fontSize: 16,
                                                  fontWeight: FontWeight.w500)),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(
                                              left: 170.0),
                                          child: Text(
                                            (snapshot.data![index].precio *
                                                        1000)
                                                    .toString() +
                                                '???',
                                            style:
                                                const TextStyle(fontSize: 18),
                                          ),
                                        )
                                      ],
                                    ),
                                    Padding(
                                      padding: const EdgeInsets.only(
                                          left: 8.0, top: 5, bottom: 20),
                                      child: Row(
                                        children: [
                                          Text(
                                            snapshot.data![index]
                                                .fechaMatriculacion,
                                            style: const TextStyle(
                                                color: Colors.grey),
                                          ),
                                          SizedBox(
                                              height: 10,
                                              child: Row(children: const [
                                                VerticalDivider(
                                                  color: Colors.grey,
                                                  thickness:
                                                      1, //thickness of divier line
                                                ),
                                              ])),
                                          Text(
                                            snapshot.data![index].kilometraje,
                                            style: const TextStyle(
                                                color: Colors.grey),
                                          ),
                                          SizedBox(
                                              height: 10,
                                              child: Row(children: const [
                                                VerticalDivider(
                                                  color: Colors.grey,
                                                  thickness:
                                                      1, //thickness of divier line
                                                ),
                                              ])),
                                          Text(
                                            snapshot.data![index].potencia,
                                            style: const TextStyle(
                                                color: Colors.grey),
                                          ),
                                          SizedBox(
                                              height: 10,
                                              child: Row(children: const [
                                                VerticalDivider(
                                                  color: Colors.grey,
                                                  thickness:
                                                      1, //thickness of divier line
                                                ),
                                              ])),
                                          Text(
                                            snapshot.data![index].marchas,
                                            style: const TextStyle(
                                                color: Colors.grey),
                                          ),
                                        ],
                                      ),
                                    ),
                                  ],
                                )));
                      },
                      separatorBuilder: (BuildContext context, int index) =>
                          const Divider(),
                      itemCount: snapshot.data!.length),
                );
              } else if (snapshot.hasError) {
                return Center(
                    child: Text('Esta marca todav??a no tiene veh??culos'));
              }
              return const CircularProgressIndicator();
            },
            future: vehiculos,
          ),
        ));
  }
}

Future<List<VehiculoContent>> fetchVehiculosMarca(nombre) async {
  final prefs = await SharedPreferences.getInstance();
  String? token = prefs.getString('token');

  Map<String, String> headers = {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ${token}'
  };
  final response = await http.get(
      Uri.parse('${Constant.ApiBaseUrl}/vehiculo/marca/${nombre}'),
      headers: headers);
  print(response.statusCode);

  if (response.statusCode == 200) {
    return VehiculoResponse.fromJson(json.decode(response.body)).content;
  } else {
    throw Exception('Failed to load vehiculos');
  }
}

Widget _getPrecio(VehiculoContent vehiculo) {
  double precioDoble = vehiculo.precio;
  return Text(precioDoble.toString());
}
