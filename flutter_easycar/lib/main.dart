import 'package:flutter/material.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/screens/details_screen.dart';
import 'package:flutter_easycar/screens/login_screen.dart';
import 'package:flutter_easycar/screens/menu_screen.dart';
import 'package:flutter_easycar/screens/profile_screen.dart';
import 'package:flutter_easycar/screens/register_screen.dart';
import 'package:flutter_easycar/screens/search_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'EasyCar',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/login',
      routes: {
        '/': (context) => const MenuScreen(),
        '/login': (context) => const LoginScreen(),
        '/register': (context) => const RegisterScreen(),
        '/search': (context) => const SearchScreen(),
        '/details': (context) => const DetailsPage(),
        '/profile': (context) => const ProfileScreen()
      },
    );
  }
}
