import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easycar/screens/concesionarios_screen.dart';
import 'package:flutter_easycar/screens/home_screen.dart';
import 'package:flutter_easycar/screens/profile_screen.dart';
import 'package:flutter_easycar/screens/search_screen.dart';

class MenuScreen extends StatefulWidget {
  const MenuScreen({Key? key}) : super(key: key);

  @override
  _MenuScreenState createState() => _MenuScreenState();
}

class _MenuScreenState extends State<MenuScreen> {
  int _currentIndex = 0;

  List<Widget> pages = [
    const HomeScreen(),
    const SearchScreen(),
    const ConcesionariosScreen(),
    const ProfileScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: pages[_currentIndex], bottomNavigationBar: _buildBottomBar());
  }

  Widget _buildBottomBar() {
    return Container(
      decoration: const BoxDecoration(
          border: Border(
        top: BorderSide(color: Color(0xfff1f1f1), width: 1),
      )),
      padding: const EdgeInsets.symmetric(horizontal: 20),
      height: 70,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          GestureDetector(
            child: Icon(Icons.home,
                color: _currentIndex == 0
                    ? Colors.black
                    : const Color(0xff999999)),
            onTap: () {
              setState(() {
                _currentIndex = 0;
              });
            },
          ),
          GestureDetector(
            child: Icon(Icons.search,
                color: _currentIndex == 1
                    ? Colors.black
                    : const Color(0xff999999)),
            onTap: () {
              setState(() {
                _currentIndex = 1;
              });
            },
          ),
          GestureDetector(
            child: Icon(Icons.favorite,
                color: _currentIndex == 2
                    ? Colors.black
                    : const Color(0xff999999)),
            onTap: () {
              setState(() {
                _currentIndex = 2;
              });
            },
          ),
          GestureDetector(
            onTap: () {
              setState(() {
                _currentIndex = 3;
              });
            },
            child: Container(
              padding: const EdgeInsets.all(5),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(100),
                  border: Border.all(
                      color: _currentIndex == 4
                          ? Colors.black
                          : Colors.transparent,
                      width: 1)),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(100),
                child: Image.asset(
                  'assets/images/UserLogin.png',
                  width: 30,
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
