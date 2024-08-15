import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';

class ScreenCapture {
  static const platform = MethodChannel('com.example/screen_capture');

  static Future<void> startCapture() async {
    try {
      await platform.invokeMethod('startCapture');
    } on PlatformException catch (e) {
      print("Failed to start screen capture: '${e.message}'.");
    }
  }
}

void main() async {
  await Permission.storage.request();
  await Permission.systemAlertWindow.request();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.dark(),
      home: const Page(),
    );
  }
}

class Page extends StatefulWidget {
  const Page({super.key});

  @override
  State<Page> createState() => _PageState();
}

class _PageState extends State<Page> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await ScreenCapture.startCapture();
        },
      ),
    );
  }
}
// [com.google.android.calendar, com.android.camera2, com.android.chrome, com.google.android.deskclock, com.google.android.contacts, com.tefis.dentalignment, com.google.android.documentsui, com.android.gallery3d, com.google.android.googlequicksearchbox, com.android.vending, com.google.android.apps.maps, com.google.android.apps.messaging, com.google.android.dialer, com.android.stk, com.android.settings, com.android.traceur, com.example.picture_in_picture]