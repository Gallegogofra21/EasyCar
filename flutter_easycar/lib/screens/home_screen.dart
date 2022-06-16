import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/marca_bloc/marcas_bloc.dart';
import 'package:flutter_easycar/bloc/marca_bloc/marcas_event.dart';
import 'package:flutter_easycar/bloc/marca_bloc/marcas_state.dart';
import 'package:flutter_easycar/bloc/tipo_bloc/tipos_event.dart';
import 'package:flutter_easycar/models/marca.dart';
import 'package:flutter_easycar/repository/marca_repository/marca_repository.dart';
import 'package:flutter_easycar/repository/marca_repository/marca_repository_impl.dart';
import 'package:flutter_easycar/repository/tipo_repository/tipo_repository.dart';
import 'package:flutter_easycar/repository/tipo_repository/tipo_repository_impl.dart';
import 'package:flutter_easycar/models/tipo.dart';
import 'package:flutter_easycar/bloc/tipo_bloc/tipos_bloc.dart';
import 'package:flutter_easycar/bloc/tipo_bloc/tipos_state.dart';
import 'package:flutter_easycar/screens/vehiculos_marca_screen.dart';
import 'package:flutter_easycar/screens/vehiculos_tipo_screen.dart';
import 'package:flutter_easycar/ui/error_page.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late TipoRepository tipoRepository;
  late MarcaRepository marcaRepository;

  @override
  void initState() {
    super.initState();
    tipoRepository = TipoRepositoryImpl();
    marcaRepository = MarcaRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
        providers: [
          BlocProvider<TiposBloc>(
            create: (context) {
              return TiposBloc(tipoRepository)..add(const FetchTipoWithType());
            },
          ),
          BlocProvider<MarcasBloc>(
            create: (context) {
              return MarcasBloc(marcaRepository)..add(const FetchMarca());
            },
          ),
        ],
        child: Scaffold(
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
                  const Padding(
                    padding: EdgeInsets.only(top: 8.0, left: 10),
                    child: Text(
                      'Elige el coche que mejor se adapte a tus necesidades',
                      style:
                          TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                  ),
                  _createTipo(context),
                  Stack(
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
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 90.0, top: 150),
                        child: _createMarca(context),
                      ),
                    ],
                  ),
                ],
              ),
            )));
  }
}

Widget _createTipo(BuildContext context) {
  return BlocBuilder<TiposBloc, TiposState>(builder: (context, state) {
    if (state is TiposInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is TipoFetchError) {
      return ErrorPage(
        mensaje: state.mensaje,
        retry: () {
          context.watch<TiposBloc>().add(const FetchTipoWithType());
        },
      );
    } else if (state is TiposFetched) {
      return _createTipoView(context, state.tipos);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _createTipoView(BuildContext context, List<TipoContent> tipos) {
  final contentHeight = 4.0 * (MediaQuery.of(context).size.width / 2.4) / 3;
  return Column(children: [
    SizedBox(
        height: 620,
        width: 320,
        child: GridView.builder(
            physics: const NeverScrollableScrollPhysics(),
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisSpacing: 50, mainAxisSpacing: 20, crossAxisCount: 2),
            itemCount: tipos.length,
            itemBuilder: (context, index) {
              return _createTipoViewItem(context, tipos[index]);
            })),
  ]);
}

Widget _createTipoViewItem(BuildContext context, TipoContent tipo) {
  return GridTile(
    child: InkWell(
      onTap: () {
        Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => VehiculosTipoScreen(
                      id: tipo.id,
                    ),
                settings: RouteSettings(arguments: tipo.id)));
      },
      child: Column(
        children: <Widget>[
          Image.network(tipo.foto.replaceAll('localhost', '10.0.2.2'),
              height: 100),
          Container(
            width: 100,
            padding: const EdgeInsets.only(),
            alignment: Alignment.center,
            child: Text(
              tipo.nombre,
              style: const TextStyle(fontSize: 15, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    ),
  );
}

Widget _createMarca(BuildContext context) {
  return BlocBuilder<MarcasBloc, MarcasState>(builder: (context, state) {
    if (state is MarcasInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is MarcaFetchError) {
      return ErrorPage(
        mensaje: state.mensaje,
        retry: () {
          context.watch<MarcasBloc>().add(const FetchMarca());
        },
      );
    } else if (state is MarcasFetched) {
      return _createMarcaView(context, state.marcas);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _createMarcaView(BuildContext context, List<MarcaContent> marcas) {
  return Column(children: [
    SizedBox(
        height: 150,
        width: 250,
        child: GridView.builder(
            scrollDirection: Axis.horizontal,
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2),
            itemCount: marcas.length,
            itemBuilder: (context, index) {
              return _createMarcaViewItem(context, marcas[index]);
            })),
  ]);
}

Widget _createMarcaViewItem(BuildContext context, MarcaContent marca) {
  void selectedItem(String item) {
    final snackBar = SnackBar(
      content: Text(
        'Selected $item...',
      ),
      backgroundColor: Colors.red,
    );

    ScaffoldMessenger.of(context)
      ..removeCurrentSnackBar()
      ..showSnackBar(snackBar);
  }

  return GridTile(
    child: InkWell(
      onTap: () {
        Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => VehiculosMarcaScreen(
                      nombre: marca.nombre,
                    ),
                settings: RouteSettings(arguments: marca.nombre)));
      },
      child: Column(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(left: 1.0, right: 1),
            child: Container(
              width: 100,
              height: 70,
              decoration: BoxDecoration(
                  color: Colors.white,
                  border: Border.all(color: Colors.black54)),
              child: Image.network(
                  marca.foto.replaceAll('localhost', '10.0.2.2'),
                  height: 60),
            ),
          ),
        ],
      ),
    ),
  );
}
