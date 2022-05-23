import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
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
